package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;

public interface UserDAO extends DAO<User, Integer> {

	public User getByUsernameEmailAndPassword(String usernameEmail, String password) throws DAOException;

	public User getByUsernameAndPassword(String username, String password) throws DAOException;

	public User getByEmailAndPassword(String username, String password) throws DAOException;

	public boolean insertUser(String username, String email, String password, String firstName, String lastName, String userHash) throws DAOException;

	public boolean insertUserSeller(int idUser, String name, String partitaIva) throws DAOException;

	public boolean confirmUser(String hash, String usn) throws DAOException;

	public boolean existsUser(String email) throws DAOException;

	public User getByEmail(String email) throws DAOException;

	public boolean changePassword(String email, String password, String userHash) throws DAOException;

	public boolean setNewPassword(int userID, String nuovaPassword) throws DAOException;

	public boolean setNewUserHash(int userId, String userHash) throws DAOException;

	public boolean hasRightHash(String email, String userHash) throws DAOException;
	
	public User getUser(int id) throws DAOException;

}
