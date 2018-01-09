package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.carts.Cart;
import it.unitn.disi.entities.orders.Order;

public interface OrderDAO extends DAO<Order, Integer> {

	public Order getOrder(int id) throws DAOException; //usato in segnalazione, per prendere l'ordine, per prendere l'utente e il negozio per prendere il venditore
	
	public Order getOrderUser(int id, int idUser) throws DAOException;	//dettagli di un ordine di un utente. idUser usato per garantire sicurezza(solo l'utente che ha effettuato l'ordine deve poter visualizzarlo).
	public Order[] getOrdersUser(int idUser) throws DAOException;	//tutti gli ordini di un utente

	public Order getOrderSeller(int id, int idSeller) throws DAOException; //dettagli di un ordine di un venditore (raggiungibile sia da OrdersSeller che da OrdersShop). idSeller usato per garantire sicurezza(solo il venditore cheha ricevuto l'ordine deve poter visualizzarlo).
	public Order[] getOrdersSeller(int idSeller) throws DAOException;	//tutti gli ordini di un venditore
	public Order[] getOrdersShop(int idShop, int idSeller) throws DAOException; //tutti gli ordini di un punto vendita (appartenente a un venditore (da controllare per sicurezza che appartenga al venditore))

	public boolean buyCart(Cart cart) throws DAOException;	
	
}
