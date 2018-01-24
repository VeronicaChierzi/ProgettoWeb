package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;

public interface ProductDAO extends DAO<Product, Integer> {

	public Product getProduct(int id, boolean loadMinShopProduct, boolean loadImage, boolean loadReview) throws DAOException;
	
	public Product[] searchProducts(String text, int offset) throws DAOException;
        
        public Product[] getProductsByCategory(int cat, int offset) throws DAOException;
        
        public Product[] searchProductsByCategory(String text, int cat, int offset) throws DAOException;
        
        public Product[] searchProductsByCategoryAndRating(String text, int cat, int offset, int rating) throws DAOException;
        
        public Product[] searchProductsByRating(String text, int offset, int rating) throws DAOException;
        
}
