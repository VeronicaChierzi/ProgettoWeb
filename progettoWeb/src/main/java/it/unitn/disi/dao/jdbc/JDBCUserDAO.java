package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.UserAdminDAO;
import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.UserSellerDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class JDBCUserDAO extends JDBCDAO<User, Integer> implements UserDAO {

	private static final Class classe = User.class;
	private static final String[] nomiColonne = new String[]{"id", "username", "email", "password", "first_name", "last_name", "user_hash", "verificato"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, String.class, String.class, String.class, String.class, String.class, String.class, boolean.class};

	private UserSellerDAO userSellerDAO;
	private UserAdminDAO userAdminDAO;

	public JDBCUserDAO(Connection con) {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		userSellerDAO = (UserSellerDAO) initDao(UserSellerDAO.class, daoFactory);
		userAdminDAO = (UserAdminDAO) initDao(UserAdminDAO.class, daoFactory);
	}

	private void setPointers(User u, boolean loadUserSeller, boolean loadUserAdmin) throws DAOException {
		if (u != null) {
			if (loadUserSeller) {
				u.setUserSeller(userSellerDAO.getUserSeller(u.getId()));
			}
			if (loadUserAdmin) {
				u.setUserAdmin(userAdminDAO.getUserAdmin(u.getId()));
			}
		}
	}
	
	@Override
	public User getByUsernameAndPassword(String username, String password) throws DAOException {
		if ((username == null) || (password == null)) {
			throw new DAOException("Username e password sono campi obbligatori", new NullPointerException("username or password are null"));
		}
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		Object[] parametriQuery = new Object[]{username, password};
		User u = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		setPointers(u, true, true);
		return u;
	}

	@Override
	public User getByEmailAndPassword(String email, String password) throws DAOException {
		if ((email == null) || (password == null)) {
			throw new DAOException("Email e password sono campi obbligatori", new NullPointerException("email or password are null"));
		}
		String query = "SELECT * FROM users WHERE email = ? AND password = ?";
		Object[] parametriQuery = new Object[]{email, password};
		User u = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		setPointers(u, true, true);
		return u;
	}

	@Override
	public User getByUsernameEmailAndPassword(String usernameEmail, String password) throws DAOException {
		if ((usernameEmail == null) || (password == null)) {
			throw new DAOException("Username/email e password sono campi obbligatori", new NullPointerException("username/email or password are null"));
		}
		String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
		Object[] parametriQuery = new Object[]{usernameEmail, usernameEmail, password};
		User u = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		setPointers(u, true, true);
		return u;
	}

	@Override
	public boolean insertUser(String username, String email, String password, String firstName, String lastName, String userHash) throws DAOException {
		if ((username == null) || (email == null) || (password == null) || (firstName == null) || (lastName == null) || (userHash == null)) {
			throw new DAOException("Username, email, password, firstName e lastName sono campi obbligatori", new NullPointerException("Username, email, password, firstName o lastName sono null"));
		}
		String query = "INSERT INTO users(id, username, email, password, first_name, last_name, user_hash, verificato) VALUES (nextval('users_id_seq'::regclass),?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, userHash);
			ps.setBoolean(7, false);

			int result = -1; //quantità di righe modificate dalla query insert
			try {
				result = ps.executeUpdate();
			} catch (SQLException ex) {
				//System.err.println("Impossibile eseguire query: " + ex.getMessage());
				throw new DAOException("Errore esecuzione query: " + ex);
			}
			boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Errore preparedStatement o sintassi query: " + ex);
		}
	}

	@Override
	public boolean insertUserSeller(int idUser, String name, String partitaIva) throws DAOException {
		if ((name == null) || (partitaIva == null)) {
			throw new DAOException("name e partitaIva sono campi obbligatori", new NullPointerException("name o partitaIva sono null"));
		}
		String query = "INSERT INTO users_sellers(id, name, partita_iva) VALUES (?, ?, ?)";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setInt(1, idUser);
			ps.setString(2, name);
			ps.setString(3, partitaIva);
			int result = -1; //quantità di righe modificate dalla query insert
			try {
				result = ps.executeUpdate();
			} catch (SQLException ex) {
				//System.err.println("Impossibile eseguire query: " + ex.getMessage());
				throw new DAOException("Errore esecuzione query insertUserSeller: " + ex);
			}
			boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query insertUserSeller: " + ex);
		}
	}

	@Override
	public boolean confirmUser(String hash, String usn) throws DAOException {
		if ((hash == null) || (usn == null)) {
			throw new DAOException("Hash e username sono campi obbligatori per confermare una registrazione", new NullPointerException("username or hash are null"));
		}
		try (PreparedStatement ps = CON.prepareStatement("UPDATE public.users SET verificato=true WHERE username = ? and user_hash = ?;")) {
			ps.setString(1, usn);
			ps.setString(2, hash);
			int result = -1;
			result = ps.executeUpdate();

			boolean b = (result == 1); //se ha modificato almeno 1 riga, restituisce true
			if (result > 1) {
				throw new InternalError("Trying to confirm 2 or more users");
			}
			return b;
		} catch (SQLException ex) {
			throw new DAOException("Errore query confirm User", ex);
		} finally {
			return false;
		}
	}

	@Override
	public boolean existsUser(String email) throws DAOException {
		String query = "SELECT * FROM users WHERE email ilike ?;";
		Object[] parametriQuery = new Object[]{email};
		User u = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		if (u != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean changePassword(String email, String password, String userHash) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("UPDATE public.users SET password= ? WHERE email = ? AND user_hash = ?;")) {
			ps.setString(1, password);
			ps.setString(2, email);
			ps.setString(3, userHash);
			int ris = ps.executeUpdate();
			if (ris == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			throw new DAOException("errore SQLException in query changePassword", ex);
		}

	}

	@Override
	public boolean setNewPassword(int userID, String nuovaPassword) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("UPDATE public.users SET password = ? WHERE id = ?;")) {
			ps.setString(1, nuovaPassword);
			ps.setInt(2, userID);
			int ris = ps.executeUpdate();
			if (ris == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			throw new DAOException("errore SQLException in query setNewPassword", ex);
		}
	}

	@Override
	public boolean setNewUserHash(int userID, String userHash) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("UPDATE public.users SET user_hash = ? WHERE id = ?;")) {
			ps.setString(1, userHash);
			ps.setInt(2, userID);
			int ris = ps.executeUpdate();
			if (ris == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			throw new DAOException("errore SQLException in query setNewUserHash", ex);
		}
	}

	@Override
	public User getByEmail(String email) throws DAOException {
		if (email == null) {
			throw new DAOException("Email è obbligatorio", new NullPointerException("email is null"));
		}
		String query = "SELECT * FROM users WHERE email = ?";
		Object[] parametriQuery = new Object[]{email};
		User u = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		setPointers(u, true, true);
		return u;
	}

	@Override
	public boolean hasRightHash(String email, String userHash) throws DAOException {
		User u = getByEmail(email);
		if (u.getHash().equalsIgnoreCase(userHash)) {
			return true;
		} else {
			return false;
		}
	}
	
	//funzione richiamata da Order
	@Override
	public User getUser(int id) throws DAOException {
                String query = "SELECT * FROM users WHERE id=?";
		Object[] parametriQuery = new Object[]{id};
		User u = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		u.setPassword("");
		setPointers(u, false, false);
		return u;
	}

}

