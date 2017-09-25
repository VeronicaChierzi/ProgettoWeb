package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unitn.disi.dao.AProductDAO;

public class JDBCProductDAO extends JDBCDAO<Product, Integer> implements AProductDAO {

	public JDBCProductDAO(Connection con) {
		super(con);
	}

	@Override
	public Product getProductByID(int id) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM products WHERE id = ?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				Product product = null;
				if (rs.next()) {
					product = new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description")
					);

					//calcola il prezzo minimo
					try (PreparedStatement stmUserAdmin = CON.prepareStatement("SELECT MIN(price) AS price_min FROM shops_products WHERE id_product = ?")) {
						stmUserAdmin.setInt(1, product.getId());
						ResultSet rsPrice = stmUserAdmin.executeQuery();
						if (rsPrice.next()) {
							Float priceMin = rsPrice.getFloat("price_min");
							product.setPriceMin(priceMin);
							if (rsPrice.next()) {
								throw new DAOException("Errore: ci sono più priceMin per lo stesso prodotto(?)");
							}
						}
					}
				}
				if (rs.next()) {
					throw new DAOException("Errore: ci sono più prodotti con lo stesso id");
				}
				if (product == null) {
					throw new DAOException("Prodotto non trovato");
				}
				return product;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore preparedStatement/sintassi query prodotto", ex);
		}
	}

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
	public boolean insertProduct(int name, int description) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
