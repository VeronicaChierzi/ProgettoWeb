package it.unitn.disi.entities.carts;

import it.unitn.disi.entities.ShopProduct;

public class CartItem {

	private int quantity;
	private ShopProduct shopProduct;

	public CartItem(int quantity, ShopProduct shopProduct) {
		this.quantity = quantity;
		this.shopProduct = shopProduct;
	}

	public float getTotalPrice() {
		if (getShopProduct() != null) {
			return getShopProduct().getPrice() * getQuantity();
		} else {
			return 0.0f;
		}
	}

	// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
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
	 * @return the shopProduct
	 */
	public ShopProduct getShopProduct() {
		return shopProduct;
	}

	/**
	 * @param shopProduct the shopProduct to set
	 */
	public void setShopProduct(ShopProduct shopProduct) {
		this.shopProduct = shopProduct;
	}
	// </editor-fold>

}
