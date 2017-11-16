package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCShopDAO extends JDBCDAO<Shop, Integer> implements ShopDAO {
    
    public JDBCShopDAO(Connection con) {
        super(con);
    }

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
    
}
