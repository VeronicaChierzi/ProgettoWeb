package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Shop;
import it.unitn.disi.entities.ShopProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCShopProductDAO extends JDBCDAO<ShopProduct, Integer> implements ShopProductDAO {

	private static final Class classe = ShopProduct.class;
	private static final String[] nomiColonne = new String[]{"id_product", "id_shop", "price", "quantity"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, float.class, int.class};

	public JDBCShopProductDAO(Connection con) {
		super(con);
	}
	
	@Override
	public ShopProduct getShopProduct(int idProduct, int idShop) throws DAOException {
		String query = "SELECT * FROM shops_products WHERE id_product=? AND id_shop=?";
		Object[] parametriQuery = new Object[]{idProduct, idShop};
		ShopProduct shopProduct = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return shopProduct;
	}

	@Override
	public ShopProduct getMinShopProduct(int idProduct) throws DAOException {
		String query = "SELECT DISTINCT ON (id_product) * FROM shops_products WHERE id_product=? AND (quantity > 0) ORDER BY id_product, price";
		Object[] parametriQuery = new Object[]{idProduct};
		ShopProduct sp = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return sp;
	}

	@Override
	public List<Shop> getAllShopsThatSellsAProduct(int prodId) throws DAOException {
		ArrayList<Shop> shops = new ArrayList<>();
		ArrayList<Integer> shopsId = new ArrayList<>();

		try (PreparedStatement ps = CON.prepareStatement("SELECT id_shop FROM public.shops_products where id_product = ?;")) {
			ps.setInt(1, prodId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					shopsId.add(rs.getInt("id_shop"));
				}
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query allshops in JDBCShopPRoduct", ex);
		}

		for (Integer i : shopsId) {
			try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM shops where id = ?;")) {
				ps.setInt(1, i);
				try (ResultSet rs = ps.executeQuery()) {
					Shop shop = null;
					while (rs.next()) {
						shop = new Shop(
								rs.getInt("id"),
								rs.getInt("id_onwer"),
								rs.getDouble("latitude"),
								rs.getDouble("longitude"),
								rs.getString("address"),
								rs.getInt("id_comune")
						);
						shops.add(shop);
					}
				}
			} catch (SQLException ex) {
				throw new DAOException("Errore SQLException query allshops in JDBCShopPRoduct", ex);
			}
		}
		return shops;
	}
}
