package it.unitn.disi.entities.categories;

public class Subcategory {
	
	private int id;
	private String name;
	private int idCategory;
	
	private Category category;

	public Subcategory(int id, String name, int idCategory) {
		this.id = id;
		this.name = name;
		this.idCategory = idCategory;
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
	 * @return the idCategory
	 */
	public int getIdCategory() {
		return idCategory;
	}

	/**
	 * @param idCategory the idCategory to set
	 */
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	// </editor-fold>
	
}
