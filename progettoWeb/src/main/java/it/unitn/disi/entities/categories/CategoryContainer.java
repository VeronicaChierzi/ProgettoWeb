package it.unitn.disi.entities.categories;

import java.util.HashMap;

public class CategoryContainer {
	
	private final Category[] categories;
	private final HashMap<Integer, Category> categoriesHash;
	private final HashMap<Integer, Subcategory> subcategoriesHash;

	public CategoryContainer(Category[] categories, HashMap<Integer, Category> categoriesHash, HashMap<Integer, Subcategory> subcategoriesHash) {
		this.categories = categories;
		this.categoriesHash = categoriesHash;
		this.subcategoriesHash = subcategoriesHash;
	}

	// <editor-fold defaultstate="collapsed" desc="Getters custom">
	public Category getCategory(int idCategory) {
		return categoriesHash.get(idCategory);
	}

	public Subcategory getSubcategory(int idSubcategory) {
		return subcategoriesHash.get(idSubcategory);
	}

	public Category[] getCategories() {
		return categories;
	}

	public Subcategory[] getSubcategoriesByIdCategory(int idCategory) {
		return getCategory(idCategory).getSubcategories();
	}
	// </editor-fold>
}
