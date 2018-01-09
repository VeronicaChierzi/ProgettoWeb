package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.UserAdmin;

public interface UserAdminDAO extends DAO<UserAdmin, Integer> {

	public UserAdmin getUserAdmin(int idUser) throws DAOException;

}
