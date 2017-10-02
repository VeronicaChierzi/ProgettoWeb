package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.UserAdmin;
import it.unitn.disi.entities.UserSeller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDAO extends JDBCDAO<User, Integer> implements UserDAO {

	public JDBCUserDAO(Connection con) {
		super(con);
	}

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
							rsUser.getString("last_name")
					);

					//controlla se l'utente è anche un venditore
					user.setUserSeller(getUserSeller(user.getId()));
					//controlla se l'utente è anche un admin
					user.setUserAdmin(getUserAdmin(user.getId()));
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
							rsUser.getString("last_name")
					);

					//controlla se l'utente è anche un venditore
					user.setUserSeller(getUserSeller(user.getId()));
					//controlla se l'utente è anche un admin
					user.setUserAdmin(getUserAdmin(user.getId()));
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

	@Override
	public UserSeller getUserSeller(int id) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM users_sellers WHERE id = ?")) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			UserSeller userSeller = null;
			if (rs.next()) {
				userSeller = new UserSeller(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("partita_iva")
				);
			}
			if (rs.next()) {
				throw new DAOException("Errore: ci sono più UserSeller collegati allo stesso User");
			}
			return userSeller;
		} catch (SQLException ex) {
			throw new DAOException("errore SQLException in query getUserAdmin", ex);
		}
	}

	private UserAdmin getUserAdmin(int id) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM users_admins WHERE id = ?")) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			UserAdmin userAdmin = null;
			if (rs.next()) {
				userAdmin = new UserAdmin(
						rs.getInt("id")
				);
			}
			if (rs.next()) {
				throw new DAOException("Errore: ci sono più UserAdmin collegati allo stesso User");
			}
			return userAdmin;
		} catch (SQLException ex) {
			throw new DAOException("errore SQLException in query getUserAdmin", ex);
		}
	}

	@Override
	public boolean insertUser(String username, String email, String password, String firstName, String lastName) throws DAOException {
		if ((username == null) || (email == null) || (password == null) || (firstName == null) || (lastName == null)) {
			throw new DAOException("Username, email, password, firstName e lastName sono campi obbligatori", new NullPointerException("Username, email, password, firstName o lastName sono null"));
		}
		String query = "INSERT INTO users(username, email, password, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, firstName);
			ps.setString(5, lastName);

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
}
