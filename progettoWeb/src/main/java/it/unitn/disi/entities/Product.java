package it.unitn.disi.entities;

public class Product {

	private int id;
	private String name;
	private String description;
	
	private float priceMin;
	// categoria ???

	public Product() {
	}

	public Product(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		//this.price = price;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
		/**
	 * @return the priceMin
	 */
	public float getPriceMin() {
		return priceMin;
	}

	/**
	 * @param priceMin the priceMin to set
	 */
	public void setPriceMin(float priceMin) {
		this.priceMin = priceMin;
	}
	// </editor-fold>

}
