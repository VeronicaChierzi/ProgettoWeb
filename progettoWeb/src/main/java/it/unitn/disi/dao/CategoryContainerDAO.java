package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.categories.CategoryContainer;

public interface CategoryContainerDAO extends DAO<CategoryContainer, Integer> {

	public CategoryContainer getCategoryContainer() throws DAOException;

}
