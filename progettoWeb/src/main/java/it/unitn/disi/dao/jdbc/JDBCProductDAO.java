package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unitn.disi.dao.ProductDAO;
import java.util.ArrayList;

public class JDBCProductDAO extends JDBCDAO<Product, Integer> implements ProductDAO {

	public JDBCProductDAO(Connection con) {
		super(con);
	}

	@Override
	public Product[] getProducts() throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM products")) {
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Product> products_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
				while (rs.next()) {
					Product p = new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getInt("id_subcategory")
					);
					p.setPriceMin(getMinPrice(p.getId()));
					products_temp.add(p);
				}
				Product[] products = new Product[products_temp.size()];
				products = products_temp.toArray(products); //trasforma arrayList in un array statico
				return products;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getProducts", ex);
		}
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
							rs.getString("description"),
							rs.getInt("id_subcategory")
					);
					product.setPriceMin(getMinPrice(product.getId()));
				}
				if (rs.next()) {
					throw new DAOException("Errore: ci sono più prodotti con lo stesso id");
				}
				return product;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getProductByID", ex);
		}
	}

	private float getMinPrice(int idProduct) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT MIN(price) AS price_min FROM shops_products WHERE id_product = ?")) {
			ps.setInt(1, idProduct);
			ResultSet rs = ps.executeQuery();
			float priceMin = -1;
			if (rs.next()) {
				priceMin = rs.getFloat("price_min");
			}
			if (rs.next()) {
				throw new DAOException("Errore: ci sono più priceMin per lo stesso prodotto(?)");
			}
			return priceMin;
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getMinPrice", ex);
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
