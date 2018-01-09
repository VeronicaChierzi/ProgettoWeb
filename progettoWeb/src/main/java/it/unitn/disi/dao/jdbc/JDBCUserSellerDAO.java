package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.UserSellerDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.UserSeller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class JDBCUserSellerDAO extends JDBCDAO<UserSeller, Integer> implements UserSellerDAO {

	private static final Class classe = UserSeller.class;
	private static final String[] nomiColonne = new String[]{"id", "name", "partita_iva"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, String.class, String.class};

	private ShopDAO shopDAO;

	public JDBCUserSellerDAO(Connection con) throws ServletException {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		shopDAO = (ShopDAO) initDao(ShopDAO.class, daoFactory);
	}

	@Override
	public UserSeller getUserSeller(int id) throws DAOException {
		String query = "SELECT * FROM users_sellers WHERE id=?";
		Object[] parametriQuery = new Object[]{id};
		UserSeller us = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return us;
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
