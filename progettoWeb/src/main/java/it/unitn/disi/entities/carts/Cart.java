package it.unitn.disi.entities.carts;

import it.unitn.disi.entities.orders.Order;
import java.util.ArrayList;

public class Cart {
	
	private int idUser;
	private ArrayList<Order> orders;

	public Cart(int idUser) {
		this.idUser = idUser;
		orders = new ArrayList<>();
	}

	public float getTotalPrice() {
		float totalPrice = 0.0f;
		for (Order o : orders) {
			totalPrice += o.getTotalPrice();
		}
		return totalPrice;
	}
	
	// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	/**
	 * @return the orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	// </editor-fold>
	
}
