package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.orders.OrderProduct;

public interface OrderProductDAO extends DAO<OrderProduct, Integer> {

	public OrderProduct[] getOrderProductsByIdOrder(int idOrder) throws DAOException;
	
}