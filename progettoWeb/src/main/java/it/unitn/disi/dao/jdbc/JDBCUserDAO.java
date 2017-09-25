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
					try (PreparedStatement stmUserSeller = CON.prepareStatement("SELECT * FROM users_sellers WHERE id = ?")) {
						stmUserSeller.setInt(1, user.getId());
						ResultSet rsUserSeller = stmUserSeller.executeQuery();
						if (rsUserSeller.next()) {
							UserSeller userSeller = new UserSeller(
									rsUserSeller.getInt("id"),
									rsUserSeller.getString("name"),
									rsUserSeller.getString("partita_iva")
							);
							user.setUserSeller(userSeller);
							if (rsUserSeller.next()) {
								throw new DAOException("Errore: ci sono più UserSeller collegati allo stesso User");
							}
						}
					}

					//controlla se l'utente è anche un admin
					try (PreparedStatement stmUserAdmin = CON.prepareStatement("SELECT * FROM users_admins WHERE id = ?")) {
						stmUserAdmin.setInt(1, user.getId());
						ResultSet rsUserAdmin = stmUserAdmin.executeQuery();
						if (rsUserAdmin.next()) {
							UserAdmin userAdmin = new UserAdmin(
									rsUserAdmin.getInt("id")
							);
							user.setUserAdmin(userAdmin);
							if (rsUserAdmin.next()) {
								throw new DAOException("Errore: ci sono più UserAdmin collegati allo stesso User");
							}
						}
					}
				}
				if (rsUser.next()) {
					throw new DAOException("Errore: ci sono più utenti con lo stesso username");
				}
				return user;
			}
		} catch (SQLException ex) {
			throw new DAOException("Impossible to get the list of users", ex);
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
}
