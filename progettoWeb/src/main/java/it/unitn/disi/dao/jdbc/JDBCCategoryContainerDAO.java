package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.CategoryContainerDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.categories.Category;
import it.unitn.disi.entities.categories.CategoryContainer;
import it.unitn.disi.entities.categories.Subcategory;
import java.sql.Connection;
import java.util.HashMap;

public class JDBCCategoryContainerDAO extends JDBCDAO<CategoryContainer, Integer> implements CategoryContainerDAO {

	private static final int ESTIMATED_SIZE_CATEGORIES = 10;
	private static final int ESTIMATED_SIZE_SUBCATEGORIES = 100;
	private static final int HASHMAP_INITIAL_CAPACITY_CATEGORIES = (int) (ESTIMATED_SIZE_CATEGORIES / 0.75f) + 1;
	private static final int HASHMAP_INITIAL_CAPACITY_SUBCATEGORIES = (int) (ESTIMATED_SIZE_SUBCATEGORIES / 0.75f) + 1;

	public JDBCCategoryContainerDAO(Connection con) {
		super(con);
	}

	@Override
	public CategoryContainer getCategoryContainer() throws DAOException {
		Category[] categories;
		HashMap<Integer, Category> categoriesHash = new HashMap<>(HASHMAP_INITIAL_CAPACITY_CATEGORIES);
		HashMap<Integer, Subcategory> subcategoriesHash = new HashMap<>(HASHMAP_INITIAL_CAPACITY_SUBCATEGORIES);

		//per ogni categoria, imposta l'array delle sottocategorie e aggiunge la categoria all'hashmap
		//per ogni sottocategoria, la aggiunge all'hashmap
		categories = getCategories();
		for (Category c : categories) {
			Subcategory[] subcategories = getSubcategoriesByIdCategory(c.getId(), c);
			c.setSubcategories(subcategories);
			categoriesHash.put(c.getId(), c);
			for (Subcategory s : subcategories) {
				subcategoriesHash.put(s.getId(), s);
			}
		}
		CategoryContainer cc = new CategoryContainer(categories, categoriesHash, subcategoriesHash);
		return cc;
	}

	private Category[] getCategories() throws DAOException {
		String query = "SELECT * FROM categories ORDER BY name ASC";
		Object[] parametriQuery = new Object[]{};
		Class classe = Category.class;
		String[] nomiColonne = new String[]{"id", "name"};
		Class[] constructorParameterTypes = new Class[]{int.class, String.class};
		Category[] categories = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return categories;
	}

	private Subcategory[] getSubcategoriesByIdCategory(int idCategory, Category category) throws DAOException {
		String query = "SELECT * FROM subcategories WHERE id_category = ? ORDER BY name ASC";
		Object[] parametriQuery = new Object[]{idCategory};
		Class classe = Subcategory.class;
		String[] nomiColonne = new String[]{"id", "name", "id_category"};
		Class[] constructorParameterTypes = new Class[]{int.class, String.class, int.class};
		Subcategory[] subcategories = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Subcategory s : subcategories) {
			s.setCategory(category);
		}
		return subcategories;
	}

}
