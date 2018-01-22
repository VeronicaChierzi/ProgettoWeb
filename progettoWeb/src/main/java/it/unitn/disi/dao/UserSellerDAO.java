package it.unitn.disi.dao;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.UserSeller;

public interface UserSellerDAO extends DAO<UserSeller, Integer> {

	public UserSeller getUserSeller(int idUser) throws DAOException;
	
	public boolean insertUserSeller(int idUser, String name, String partitaIva) throws DAOException;
	
}