package it.unitn.disi.entities.orders;

import it.unitn.disi.entities.Shop;
import it.unitn.disi.entities.User;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {

	private int id;
	private int idUser;
	private int idShop;
	private Timestamp datetimePurchase;

	private ArrayList<OrderProduct> orderProducts;
	private User user;
	private Shop shop;

	public Order() {
		orderProducts = new ArrayList<>();
	}

	public Order(int id, int idUser, int idShop, Timestamp datetimePurchase) {
		this.id = id;
		this.idUser = idUser;
		this.idShop = idShop;
		this.datetimePurchase = datetimePurchase;
		orderProducts = new ArrayList<>();
	}

	public float getTotalPrice() {
		float totalPrice = 0.0f;
		for (OrderProduct op : orderProducts) {
			totalPrice += op.getTotalPrice();
		}
		return totalPrice;
	}

	// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @return the idShop
	 */
	public int getIdShop() {
		return idShop;
	}

	/**
	 * @param idShop the idShop to set
	 */
	public void setIdShop(int idShop) {
		this.idShop = idShop;
	}

	/**
	 * @return the datetimePurchase
	 */
	public Timestamp getDatetimePurchase() {
		return datetimePurchase;
	}

	/**
	 * @param datetimePurchase the datetimePurchase to set
	 */
	public void setDatetimePurchase(Timestamp datetimePurchase) {
		this.datetimePurchase = datetimePurchase;
	}

	/**
	 * @return the orderProducts
	 */
	public ArrayList<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	/**
	 * @param orderProducts the orderProducts to set
	 */
	public void setOrderProducts(ArrayList<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the shop
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * @param shop the shop to set
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	// </editor-fold>

}
