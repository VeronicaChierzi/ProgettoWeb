package it.unitn.disi.entities.carts;

import it.unitn.disi.entities.orders.Order;
import java.util.ArrayList;

public class Cart {

	private int idUser;
	private ArrayList<Order> orders;
	
	private CartItem[] cartItems;

	public Cart(int idUser) {
		this.idUser = idUser;
		orders = new ArrayList<>();
	}

	public float getTotalPriceRitiro() {
		float totalPrice = 0.0f;
		for (Order o : orders) {
			if(!o.isSpedizione()){
				totalPrice += o.getTotalPrice();
			}
		}
		return totalPrice;
	}

	public float getTotalPriceSpedizione() {
		float totalPrice = 0.0f;
		for (Order o : orders) {
			if(o.isSpedizione()){
				totalPrice += o.getTotalPrice();
			}
		}
		return totalPrice;
	}

	public float getTotalPrice() {
		float totalPrice = 0.0f;
		for (Order o : orders) {
			totalPrice += o.getTotalPrice();
		}
		return totalPrice;
	}
	
	public boolean isEmpty(){
		return (orders.isEmpty());
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
	
	/**
	 * @return the cartItems
	 */
	public CartItem[] getCartItems() {
		return cartItems;
	}

	/**
	 * @param cartItems the cartItems to set
	 */
	public void setCartItems(CartItem[] cartItems) {
		this.cartItems = cartItems;
	}
	// </editor-fold>
	
}
