package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import it.unitn.disi.entities.ShopProduct;

public interface ShopProductDAO extends DAO<ShopProduct, Integer> {

	public ShopProduct getShopProduct(int idProduct, int idShop, boolean loadProduct, boolean loadShop) throws DAOException;

	public ShopProduct getMinShopProduct(int idProduct, Product product) throws DAOException;
	
	public ShopProduct[] getShopsProductsByIdProduct(int idProduct, boolean loadProduct, boolean loadShop) throws DAOException;

	public ShopProduct[] getShopsProductsByIdProduct(int idProduct) throws DAOException;

	public ShopProduct[] getShopsProductsByIdShop(int idShop, boolean loadProduct, boolean loadShop) throws DAOException;

	public ShopProduct getShopProductByProduct(int idProduct, int idShop, Product product) throws DAOException;

}
