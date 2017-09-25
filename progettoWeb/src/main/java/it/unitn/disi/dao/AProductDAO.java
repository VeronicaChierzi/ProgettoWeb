package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;

public interface AProductDAO extends DAO<Product, Integer> {

	public Product getProductByID(int id) throws DAOException;

	public boolean insertProduct(int name, int description) throws DAOException;

}
