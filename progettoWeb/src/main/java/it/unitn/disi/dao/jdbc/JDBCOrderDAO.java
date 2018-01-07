package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.OrderProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.carts.Cart;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.entities.orders.OrderProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;


public class JDBCOrderDAO extends JDBCDAO<Order, Integer> implements OrderDAO {

	private static final Class classe = Order.class;
	private static final String[] nomiColonne = new String[]{"id", "id_user", "id_shop", "datetime_purchase"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, int.class, Timestamp.class};

	private OrderProductDAO orderProductDAO;

	public JDBCOrderDAO(Connection con) {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		orderProductDAO = (OrderProductDAO) initDao(OrderProductDAO.class, daoFactory);
	}
	
	@Override
	public Order[] getOrdersUser(int idUser) throws DAOException {
		String query = "SELECT * FROM orders WHERE id_user=? ORDER BY datetime_purchase DESC";
		Object[] parametriQuery = new Object[]{idUser};
		Order[] orders = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Order o : orders) {
			OrderProduct[] op = orderProductDAO.getOrderProductsByIdOrder(o.getId());
			ArrayList<OrderProduct> opList = new ArrayList<>(Arrays.asList(op));
			o.setOrderProducts(opList);
		}
		return orders;
	}

	@Override
	public Order getOrderUser(int id, int idUser) throws DAOException {
		String query = "SELECT * FROM orders WHERE id=? ORDER BY datetime_purchase DESC";
		Object[] parametriQuery = new Object[]{id};
		Order o = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		if(o!=null) {
			OrderProduct[] op = orderProductDAO.getOrderProductsByIdOrder(o.getId());
			ArrayList<OrderProduct> opList = new ArrayList<>(Arrays.asList(op));
			o.setOrderProducts(opList);
		}
		return o;
	}

	@Override
	public Order[] getOrdersSeller(int idSeller) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Order[] getOrdersShop(int idShop, int idSeller) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Order getOrderSeller(int id, int idSeller) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	/*
	@Override
	public Order[] getOrdersByIdUser(int idUser) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM orders WHERE id_user=? ORDER BY datetime_purchase DESC")) {
			ps.setInt(1, idUser);
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Order> orders_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
				while (rs.next()) {
					Order o = new Order(
							rs.getInt("id"),
							rs.getInt("id_user"),
							rs.getInt("id_shop"),
							rs.getTimestamp("datetime_purchase")
					);
					OrderProduct[] op = getOrderProductsByIdOrder(o.getId());
					ArrayList<OrderProduct> opList = new ArrayList<>(Arrays.asList(op));
					o.setOrderProducts(opList);
					orders_temp.add(o);
				}
				Order[] orders = new Order[orders_temp.size()];
				orders = orders_temp.toArray(orders); //trasforma arrayList in un array statico
				return orders;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getOrdersByIdUser: " + ex.getMessage(), ex);
		}
	}
	*/

	/*
	private OrderProduct[] getOrderProductsByIdOrder(int idOrder) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM orders_products WHERE id_order=?")) {
			ps.setInt(1, idOrder);
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<OrderProduct> orderProducts_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
				while (rs.next()) {
					OrderProduct o = new OrderProduct(
							rs.getInt("id_order"),
							rs.getInt("id_product"),
							rs.getFloat("price"),
							rs.getInt("quantity")
					);
					orderProducts_temp.add(o);
				}
				OrderProduct[] orderProducts = new OrderProduct[orderProducts_temp.size()];
				orderProducts = orderProducts_temp.toArray(orderProducts); //trasforma arrayList in un array statico
				return orderProducts;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getOrderProductsByIdOrder: " + ex.getMessage(), ex);
		}
	}
	*/

	@Override
	public boolean buyCart(Cart cart) throws DAOException {
		if (cart == null) {
			throw new DAOException("Cart è null", new NullPointerException("Cart è null"));
		}
		try {
			CON.setAutoCommit(false);
			for (Order o : cart.getOrders()) {
				insertOrder(o);
			}
			CON.commit();
			return true;
		} catch (DAOException ex) {
			try {
				System.err.println("Transaction is being rolled back");
				CON.rollback();
			} catch (SQLException exc) {
				throw new DAOException("Errore SQLException durante il rollback di saveCart: " + exc.getMessage());
			}
			throw new DAOException("Errore DAOException saveCart: " + ex.getMessage());
		} catch (SQLException ex) {
			try {
				System.err.println("Transaction is being rolled back");
				CON.rollback();
			} catch (SQLException exc) {
				throw new DAOException("Errore SQLException durante il rollback di saveCart: " + exc.getMessage());
			}
			throw new DAOException("Errore SQLException saveCart: " + ex.getMessage());
		} finally {
			try {
				CON.setAutoCommit(true);
			} catch (SQLException ex) {
				throw new DAOException("Errore: impossibile risettare autoCommit a true: " + ex.getMessage());
			}
		}
	}

	private boolean insertOrder(Order order) throws DAOException {
		/*
		if ((order.getIdShop() == -1) || (order.getIdUser() == -1)) {
			throw new DAOException("", new NullPointerException("Username, email, password, firstName o lastName sono null"));
		}
		 */
		//id order e datetime_purchase sono generati dal database
		String query = "INSERT INTO orders(id_user, id_shop) VALUES (?, ?)";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setInt(1, order.getIdUser());
			ps.setInt(2, order.getIdShop());

			int result = -1; //quantità di righe modificate dalla query insert
			try {
				result = ps.executeUpdate();
				aggiornaIdOrder(order); //imposta alla variabile order e agli orderProducts, l'id di order che è stato appena generato dal database
				for (OrderProduct op : order.getOrderProducts()) {
					decreaseShopProduct(op, order);
					insertOrderProduct(op);
				}
			} catch (SQLException ex) {
				//System.err.println("Impossibile eseguire query: " + ex.getMessage());
				throw new DAOException("Errore SQLException esecuzione query insertOrder: " + ex.getMessage());
			}
			boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException in insertOrder: " + ex.getMessage());
		}
	}

	//diminuisce la quantità del prodotto in vendita nel negozio
	private boolean decreaseShopProduct(OrderProduct orderProduct, Order order) throws DAOException {
		String query = "UPDATE shops_products SET quantity = quantity-? WHERE id_product=? AND id_shop=?;";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setInt(1, orderProduct.getQuantity());
			ps.setInt(2, orderProduct.getIdProduct());
			ps.setInt(3, order.getIdShop());

			int result = -1; //quantità di righe modificate dalla query insert
			try {
				result = ps.executeUpdate();
			} catch (SQLException ex) {
				//System.err.println("Impossibile eseguire query: " + ex.getMessage());
				throw new DAOException("Errore SQLException esecuzione query decreaseShopProduct: " + ex.getMessage());
			}
			boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLExcpetion in decreaseShopProduct: " + ex.getMessage());
		}
	}

	private boolean insertOrderProduct(OrderProduct orderProduct) throws DAOException {
		//id order e datetime_purchase sono generati dal database
		String query = "INSERT INTO orders_products(id_order, id_product, price, quantity) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setInt(1, orderProduct.getIdOrder());
			ps.setInt(2, orderProduct.getIdProduct());
			ps.setFloat(3, orderProduct.getPrice());
			ps.setInt(4, orderProduct.getQuantity());

			int result = -1; //quantità di righe modificate dalla query insert
			try {
				result = ps.executeUpdate();
			} catch (SQLException ex) {
				//System.err.println("Impossibile eseguire query: " + ex.getMessage());
				throw new DAOException("Errore SQLException esecuzione query insertOrderProduct: " + ex.getMessage());
			}
			boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLExcpetion in insertOrderProduct: " + ex.getMessage());
		}
	}

	//aggiorna l'id di order e di tutti gli orderproducts.
	//serve perchè quando inserisco un nuovo ordine, il database genera l'id dell'ordine, che mi serve per inserire nel database gli OrderProduct
	private void aggiornaIdOrder(Order order) throws DAOException {
		int orderId = getLastOrderId(order.getIdUser());
		order.setId(orderId);
		for (OrderProduct op : order.getOrderProducts()) {
			op.setIdOrder(orderId);
		}
	}
	
	//restituisce l'id dell'ultimo ordine inserito da un utente.
	//serve per conoscere l'id che il database ha assegnato all'ordine appena inserito
	private int getLastOrderId(int idUser) throws DAOException {
		String query = "SELECT * FROM orders WHERE id_user=? ORDER BY datetime_purchase DESC LIMIT 1";
		Object[] parametriQuery = new Object[]{idUser};
		Order order = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return order.getId();
	}

}
