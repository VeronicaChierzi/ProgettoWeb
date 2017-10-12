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
import java.util.List;
import org.unbescape.html.HtmlEscape;

public class JDBCProductDAO extends JDBCDAO<Product, Integer> implements ProductDAO {

    public JDBCProductDAO(Connection con) {
        super(con);
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

    @Override
    public List<Product> searchProducts(String text) throws DAOException {

        String querySelectProdotto = "select distinct on (\"name\") * from\n"
                + "((SELECT * FROM products WHERE to_tsvector(\"name\") @@ plainto_tsquery(?))\n"
                + "UNION\n"
                + "(select * from products where \"name\" ilike ? or \"name\" ilike ? order by \"name\" asc)) as ris\n"
                + "ORDER BY ris.\"name\" asc;";

        try (PreparedStatement ps = CON.prepareStatement(querySelectProdotto)) {

            ps.setString(1, HtmlEscape.escapeHtml5(text));
            ps.setString(2, HtmlEscape.escapeHtml5(text) + "%");
            ps.setString(3, "%" + HtmlEscape.escapeHtml5(text) + "%");
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                ArrayList<Product> products_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("id_subcategory")
                    );
                    //p.setPriceMin(getMinPrice(p.getId()));
                    p.setPriceMin(rs.getFloat("price_standard"));
                    products_temp.add(p);
                }
                return products_temp;
            }
        } catch (SQLException ex) {
            throw new DAOException("Errore SQLException query getProducts", ex);
        }
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
				if (product.getShopProduct() != null) {
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
			throw new DAOException("Errore SQLException query getMinShopProduct: " + ex.getMessage(), ex);
		}
	}

	@Override
	public boolean insertProduct(int name, int description) throws DAOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ShopProduct getShopProduct(int idProduct, int idShop) throws DAOException {
		String query = "SELECT * FROM shops_products WHERE id_product=? AND id_shop=?";
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			ps.setInt(1, idProduct);
			ps.setInt(2, idShop);
			try (ResultSet rs = ps.executeQuery()) {
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
					throw new DAOException("Errore: ci sono più shopProduct con stesso idProduct e idShop");
				}
				return sp;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getShopProduct", ex);
		}
	}
}
