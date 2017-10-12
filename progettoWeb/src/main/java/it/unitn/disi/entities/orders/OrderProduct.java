package it.unitn.disi.entities.orders;

public class OrderProduct {
	
	private int idOrder;
	private int idProduct;
	private float price;
	private int quantity;

	public OrderProduct() {
	}

	public OrderProduct(int idOrder, int idProduct, float price, int quantity) {
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.price = price;
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return price*quantity;
	}

	
	// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
	/**
	 * @return the idOrder
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * @param idOrder the idOrder to set
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

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
