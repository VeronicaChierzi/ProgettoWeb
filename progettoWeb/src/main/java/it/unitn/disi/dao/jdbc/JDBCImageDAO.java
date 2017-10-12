/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ImageDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author root
 */
public class JDBCImageDAO extends JDBCDAO<Image, Integer> implements ImageDAO {

    public JDBCImageDAO(Connection con) {
        super(con);
    }
    
    @Override
    public Image getProductImage(int prodId) throws DAOException {
        try (PreparedStatement ps = CON.prepareStatement("select * from images where id_product = ? limit 1;")) {
            ps.setInt(1, prodId);
            try (ResultSet rs = ps.executeQuery()) {
                Image image = null;
                if (rs.next()) {
                    image = new Image(
                            rs.getInt("id"),
                            rs.getInt("id_product"),
                            rs.getString("path"),
                            rs.getString("alt")
                    );
                }
                return image;
            }
        } catch (SQLException ex) {
            throw new DAOException("Errore SQLException query getProductImage", ex);
        }
    }
    
}
