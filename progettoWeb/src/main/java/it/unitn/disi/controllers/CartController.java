package it.unitn.disi.controllers;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.ShopProduct;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.carts.Cart;
import it.unitn.disi.entities.carts.CartItem;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.entities.orders.OrderProduct;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyUtils;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CartController {

	//restituisce il carrello. se non esiste, lo crea.
	private static Cart getOrCreateCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			if (MyUtils.debugCartController) {
				System.err.println("getOrCreateCart. Il carrello non esiste ancora. Verrà creato un nuovo carrello.");
			}
			User user = (User) session.getAttribute(Model.Session.user);
			int idUser;
			if (user != null) {
				idUser = user.getId();
			} else {
				idUser = -1;
			}
			cart = new Cart(idUser);
			session.setAttribute(Model.Session.cart, cart);
		}
		return cart;
	}

	//cerca un ordine riferito allo stesso negozio o lo crea
	//cerca un orderProduct con lo stesso prodotto o lo crea
	//imposta il prezzo reale
	//imposta la quantità (minimo fra (quantità già aggiunta+appena aggiunta),(quantità in vendita))
	public static void addToCart(HttpSession session, ShopProduct sp, int quantityToAdd) {
		if (MyUtils.debugCartController) {
			System.err.println("Inizio addToCart. L'oggetto sta per essere aggiunto al carrello. sp: " + sp + ", quantita:" + quantityToAdd);
		}
		Cart cart = getOrCreateCart(session);
		Order o = cercaOrder(cart, sp.getIdShop());
		OrderProduct op = cercaOrderProduct(o, sp.getIdProduct());
		op.setPrice(sp.getPrice());
		int nextQuantity = Math.min(op.getQuantity() + quantityToAdd, sp.getQuantity());
		op.setQuantity(nextQuantity);
		if (MyUtils.debugCartController) {
			System.err.println("Fine addTocart. L'oggetto è stato aggiunto al carrello");
		}
	}

	//cerca un ordine con idShop. se non lo trova, lo crea
	private static Order cercaOrder(Cart cart, int idShop) {
		for (Order o : cart.getOrders()) {
			if (o.getIdShop() == idShop) { //è stato trovato un ordine con lo stesso negozio
				return o;
			}
		}
		Order o = new Order(-1, cart.getIdUser(), idShop, null, true, false, null);
		cart.getOrders().add(o);
		return o;
	}

	private static OrderProduct cercaOrderProduct(Order order, int idProduct) {
		for (OrderProduct op : order.getOrderProducts()) {
			if (op.getIdProduct() == idProduct) { //è stato trovato un ordine con lo stesso negozio
				return op;
			}
		}
		OrderProduct op = new OrderProduct(0,order.getId(), idProduct, 0.0f, 0);
		order.getOrderProducts().add(op);
		return op;
	}

	public static boolean buyCart(HttpSession session, User user, ShopProductDAO shopProductDAO, OrderDAO orderDAO) throws DAOException {
		if (MyUtils.debugCartController) {
			System.err.println("Inizio buyCart. L'acquisto dei prodotti nel carrello sta per essere effettuato");
		}
		if (checkCart(session, user, shopProductDAO)) {
			if (orderDAO.buyCart(CartController.getOrCreateCart(session))) {
				if (MyUtils.debugCartController) {
					System.err.println("Fine buyCart. L'acquisto dei prodotti nel carrello è stato completato");
				}
				deleteCartAndCreateNew(session);
				return true;
			} else {
				System.err.println("Fine buyCart. Impossibile effettuare l'acquisto. Problema in orderDAO.buyCart");
				return false;
			}
		} else {
			System.err.println("Fine buyCart. Impossibile effettuare l'acquisto. Problema in checkCart.");
			return false;
		}
	}

	private static boolean checkCart(HttpSession session, User user, ShopProductDAO shopProductDAO) throws DAOException {
		if (MyUtils.debugCartController) {
			System.err.println("Inizio checkCart. Il carrello verrà controllato (confronto prezzi attuali/precedenti, quantità, ecc...)");
			printCart(session);
		}
		Cart cart = getOrCreateCart(session);

		//controlla che il carrello non sia vuoto
		if (cart == null || cart.isEmpty()) {
			System.err.println("Errore: il carrello è vuoto");
			return false;
		}

		//controlla che l'id del carrello sia ugule all'id dell'utente
		if (cart.getIdUser() != user.getId()) {
			System.err.println("Errore: l'id dell'utente loggato è diverso dall'id dell'utente del carrello");
			return false;
		}

		//controlla che il carrello abbia almeno un ordine
		if (cart.getOrders().isEmpty()) {
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
			if (o.getOrderProducts().isEmpty()) {
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
				ShopProduct sp = shopProductDAO.getShopProduct(op.getIdProduct(), o.getIdShop(), false, false);

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

				if (aggiornaOrderProduct(op, o, shopProductDAO) == false) {
					System.err.println("Il prezzo o la quantità del prodotto " + op.getIdProduct() + " sono cambiati");
					return false;
				}
			}
		}
		if (MyUtils.debugCartController) {
			System.err.println("Fine checkCart. Il carrello è corretto");
		}
		return true;
	}

	private static void deleteCartAndCreateNew(HttpSession session) {
		Cart currentCart = getOrCreateCart(session);
		currentCart.getOrders().clear();
	}

	//controlla che i dati (prezzo, quantità) dell'orderProduct siano coerenti con quelli attualmente contenuti nel database
	//i dati potrebbe essere diversi perchè il venditore potrebbe aver già venduto alcuni oggetti oppure potrebbe aver cambiato prezzo
	//restituisce true se i dati non sono cambiati
	//restituisce false se i dati sono cambiati
	private static boolean aggiornaOrderProduct(OrderProduct op, Order o, ShopProductDAO shopProductDAO) throws DAOException {
		boolean b = true;
		//ricava dal datatbase i dati (prezzo, quantita) aggiornati rispetto al prodotto venduto dal negozio
		ShopProduct sp = shopProductDAO.getShopProduct(op.getIdProduct(), o.getIdShop(), false, false);

		//controlla che la quantità sia uguale a quella attualmente nel database
		if (op.getQuantity() > sp.getQuantity()) {
			op.setQuantity(sp.getQuantity());
			System.err.println("Aggiornamento carrello: la quantita di oggetti " + op.getIdProduct() + " venduti dal venditore è inferiore rispetto alla quantita che l'utente ha nel carrello in sessione. il venditore potrebbe aver già venduto alcuni oggetti.");
			b = false;
		}

		//controlla che il prezzo sia uguale a quello attualmente nel database
		if (op.getPrice() != sp.getPrice()) {
			op.setPrice(sp.getPrice());
			System.err.println("Aggiornamento carrello: il prezzo del database è diverso da quello memorizzato nella sessione. il venditore potrebbe aver cambiato il prezzo.");
			b = false;
		}
		return b;
	}

	private static void printCart(HttpSession session) {
		Cart cart = getOrCreateCart(session);
		System.err.println("Stampa carrello");
		String s = "";
		s += "\tCart\n";
		s += "idUser: " + cart.getIdUser() + "\n";
		s += "totalPrice: " + cart.getTotalPrice() + "\n";
		s += "orders size: " + cart.getOrders().size() + "\n";
		for (Order o : cart.getOrders()) {
			s += "\tOrder\n";
			s += "id: " + o.getId() + "\n";
			s += "idShop: " + o.getIdShop() + "\n";
			s += "idUser: " + o.getIdUser() + "\n";
			s += "datetimePurchase: " + o.getDatetimePurchase() + "\n";
			s += "totalPrice: " + o.getTotalPrice() + "\n";
			s += "orderProducts size: " + o.getOrderProducts().size() + "\n";
			for (OrderProduct op : o.getOrderProducts()) {
				s += "\tOrder Product\n";
				s += "idOrder: " + op.getIdOrder() + "\n";
				s += "idProduct: " + op.getIdProduct() + "\n";
				s += "price: " + op.getPrice() + "\n";
				s += "quantity: " + op.getQuantity() + "\n";
				s += "totalPrice: " + op.getTotalPrice() + "\n";
			}
		}
		System.err.println(s);
	}

	//connette l'utente che ha appena efettuato il login con il carrello (se esisteva già un carrello. altrimenti lo crea)
	public static void connectCartToUser(HttpSession session, HttpServletRequest request) {
		if (MyUtils.debugCartController) {
			System.err.println("Inizio connectCartToUser. Il carrello sarà collegato all'utente.");
		}
		try {
			Cart cart = getOrCreateCart(session);
			User user = Model.Session.getUserLogged(request);
			cart.setIdUser(user.getId());
			for (Order o : cart.getOrders()) {
				o.setIdUser(user.getId());
			}
			if (MyUtils.debugCartController) {
				System.err.println("Fine connectCartToUser. Il Carrello è stato collegato all'utente");
			}
		} catch (Exception e) {
			System.err.println("Errore utente non loggato in connectCartToUser: " + e.getMessage());
			System.err.println("Fine connectCartToUser. Impossibile connettere il carrello all'utente.");
		}
	}

	//modifica la quantità di un prodotto nel carrello
	public static void changeQuantity(HttpSession session, ShopProduct sp, int quantity) {
		if (MyUtils.debugCartController) {
			System.err.println("Inizio changeQuantity. La quantità dell'oggetto sta per essere modificata. sp: " + sp + ", quantita:" + quantity);
		}
		Cart cart = getOrCreateCart(session);
		Order o = cercaOrder(cart, sp.getIdShop());
		OrderProduct op = cercaOrderProduct(o, sp.getIdProduct());
		op.setPrice(sp.getPrice());
		int nextQuantity = Math.min(quantity, sp.getQuantity());
		op.setQuantity(nextQuantity);
		if (MyUtils.debugCartController) {
			System.err.println("Fine changeQuantity. La quantità dell'oggetto è stata modificata");
		}
	}

	//rimuove un prodotto dal carrello
	public static void deleteProduct(HttpSession session, ShopProduct sp) {
		if (MyUtils.debugCartController) {
			System.err.println("Inizio deleteProduct. L'oggetto sta per essere rimosso dal carrello. sp: " + sp);
		}
		Cart cart = getOrCreateCart(session);
		Order o = cercaOrder(cart, sp.getIdShop());
		OrderProduct op = cercaOrderProduct(o, sp.getIdProduct());
		o.getOrderProducts().remove(op); //rimuove orderProduct
		if (o.getOrderProducts().isEmpty()) { //se order non ha più nessun orderProduct, rimuove anche order
			cart.getOrders().remove(o);
		}
		if (MyUtils.debugCartController) {
			System.err.println("Fine deleteProduct. L'oggetto è stato rimosso dal carrello");
		}
	}

	public static void updateCart(HttpSession session, ShopProductDAO shopProductDAO) throws DAOException {
		Cart cart = getOrCreateCart(session);
		ArrayList<CartItem> tempList = new ArrayList<>();
		for (Order o : cart.getOrders()) {
			for (OrderProduct op : o.getOrderProducts()) {
				aggiornaOrderProduct(op, o, shopProductDAO);
				ShopProduct sp = shopProductDAO.getShopProduct(op.getIdProduct(), o.getIdShop(), true, true);
				CartItem ci = new CartItem(op.getQuantity(), sp, o.isSpedizione());
				tempList.add(ci);
			}
		}
		CartItem[] cartItems = new CartItem[tempList.size()];
		cartItems = (CartItem[]) tempList.toArray(cartItems);
		cart.setCartItems(cartItems);
	}

	//modifica la spedizione/ritiro in negozio di un prodotto (e del suo ordine) nel carrello
	public static void changeSpedizione(HttpSession session, ShopProduct sp, boolean spedizione) {
		if (MyUtils.debugCartController) {
			System.err.println("Inizio changeSpedizione. La spedizione/ritiro dell'oggetto sta per essere modificata. sp: " + sp + ", spedizione:" + spedizione);
		}
		Cart cart = getOrCreateCart(session);
		Order o = cercaOrder(cart, sp.getIdShop());
		OrderProduct op = cercaOrderProduct(o, sp.getIdProduct());
		//if(sp.isSpedizione()){
		o.setSpedizione(spedizione);
		//} else {
		//o.setSpedizione(false);
		//}
		if (MyUtils.debugCartController) {
			System.err.println("Fine changeSpedizione. La spedizione/ritiro dell'oggetto è stata modificata");
		}
	}

}
