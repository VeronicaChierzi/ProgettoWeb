package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.CategoryContainerDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.categories.Category;
import it.unitn.disi.entities.categories.CategoryContainer;
import it.unitn.disi.entities.categories.Subcategory;
import it.unitn.disi.entities.locations.Comune;
import it.unitn.disi.entities.locations.Location;
import it.unitn.disi.entities.locations.Provincia;
import it.unitn.disi.entities.locations.Regione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM categories ORDER BY name ASC")) {
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Category> categories_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
				while (rs.next()) {
					Category c = new Category(
							rs.getInt("id"),
							rs.getString("name")
					);
					categories_temp.add(c);
				}
				Category[] categories = new Category[categories_temp.size()];
				categories = categories_temp.toArray(categories); //trasforma arrayList in un array statico
				return categories;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException in getCategories", ex);
		}
	}

	private Subcategory[] getSubcategoriesByIdCategory(int idCategory, Category category) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM subcategories WHERE id_category = ? ORDER BY name ASC")) {
			ps.setInt(1, idCategory);
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Subcategory> subcategories_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
				while (rs.next()) {
					Subcategory p = new Subcategory(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("id_category"),
							category
					);
					subcategories_temp.add(p);
				}
				Subcategory[] subcategories = new Subcategory[subcategories_temp.size()];
				subcategories = subcategories_temp.toArray(subcategories); //trasforma arrayList in un array statico
				return subcategories;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException in getSubcategoriesByIdCategory", ex);
		}
	}
}
