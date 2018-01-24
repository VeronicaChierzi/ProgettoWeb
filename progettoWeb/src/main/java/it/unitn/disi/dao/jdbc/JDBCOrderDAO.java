package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.OrderProductDAO;
import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.Product;
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
	private UserDAO userDAO;
	private ShopDAO shopDAO;

	public JDBCOrderDAO(Connection con) {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		orderProductDAO = (OrderProductDAO) initDao(OrderProductDAO.class, daoFactory);
		shopDAO = (ShopDAO) initDao(ShopDAO.class, daoFactory);
	}

	private void setPointers(Order o, boolean loadOrdersProducts, boolean loadUser, boolean loadShop) throws DAOException {
		if (o != null) {
			if (loadOrdersProducts) {
				OrderProduct[] op = orderProductDAO.getOrderProductsByIdOrder(o.getId());
				ArrayList<OrderProduct> opList = new ArrayList<>(Arrays.asList(op));
				o.setOrderProducts(opList);
			}
			if (loadUser) {
				o.setUser(userDAO.getUser(o.getIdUser()));
			}
			if (loadShop) {
				o.setShop(shopDAO.getShop(o.getIdShop(), true, true));
			}
		}
	}

	//usato in segnalazione, per prendere l'ordine, per prendere l'utente e il negozio per prendere il venditore
	@Override
	public Order getOrder(int id) throws DAOException {
		String query = "SELECT * FROM orders WHERE id=?";
		Object[] parametriQuery = new Object[]{id};
		Order o = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		if (o != null) {
			setPointers(o, true, true, true);
		}
		return o;
	}

	//dettagli di un ordine di un utente. idUser usato per garantire sicurezza(solo l'utente che ha effettuato l'ordine deve poter visualizzarlo).
	@Override
	public Order getOrderUser(int id, int idUser) throws DAOException {
		String query = "SELECT * FROM orders WHERE id=? AND id_user=?";
		Object[] parametriQuery = new Object[]{id, idUser};
		Order o = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		if (o != null) {
			setPointers(o, true, false, true);
		}
		return o;
	}

	//tutti gli ordini di un utente
	@Override
	public Order[] getOrdersUser(int idUser) throws DAOException {
		String query = "SELECT * FROM orders WHERE id_user=? ORDER BY id DESC";
		Object[] parametriQuery = new Object[]{idUser};
		Order[] orders = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Order o : orders) {
			setPointers(o, true, false, true);
		}
		return orders;
	}

	//dettagli di un ordine di un venditore (raggiungibile sia da OrdersSeller che da OrdersShop). idSeller usato per garantire sicurezza(solo il venditore cheha ricevuto l'ordine deve poter visualizzarlo).
	@Override
	public Order getOrderSeller(int id, int idSeller) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	//tutti gli ordini di un venditore
	@Override
	public Order[] getOrdersSeller(int idSeller) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	//tutti gli ordini di un punto vendita (appartenente a un venditore (da controllare per sicurezza che appartenga al venditore))
	@Override
	public Order[] getOrdersShop(int idShop, int idSeller) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	// <editor-fold defaultstate="collapsed" desc="BuyCart e funzioni per completare l'acquisto">
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
		String query = "INSERT INTO orders(id_user, id_shop, datetime_purchase) VALUES (?, ?, ?)";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setInt(1, order.getIdUser());
			ps.setInt(2, order.getIdShop());
                        ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

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
		order.getIdShop();
		int orderId = getLastOrderId(order);
		order.setId(orderId);
		for (OrderProduct op : order.getOrderProducts()) {
			op.setIdOrder(orderId);
		}
	}

	//restituisce l'id dell'ultimo ordine inserito da un utente.
	//serve per conoscere l'id che il database ha assegnato all'ordine appena inserito
	private int getLastOrderId(Order o) throws DAOException {
		int idUser = o.getIdUser();
		int idShop = o.getIdShop();
		String query = "SELECT * FROM orders WHERE id_user=? AND id_shop=? ORDER BY datetime_purchase DESC LIMIT 1";
		Object[] parametriQuery = new Object[]{idUser, idShop};
		Order order = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return order.getId();
	}
	// </editor-fold>

    @Override
    public int hasUserBought(int userId, int prodId) throws DAOException {
        Order[] o = getOrdersUser(userId);
        for(int i = 0; i < o.length; i++) {
            for(OrderProduct p : o[i].getOrderProducts()) {
                if(p.getIdProduct() == prodId) {
                    return p.getIdOrder();
                }
            }
        }
        return -1;
        
    }

}
