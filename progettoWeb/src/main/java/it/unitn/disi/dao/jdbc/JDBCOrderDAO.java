package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.entities.orders.OrderProduct;
import it.unitn.disi.entities.carts.Cart;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCOrderDAO extends JDBCDAO<Order, Integer> implements OrderDAO {

	public JDBCOrderDAO(Connection con) {
		super(con);
	}

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

	@Override
	public Order getOrderByID(int id) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

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
			CON.setAutoCommit(true);
			return true;
		} catch (DAOException ex) {
			throw new DAOException("Errore saveCart: " + ex.getMessage());
		} catch (SQLException ex) {
			//CON.rollback();
			throw new DAOException("Errore saveCart: " + ex.getMessage());
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
				for (OrderProduct op : order.getOrderProducts()) {
					insertOrderProduct(op);
				}
			} catch (SQLException ex) {
				//System.err.println("Impossibile eseguire query: " + ex.getMessage());
				throw new DAOException("Errore esecuzione query insertOrder: " + ex.getMessage());
			}
			boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Errore sqlexcpetion in insertOrder: " + ex.getMessage());
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
				throw new DAOException("Errore esecuzione query insertOrderProduct: " + ex.getMessage());
			}
			boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Errore sqlexcpetion in insertOrderProduct: " + ex.getMessage());
		}
	}

}
