package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;

public interface ProductDAO extends DAO<Product, Integer> {

	public Product getProduct(int id) throws DAOException;
	
	public Product[] searchProducts(String text) throws DAOException;

}
