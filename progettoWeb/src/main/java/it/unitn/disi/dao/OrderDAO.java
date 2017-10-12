package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.carts.Cart;
import it.unitn.disi.entities.orders.Order;

public interface OrderDAO extends DAO<Order, Integer> {

	public Order[] getOrdersByIdUser(int idUser) throws DAOException;

	public Order getOrderByID(int id) throws DAOException;

	public boolean buyCart(Cart cart) throws DAOException;
	
}
