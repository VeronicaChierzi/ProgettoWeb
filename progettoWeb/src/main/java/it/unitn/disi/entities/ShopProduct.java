package it.unitn.disi.entities;

public class ShopProduct {

	private int idProduct;
	private int idShop;
	private float price;
	private int quantity;
	
	private Product product;
	private Shop shop;
	
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

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
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
