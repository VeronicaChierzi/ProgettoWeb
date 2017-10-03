package it.unitn.disi.entities;

public class ShopProduct {

	private int idProduct;
	private int idShop;
	private float price;
	private int quantity;

	public ShopProduct() {
	}

	public ShopProduct(int idProduct, int idShop, float price, int quantity) {
		this.idProduct = idProduct;
		this.idShop = idShop;
		this.price = price;
		this.quantity = quantity;
	}

	// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
	/**
	 * @return the idProduct
	 */
	public int getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
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
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	// </editor-fold>

}
