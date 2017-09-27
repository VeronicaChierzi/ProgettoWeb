package it.unitn.disi.entities.categories;

public class Category {
	
	private int id;
	private String name;
	
	private Subcategory[] subcategories;
	
	public Category() {
	}

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
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
	 * @return the subcategories
	 */
	public Subcategory[] getSubcategories() {
		return subcategories;
	}

	/**
	 * @param subcategories the subcategories to set
	 */
	public void setSubcategories(Subcategory[] subcategories) {
		this.subcategories = subcategories;
	}
	// </editor-fold>
	
}
