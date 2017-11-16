package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Shop;
import it.unitn.disi.entities.ShopProduct;
import java.util.List;

public interface ShopProductDAO {

	public List<Shop> getAllShopsThatSellsAProduct(int prodId) throws DAOException;

	public ShopProduct getShopProduct(int idProduct, int idShop) throws DAOException;

	public ShopProduct getMinShopProduct(int idProduct) throws DAOException;

}
