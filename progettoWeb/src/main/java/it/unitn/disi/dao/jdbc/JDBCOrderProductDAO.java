package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.OrderProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.SegnalazioneRisposta;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.entities.orders.OrderProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCOrderProductDAO extends JDBCDAO<OrderProduct, Integer> implements OrderProductDAO {

	public JDBCOrderProductDAO(Connection con) {
		super(con);
	}

	private static final Class classe = OrderProduct.class;
	private static final String[] nomiColonne = new String[]{"id_order", "id_product", "price", "quantity"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, float.class, int.class};

	@Override
	public OrderProduct[] getOrderProductsByIdOrder(int idOrder) throws DAOException {
		String query = "SELECT * FROM orders_products WHERE id_order=?";
		Object[] parametriQuery = new Object[]{idOrder};
		OrderProduct[] op = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return op;
	}
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
	
}
