/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author root
 */
public class JDBCShopProduct extends JDBCDAO<ShopProduct, Integer> implements ShopProductDAO {

    public JDBCShopProduct(Connection con) {
        super(con);
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
