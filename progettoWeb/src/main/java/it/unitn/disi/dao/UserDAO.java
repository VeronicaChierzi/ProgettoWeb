package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.UserSeller;

public interface UserDAO extends DAO<User, Integer> {

	public User getByUsernameAndPassword(String username, String password) throws DAOException;
        
        public User getByEmailAndPassword(String username, String password) throws DAOException;
        
	public boolean insertUser(String username, String email, String password, String firstName, String lastName, String userHash) throws DAOException;

	public boolean insertUserSeller(int idUser, String name, String partitaIva) throws DAOException;

	public UserSeller getUserSeller(int idUser) throws DAOException;
        
        public boolean confirmUser(String hash, String usn) throws DAOException;

}
