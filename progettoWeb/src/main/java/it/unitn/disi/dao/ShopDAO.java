package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Shop;
import java.util.List;

public interface ShopDAO extends DAO<Shop, Integer> {

	public Shop getShop(int idShop, boolean loadUserSeller, boolean loadComune) throws DAOException;

	public Shop[] getShops() throws DAOException;

	public List<Shop> getAllShopsThatSellsAProduct(int prodId) throws DAOException;

	public Shop[] getShopsByUserSeller(int idUser) throws DAOException;

	public boolean changeOrarioShop(int idShop, String orario) throws DAOException;

	public boolean changeRitiroInNegozio(int idShop, boolean ritiroInNegozio) throws DAOException;

}
