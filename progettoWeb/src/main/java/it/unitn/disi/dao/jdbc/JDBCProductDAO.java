package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.entities.ShopProduct;
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
					p.setShopProduct(getMinShopProduct(p.getId()));
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
					product.setShopProduct(getMinShopProduct(product.getId()));
				}
				System.out.println(product);
				System.out.println(product.getShopProduct());
				if(product.getShopProduct()!=null){
					System.out.println(product.getShopProduct().getPrice());
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

	private ShopProduct getMinShopProduct(int idProduct) throws DAOException {
		String query = "SELECT DISTINCT ON (id_product) * FROM shops_products WHERE id_product=? AND (quantity > 0) ORDER BY id_product, price";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setInt(1, idProduct);
			ResultSet rs = ps.executeQuery();
			ShopProduct sp = null;
			if (rs.next()) {
				sp = new ShopProduct(
						rs.getInt("id_product"),
						rs.getInt("id_shop"),
						rs.getFloat("price"),
						rs.getInt("quantity")
				);
			}
			if (rs.next()) {
				throw new DAOException("Errore: ci sono più ShopProduct minimi con lo stesso idProduct (impossibile)(?)");
			}
			return sp;
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getMinShopProduct: "+ex.getMessage(), ex);
		}
	}

	@Override
	public boolean insertProduct(int name, int description) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
