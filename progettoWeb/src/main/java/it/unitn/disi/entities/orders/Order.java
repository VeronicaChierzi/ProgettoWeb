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
	private boolean spedizione;
	private boolean concluso;
	private Timestamp datetimeConcluso;

	private ArrayList<OrderProduct> orderProducts;
	private User user;
	private Shop shop;

	public Order() {
		orderProducts = new ArrayList<>();
	}

	public Order(int id, int idUser, int idShop, Timestamp datetimePurchase, boolean spedizione, boolean concluso, Timestamp datetimeConcluso) {
		this.id = id;
		this.idUser = idUser;
		this.idShop = idShop;
		this.datetimePurchase = datetimePurchase;
		this.spedizione = spedizione;
		this.concluso = concluso;
		this.datetimeConcluso = datetimeConcluso;
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
	/**
	 * @return the spedizione
	 */
	public boolean isSpedizione() {
		return spedizione;
	}

	/**
	 * @param spedizione the spedizione to set
	 */
	public void setSpedizione(boolean spedizione) {
		this.spedizione = spedizione;
	}

	/**
	 * @return the concluso
	 */
	public boolean isConcluso() {
		return concluso;
	}

	/**
	 * @param concluso the concluso to set
	 */
	public void setConcluso(boolean concluso) {
		this.concluso = concluso;
	}

	/**
	 * @return the datetimeConcluso
	 */
	public Timestamp getDatetimeConcluso() {
		return datetimeConcluso;
	}

	/**
	 * @param datetimeConcluso the datetimeConcluso to set
	 */
	public void setDatetimeConcluso(Timestamp datetimeConcluso) {
		this.datetimeConcluso = datetimeConcluso;
	}
	// </editor-fold>

}
