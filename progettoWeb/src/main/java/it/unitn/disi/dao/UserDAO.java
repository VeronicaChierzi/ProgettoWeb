package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;

public interface UserDAO extends DAO<User, Integer> {

	public User getByUsernameAndPassword(String username, String password) throws DAOException;

	public boolean insertUser(String username, String email, String password, String firstName, String lastName) throws DAOException;

}
