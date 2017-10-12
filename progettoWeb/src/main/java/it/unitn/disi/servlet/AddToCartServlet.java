package it.unitn.disi.servlet;

import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.ShopProduct;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.carts.Cart;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.entities.orders.OrderProduct;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends MyServlet {

	private ProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		productDAO = (ProductDAO) initDao(ProductDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idProduct = Integer.parseInt(request.getParameter("id_product"));
		int idShop = Integer.parseInt(request.getParameter("id_shop"));
		float clientPrice = Float.parseFloat(request.getParameter("current_price"));
		int quantityToAdd = Integer.parseInt(request.getParameter("quantity"));

		HttpSession session = request.getSession(true);
		Cart cart = getOrCreateCart(session);
		try {
			ShopProduct sp = productDAO.getShopProduct(idProduct, idShop);
			float realPrice = sp.getPrice(); //ricava dal database il prezzo reale
			if (realPrice != clientPrice) {
				//stampa messaggio per l'utente. il prezzo è stato cambiato.
			}
			int maxQuantity = sp.getQuantity(); //ricava dal database la quantita massima acquistabile
			addToCart(cart, idShop, idProduct, realPrice, quantityToAdd, maxQuantity);
			redirect(response, "paginaDiDecisione.jsp");
		} catch (DAOException ex) {
			System.err.println(ex.getMessage());
			//redirect?
		}
	}

	private Cart getOrCreateCart(HttpSession session) {
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
	//cerca lo stesso orderProduct o lo crea	
	//imposta il prezzo reale
	//imposta la quantità
	private void addToCart(Cart cart, int idShop, int idProduct, float price, int quantityToAdd, int maxQuantity) {
		Order o = cercaOrder(cart, idShop);
		OrderProduct op = cercaOrderProduct(o, idProduct);
		op.setPrice(price);
		int nextQuantity = Math.min(op.getQuantity() + quantityToAdd, maxQuantity);
		op.setQuantity(nextQuantity);
	}

	//cerca un ordine con idShop. se non lo trova, lo crea
	private Order cercaOrder(Cart cart, int idShop) {
		for (Order o : cart.getOrders()) {
			if (o.getIdShop() == idShop) { //è stato trovato un ordine con lo stesso negozio
				return o;
			}
		}
		Order o = new Order(-1, cart.getIdUser(), idShop, null);
		cart.getOrders().add(o);
		return o;
	}

	private OrderProduct cercaOrderProduct(Order order, int idProduct) {
		for (OrderProduct op : order.getOrderProducts()) {
			if (op.getIdProduct() == idProduct) { //è stato trovato un ordine con lo stesso negozio
				return op;
			}
		}
		OrderProduct op = new OrderProduct(order.getId(), idProduct, 0.0f, 0);
		order.getOrderProducts().add(op);
		return op;
	}

	/*
	private void cercaOrder(CartSession cart, int idShop, int idProduct, float price, int quantity) {
		Order order = null;
		boolean foundOrder = false;
		Iterator<Order> iterator = cart.getOrders().iterator();
		while (!foundOrder && iterator.hasNext()) {
			Order o = iterator.next();
			if (o.getIdShop() == idShop) { //è stato trovato un ordine con lo stesso negozio
				order = o;
				cercaOrderProduct(o, idProduct, price, quantity);
			}
		}
		if (!foundOrder) { //non è stato trovato un ordine con lo stesso negozio
			Order o = creaEAggiungiOrdine(cart, idShop);
			creaEAggiungiProdotto(o, idProduct, price, quantity);
		}
		return order;
	}

	private void cercaOrderProduct(Order o, int idProduct, float price, int quantity) {
		boolean foundProduct = false;
		Iterator<OrderProduct> i = o.getOrderProducts().iterator();
		while (!foundProduct && i.hasNext()) {
			OrderProduct op = i.next();
			if (op.getIdProduct() == idProduct) { //è stato lo stesso prodotto
				int nextQuantity = quantity + op.getQuantity();
				op.setQuantity(nextQuantity); //aumenta la quantità
			}
		}
		if (!foundProduct) { //non è stato trovato lo stesso prodotto
			creaEAggiungiProdotto(o, idProduct, price, quantity);
		}
	}

	private Order creaEAggiungiOrdine(CartSession cart, int idShop) {
		Order o = new Order(-1, -1, idShop, null);
		cart.getOrders().add(o);
		return o;
	}

	private void creaEAggiungiProdotto(Order order, int idProduct, float price, int quantity) {
		OrderProduct op = new OrderProduct(-1, idProduct, price, quantity);
		order.getOrderProducts().add(op);
	}
	 */
}

//get real price
//correct price & set message
//nothing
//add product to cart (idProduct, idShop, realPrice, quantity)
//check if order with same shop already exist
//then, check if same product already exist
//then increase quantity
//else create new orderproduct
//else, create it & add orderProduct
/*
	private void addToCart(CartSession cart, int idShop, int idProduct, float price, int quantity) {
		boolean foundOrder = false;
		Iterator<Order> iterator = cart.getOrders().iterator();
		while (!foundOrder && iterator.hasNext()) {
			Order o = iterator.next();
			if (o.getIdShop() == idShop) { //è stato trovato un ordine con lo stesso negozio

				boolean foundProduct = false;
				Iterator<OrderProduct> i = o.getOrderProducts().iterator();
				while (!foundProduct && i.hasNext()) {
					OrderProduct op = i.next();
					if (op.getIdProduct() == idProduct) { //è stato lo stesso prodotto
						int nextQuantity = quantity + op.getQuantity();
						op.setQuantity(nextQuantity); //aumenta la quantità
					}
				}
				if (!foundProduct) { //non è stato trovato lo stesso prodotto
					OrderProduct op = new OrderProduct(-1, idProduct, price, quantity);
					o.getOrderProducts().add(op);
				}

			}
		}
		if (!foundOrder) { //non è stato trovato un ordine con lo stesso negozio
			Order o = new Order(-1, -1, idShop, null);
			cart.getOrders().add(o);
			OrderProduct op = new OrderProduct(-1, idProduct, price, quantity);
			o.getOrderProducts().add(op);
		}
	}
 */
