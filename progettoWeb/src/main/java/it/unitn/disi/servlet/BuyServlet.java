package it.unitn.disi.servlet;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.carts.Cart;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BuyServlet", urlPatterns = {"/BuyServlet"})
public class BuyServlet extends MyServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = (OrderDAO) initDao(OrderDAO.class);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		checkCart(cart, user);
		try {
			orderDAO.buyCart(cart);
            redirect(response, "index.jsp");
		} catch (DAOException ex) {
			System.err.println("Errore buy servlet: " + ex.getMessage());
            redirect(response, "index.jsp");
		}
	}
	
	/*
	private void convertCartToRealOrders(Cart cart, User user){
		ArrayList<Order> orders = cart.getOrders();
		for (Order o : orders) {
			o.setIdUser(user.getId());
		}
	}
	*/
	
	private void checkCart(Cart cart, User user){
		/*
		ArrayList<Order> orders = cart.getOrders();
		for (Order o : orders) {
			o.getIdShop();
		}
		*/
	}
	
	//private void checkCart(Cart cart, User user){
		/*
		int idUser = user.getId();
		//controlla che le informazioni siano corrette
		ArrayList<OrderTemp> ordersTemp = new ArrayList<>();
		
		ArrayList<Order> orders = cart.getOrders();
		for (Order o : orders) {
			boolean found = false;
			int idShop = i.getIdShop();
			for (OrderTemp o : ordersTemp) {
				if(!found && o.idShop==idShop){
					//trovato ordine negozio X
					found = true;
					//creare orderProduct
					OrderProductTemp opt = new OrderProductTemp(i.getIdProduct(), i.getCurrentPrice(), i.getQuantity());
					//aggiungi a ordine l'oggetto
					o.add(opt);
				}
			}
			if(!found){
				OrderTemp o = new OrderTemp(idShop);
				OrderProductTemp opt = new OrderProductTemp(i.getIdProduct(), i.getCurrentPrice(), i.getIdShop());
				o.add(opt);
			}
		}*/

		/*
		int idUser = user.getId();
		//controlla che le informazioni siano corrette
		ArrayList<Order> orders = new ArrayList<>();
		ArrayList<ArrayList<OrderProduct>> orderProducts = new ArrayList<>();
		
		ArrayList<CartItem> items = cart.getItems();
		for (CartItem i : items) {
			boolean found = false;
			int idShop = i.getIdShop();
			for (Order o : orders) {
				if(!found && o.getIdShop()==idShop){
					//trovato ordine negozio X
					found = true;
					//creare orderProduct
					//aggiungi a ordine l'oggetto
					o.add(i);
				}
			}
			if(!found){
				OrderProduct op = new OrderProduct();
				op.setIdProduct(i.getIdProduct());
				op.setQuantity(i.getQuantity());
				op.setPrice(i.getCurrentPrice());
				
				ArrayList<OrderProduct> a = new ArrayList<>();
				a.add(op);
				orderProducts.add(a);				
				
				Order o = new Order();
				o.setIdShop(idShop);
				o.setIdUser(idUser);
				//crea orderProduct
				//arraylist temporaneo?
				OrderProduct op = new OrderProduct(i.getIdProduct(), i.getIdShop(), i.getCurrentPrice(), i.getQuantity());
				o.getOrderProducts().add(op);
			}
		}
		*/
		
		//raggruppa in base a shop
		//crea Order
	//}
	
	/*
	public class OrderTemp{
		public int idShop;
		public ArrayList<OrderProductTemp> orderProductsTemp;

		public OrderTemp(int idShop) {
			this.idShop = idShop;
			orderProductsTemp = new ArrayList<>();
		}
		
		public void add(OrderProductTemp opt){
			orderProductsTemp.add(opt);
		}
		
	}
	
	public class OrderProductTemp{
		public int idProduct;
		public float price;
		public int quantity;

		public OrderProductTemp(int idProduct, float price, int quantity) {
			this.idProduct = idProduct;
			this.price = price;
			this.quantity = quantity;
		}
	}*/

}
