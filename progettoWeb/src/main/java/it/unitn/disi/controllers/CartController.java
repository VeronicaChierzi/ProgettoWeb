package it.unitn.disi.controllers;

import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.ShopProduct;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.carts.Cart;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.entities.orders.OrderProduct;
import javax.servlet.http.HttpSession;

public class CartController {

	//restituisce il carrello. se non esiste, lo crea.
	public static Cart getOrCreateCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			User user = (User) session.getAttribute("user");
			int idUser;
			if (user != null) {
				idUser = user.getId();
			} else {
				idUser = -1;
			}
			cart = new Cart(idUser);
			session.setAttribute("cart", cart);
		}
		return cart;
	}

	//cerca un ordine riferito allo stesso negozio o lo crea
	//cerca un orderProduct con lo stesso prodotto o lo crea
	//imposta il prezzo reale
	//imposta la quantità (minimo fra (quantità già aggiunta+appena aggiunta),(quantità in vendita))
	public static void addToCart(HttpSession session, ShopProduct sp, int quantityToAdd) {
		Cart cart = getOrCreateCart(session);
		Order o = cercaOrder(cart, sp.getIdShop());
		OrderProduct op = cercaOrderProduct(o, sp.getIdProduct());
		op.setPrice(sp.getPrice());
		int nextQuantity = Math.min(op.getQuantity() + quantityToAdd, sp.getQuantity());
		op.setQuantity(nextQuantity);
	}

	//cerca un ordine con idShop. se non lo trova, lo crea
	private static Order cercaOrder(Cart cart, int idShop) {
		for (Order o : cart.getOrders()) {
			if (o.getIdShop() == idShop) { //è stato trovato un ordine con lo stesso negozio
				return o;
			}
		}
		Order o = new Order(-1, cart.getIdUser(), idShop, null);
		cart.getOrders().add(o);
		return o;
	}

	private static OrderProduct cercaOrderProduct(Order order, int idProduct) {
		for (OrderProduct op : order.getOrderProducts()) {
			if (op.getIdProduct() == idProduct) { //è stato trovato un ordine con lo stesso negozio
				return op;
			}
		}
		OrderProduct op = new OrderProduct(order.getId(), idProduct, 0.0f, 0);
		order.getOrderProducts().add(op);
		return op;
	}

	public static boolean checkCart(HttpSession session, User user, ShopProductDAO shopProductDAO) throws DAOException {
		Cart cart = getOrCreateCart(session);

		//controlla che l'id del carrello sia ugule all'id dell'utente
		if (cart.getIdUser() != user.getId()) {
			System.err.println("Errore: l'id dell'utente loggato è diverso dall'id dell'utente del carrello");
			return false;
		}

		//controlla che il carrello abbia almeno un ordine
		if (cart.getOrders().size() > 0) {
			System.err.println("Errore: il carrello non contiene nessun ordine");
			return false;
		}

		//controlla ogni ordine
		for (Order o : cart.getOrders()) {
			//controlla che l'id dell'ordine sia uguale all'id dell'utente
			if (o.getIdUser() != user.getId()) {
				System.err.println("Errore: l'id dell'utente loggato è diverso dall'id dell'utente dell'ordine");
				return false;
			}
			
			//controlla che l'ordine abbia almeno un orderProduct
			if (o.getOrderProducts().size() > 0) {
				System.err.println("Errore: l'ordine non contiene nessun orderProduct");
				return false;
			}

			//controlla che non ci siano più ordini diversi riferiti allo stesso negozio
			for (Order o2 : cart.getOrders()) {
				if (o != o2 && o.getIdShop() == o2.getIdShop()) {
					System.err.println("Errore: nel carrello ci sono più ordini riferiti allo stesso negozio");
					return false;
				}
			}

			//controlla ogni orderProduct
			for (OrderProduct op : o.getOrderProducts()) {
				//controlla che non ci siano più orderProduct diversi riferiti allo stesso prodotto
				for (OrderProduct op2 : o.getOrderProducts()) {
					if (op != op2 && op.getIdProduct() == op2.getIdProduct()) {
						System.err.println("Errore: in un ordine ci sono più orderProduct riferiti allo stesso prodotto");
						return false;
					}
				}

				//ricava dal datatbase i dati (prezzo, quantita) aggiornati rispetto al prodotto venduto dal negozio
				ShopProduct sp = shopProductDAO.getShopProduct(op.getIdProduct(), o.getIdShop());

				//controlla che la quantità sia uguale a quella attualmente nel database
				if (op.getQuantity() > sp.getQuantity()) {
					System.err.println("Errore: la quantita di oggetti " + op.getIdProduct() + " venduti dal venditore è inferiore rispetto alla quantita che l'utente ha nel carrello in sessione. il venditore potrebbe aver già venduto alcuni oggetti.");
					return false;
				}

				//controlla che il prezzo sia uguale a quello attualmente nel database
				if (op.getPrice() != sp.getPrice()) {
					System.err.println("Errore: il prezzo del database è diverso da quello memorizzato nella sessione. il venditore potrebbe aver cambiato il prezzo.");
					return false;
				}
				
				if(aggiornaOrderProduct(op, o, shopProductDAO)==false){
					System.err.println("Il prezzo o la quantità del prodotto " + op.getIdProduct() + " sono cambiati");
					return false;
				}
			}
		}
		return true;
	}

	//controlla che i dati (prezzo, quantità) dell'orderProduct siano coerenti con quelli attualmente contenuti nel database
	//i dati potrebbe essere diversi perchè il venditore potrebbe aver già venduto alcuni oggetti oppure potrebbe aver cambiato prezzo
	//restituisce true se i dati non sono cambiati
	//restituisce false se i dati sono cambiati
	private static boolean aggiornaOrderProduct(OrderProduct op, Order o, ShopProductDAO shopProductDAO) throws DAOException {
		boolean b = true;
		//ricava dal datatbase i dati (prezzo, quantita) aggiornati rispetto al prodotto venduto dal negozio
		ShopProduct sp = shopProductDAO.getShopProduct(op.getIdProduct(), o.getIdShop());

		//controlla che la quantità sia uguale a quella attualmente nel database
		if (op.getQuantity() > sp.getQuantity()) {
			op.setQuantity(sp.getQuantity());
			System.err.println("Errore: la quantita di oggetti " + op.getIdProduct() + " venduti dal venditore è inferiore rispetto alla quantita che l'utente ha nel carrello in sessione. il venditore potrebbe aver già venduto alcuni oggetti.");
			b = false;
		}

		//controlla che il prezzo sia uguale a quello attualmente nel database
		if (op.getPrice() != sp.getPrice()) {
			op.setPrice(sp.getPrice());
			System.err.println("Errore: il prezzo del database è diverso da quello memorizzato nella sessione. il venditore potrebbe aver cambiato il prezzo.");
			b = false;
		}
		return b;
	}

}
