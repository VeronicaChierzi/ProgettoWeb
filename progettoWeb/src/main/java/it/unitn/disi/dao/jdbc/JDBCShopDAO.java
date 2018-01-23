package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ComuneDAO;
import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.UserSellerDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

public class JDBCShopDAO extends JDBCDAO<Shop, Integer> implements ShopDAO {

    private static final Class classe = Shop.class;
    private static final String[] nomiColonne = new String[]{"id", "id_owner", "latitude", "longitude", "address", "id_comune", "orario"};
    private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, double.class, double.class, String.class, int.class, String.class};

    private UserSellerDAO userSellerDAO;
    private ComuneDAO comuneDAO;

    public JDBCShopDAO(Connection con) {
        super(con);
    }

    @Override
    public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
        userSellerDAO = (UserSellerDAO) initDao(UserSellerDAO.class, daoFactory);
        comuneDAO = (ComuneDAO) initDao(ComuneDAO.class, daoFactory);
    }

    private void setPointers(Shop s, boolean loadUserSeller, boolean loadComune) throws DAOException {
        if (s != null) {
            if (loadUserSeller) {
                s.setUserSeller(userSellerDAO.getUserSeller(s.getIdOwner()));
            }
            if (loadComune) {
                s.setComune(comuneDAO.getComune(s.getIdComune(), true));
            }
        }
    }

    @Override
    public Shop getShop(int idShop, boolean loadUserSeller, boolean loadComune) throws DAOException {
        String query = "SELECT * FROM shops WHERE id=?";
        Object[] parametriQuery = new Object[]{idShop};
        Shop s = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        setPointers(s, loadUserSeller, loadComune);
        return s;
    }

    @Override
    public Shop[] getShopsByUserSeller(int idUser) throws DAOException {
        String query = "SELECT * FROM shops WHERE id_owner=? ORDER BY \"address\"";
        Object[] parametriQuery = new Object[]{idUser};
        Shop[] shops = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        for (Shop s : shops) {
            setPointers(s, true, true);
        }
        return shops;
    }

    @Override
    public Shop[] getShops() throws DAOException {
        String query = "SELECT * FROM shops";
        Object[] parametriQuery = new Object[]{};
        Shop[] shops = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        return shops;
    }

    /*
	@Override
	public Shop[] getShopsByIdUser(int idUser) throws DAOException {
		String query = "SELECT * FROM shops WHERE id=";
		Object[] parametriQuery = new Object[]{};
		Shop[] shops = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return shops;
	}
     */
 /*
	@Override
	public List<Shop> getAllShops() throws DAOException {
		List<Shop> l = new ArrayList<Shop>();
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM shops")) {
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
					l.add(shop);
				}
				return l;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query allshops", ex);
		}
	}
     */
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
                                rs.getInt("id_comune"),
                                rs.getString("orario")
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

    @Override
    public boolean changeOrarioShop(int idShop, String orario) throws DAOException {
        try (PreparedStatement ps = CON.prepareStatement("UPDATE public.shops SET orario=? WHERE id=?;")) {
            ps.setString(1, orario);
            ps.setInt(2, idShop);
            int ris = ps.executeUpdate();
            if (ris == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new DAOException("errore SQLException in query changeOrarioShop", ex);
        }
    }

}
