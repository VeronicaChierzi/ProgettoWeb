package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import java.util.List;

public interface ProductDAO extends DAO<Product, Integer> {

	public Product[] getProducts() throws DAOException;

	public Product getProductByID(int id) throws DAOException;

	public boolean insertProduct(int name, int description) throws DAOException;
	
        public List<Product> searchProducts(String text) throws DAOException;
        
}
