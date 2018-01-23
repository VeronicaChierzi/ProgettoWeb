package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.Product;
import it.unitn.disi.entities.ShopProduct;
import java.sql.Connection;
import javax.servlet.ServletException;

public class JDBCShopProductDAO extends JDBCDAO<ShopProduct, Integer> implements ShopProductDAO {

	private static final Class classe = ShopProduct.class;
	private static final String[] nomiColonne = new String[]{"id_product", "id_shop", "price", "quantity"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, float.class, int.class};

	private ProductDAO productDAO;
	private ShopDAO shopDAO;

	public JDBCShopProductDAO(Connection con) {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		productDAO = (ProductDAO) initDao(ProductDAO.class, daoFactory);
		shopDAO = (ShopDAO) initDao(ShopDAO.class, daoFactory);
	}

	private void setPointers(ShopProduct sp, boolean loadProduct, boolean loadShop) throws DAOException {
		if (sp != null) {
			if (loadProduct) {
				//non dice a productDAO di caricare il MinShopProduct perchè è questo shopProduct che sta chiamando il prodotto. (altrimenti sarebbe un loop infinito. inoltre non è detto che questo sia lo shopProduct minore(per esempio quando richiamo dal carrello))
				Product p = productDAO.getProduct(sp.getIdProduct(), false, true, true);
				p.setShopProduct(sp);
				sp.setProduct(p);
			}
			if (loadShop) {
				sp.setShop(shopDAO.getShop(sp.getIdShop(), true, true));
			}
		}
	}

	@Override
	public ShopProduct getShopProduct(int idProduct, int idShop, boolean loadProduct, boolean loadShop) throws DAOException {
		String query = "SELECT * FROM shops_products WHERE id_product=? AND id_shop=?";
		Object[] parametriQuery = new Object[]{idProduct, idShop};
		ShopProduct sp = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		setPointers(sp, loadProduct, loadShop);
		return sp;
	}

	@Override
	public ShopProduct getMinShopProduct(int idProduct, Product product) throws DAOException {
		String query = "SELECT DISTINCT ON (id_product) * FROM shops_products WHERE id_product=? AND (quantity > 0) ORDER BY id_product, price";
		Object[] parametriQuery = new Object[]{idProduct};
		ShopProduct sp = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		if (sp != null) {
			sp.setProduct(product);
			sp.setShop(shopDAO.getShop(sp.getIdShop(), true, true));
		}
		return sp;
	}
	
	
	@Override
	public ShopProduct[] getShopsProductsByIdProduct(int idProduct, boolean loadProduct, boolean loadShop) throws DAOException {
		String query = "SELECT * FROM shops_products WHERE id_product=?";
		Object[] parametriQuery = new Object[]{idProduct};
		ShopProduct[] shopProducts = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (ShopProduct sp : shopProducts) {
			setPointers(sp, loadProduct, loadShop);
		}
		return shopProducts;
	}
	
	@Override
	public ShopProduct[] getShopsProductsByIdShop(int idShop, boolean loadProduct, boolean loadShop) throws DAOException {
		String query = "SELECT * FROM shops_products WHERE id_shop=?";
		Object[] parametriQuery = new Object[]{idShop};
		ShopProduct[] shopProducts = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (ShopProduct sp : shopProducts) {
			setPointers(sp, loadProduct, loadShop);
		}
		return shopProducts;
	}

}
