package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.carts.Cart;
import it.unitn.disi.entities.orders.Order;

public interface OrderDAO extends DAO<Order, Integer> {

	public Order[] getOrdersUser(int idUser) throws DAOException;
	public Order getOrderUser(int id, int idUser) throws DAOException;

	public Order[] getOrdersSeller(int idSeller) throws DAOException;	//tutti gli ordini di un venditore
	public Order[] getOrdersShop(int idShop, int idSeller) throws DAOException; //tutti gli ordini di un punto vendita (appartenente a un venditore (da controllare per sicurezza che appartenga al venditore))
	public Order getOrderSeller(int id, int idSeller) throws DAOException; //dettagli di un ordine di un venditore (raggiungibile sia da OrdersSeller che da OrdersShop)

	public boolean buyCart(Cart cart) throws DAOException;	
	
}