// vecchie funzioni, da cancellare in futuro
/*
    @Override
    public User getByUsernameAndPassword(String username, String password) throws DAOException {
        if ((username == null) || (password == null)) {
            throw new DAOException("Username e password sono campi obbligatori", new NullPointerException("username or password are null"));
        }
        try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rsUser = ps.executeQuery()) {
                User user = null;
                if (rsUser.next()) {
                    user = new User(
                            rsUser.getInt("id"),
                            rsUser.getString("username"),
                            rsUser.getString("email"),
                            rsUser.getString("password"),
                            rsUser.getString("first_name"),
                            rsUser.getString("last_name"),
                            rsUser.getString("user_hash"),
                            rsUser.getBoolean("verificato")
                    );

                    //controlla se l'utente è anche un venditore
                    user.setUserSeller(userSellerDAO.getUserSeller(user.getId()));
                    //controlla se l'utente è anche un admin
                    user.setUserAdmin(userAdminDAO.getUserAdmin(user.getId()));
                }
                if (rsUser.next()) {
                    throw new DAOException("Errore: ci sono più utenti con lo stesso username");
                }
                return user;
            }
        } catch (SQLException ex) {
            throw new DAOException("Errore query getByUsernameAndPassword", ex);
        }
    }*/
 /*
    @Override
    public User getByEmailAndPassword(String username, String password) throws DAOException {
        if ((username == null) || (password == null)) {
            throw new DAOException("Username e password sono campi obbligatori", new NullPointerException("username or password are null"));
        }
        try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rsUser = ps.executeQuery()) {
                User user = null;
                if (rsUser.next()) {
                    user = new User(
                            rsUser.getInt("id"),
                            rsUser.getString("username"),
                            rsUser.getString("email"),
                            rsUser.getString("password"),
                            rsUser.getString("first_name"),
                            rsUser.getString("last_name"),
                            rsUser.getString("user_hash"),
                            rsUser.getBoolean("verificato")
                    );

                    //controlla se l'utente è anche un venditore
                    user.setUserSeller(userSellerDAO.getUserSeller(user.getId()));
                    //controlla se l'utente è anche un admin
                    user.setUserAdmin(userAdminDAO.getUserAdmin(user.getId()));
                }
                if (rsUser.next()) {
                    throw new DAOException("Errore: ci sono più utenti con lo stesso username");
                }
                return user;
            }
        } catch (SQLException ex) {
            throw new DAOException("Errore query getByUsernameAndPassword", ex);
        }
    }
 */

 /*
    @Override
    public User getByUsernameEmailAndPassword(String usernameEmail, String password) throws DAOException {
		if ((usernameEmail == null) || (password == null)) {
            throw new DAOException("Username/email e password sono campi obbligatori", new NullPointerException("username/email or password are null"));
        }
		String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
		Object[] parametriQuery = new Object[]{usernameEmail, usernameEmail, password};
		Class classe = User.class;
		String[] nomiColonne = new String[]{"id", "username", "email", "password", "first_name", "last_name", "user_hash", "verificato"};
		Class[] constructorParameterTypes = new Class[]{int.class, String.class, String.class, String.class, String.class, String.class, String.class, boolean.class};
		User user = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		
		if(user!=null){
			//controlla se l'utente è anche un venditore
			user.setUserSeller(userSellerDAO.getUserSeller(user.getId()));
			//controlla se l'utente è anche un admin
			user.setUserAdmin(userAdminDAO.getUserAdmin(user.getId()));
		}

		return user;
	}
 */
 /*
    @Override
    public boolean existsUser(String email) throws DAOException {
        try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM users WHERE email ilike ?;")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new DAOException("errore SQLException in query existsUser", ex);
        }
    }
 */

	/*
    @Override
    public User getByEmail(String email) throws DAOException {
        if (email == null) {
            throw new DAOException("Email è obbligatorio", new NullPointerException("email is null"));
        }
        try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            ps.setString(1, email);
            try (ResultSet rsUser = ps.executeQuery()) {
                User user = null;
                if (rsUser.next()) {
                    user = new User(
                            rsUser.getInt("id"),
                            rsUser.getString("username"),
                            rsUser.getString("email"),
                            rsUser.getString("password"),
                            rsUser.getString("first_name"),
                            rsUser.getString("last_name"),
                            rsUser.getString("user_hash"),
                            rsUser.getBoolean("verificato")
                    );

                    //controlla se l'utente è anche un venditore
                    user.setUserSeller(userSellerDAO.getUserSeller(user.getId()));
                    //controlla se l'utente è anche un admin
                    user.setUserAdmin(userAdminDAO.getUserAdmin(user.getId()));
                }
                if (rsUser.next()) {
                    throw new DAOException("Errore: ci sono più utenti con la stessa email");
                }
                return user;
            }
        } catch (SQLException ex) {
            throw new DAOException("Errore query getByEmail", ex);
        }
    }
	*/
