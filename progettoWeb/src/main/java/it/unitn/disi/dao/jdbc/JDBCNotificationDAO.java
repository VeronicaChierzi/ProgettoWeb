/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.NotificationDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Notification;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class JDBCNotificationDAO extends JDBCDAO<Notification, Integer> implements NotificationDAO {

    public JDBCNotificationDAO(Connection con) {
        super(con);
    }

    @Override
    public Notification getNotificationById(int id) throws DAOException {
        try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM notifications WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                Notification notification = null;
                if (rs.next()) {
                    notification = new Notification(
                            rs.getInt("id"),
                            rs.getInt("id_user_sender"),
                            rs.getInt("id_user_receiver"),
                            rs.getString("message"),
                            rs.getDate("date")
                    );
                }
                if (rs.next()) {
                    throw new DAOException("Errore: ci sono più notifiche con lo stesso id");
                }
                return notification;
            }
        } catch (SQLException ex) {
            throw new DAOException("Errore SQLException query notifiche", ex);
        }
    }

    @Override
    public boolean insertNotification(int name, int id_user_sender, int id_user_receiver, String message) throws DAOException {
        if ((message == null)) {
            throw new DAOException("Message è un campo obbligatoro", new NullPointerException("Name, id_user_receiver, message sono null"));
        }
        String query = "INSERT INTO notification(id_user_sender, id_user_receiver, message, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = CON.prepareStatement(query)) {
            ps.setInt(1, id_user_sender);
            ps.setInt(2, id_user_receiver);
            ps.setString(3, message);
            ps.setDate(4, new Date(System.currentTimeMillis()));

            int result = -1; //quantità di righe modificate dalla query insert
            try {
                result = ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DAOException("Errore esecuzione query: " + ex);
            }
            boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
            return b;
        } catch (SQLException ex) {
            throw new DAOException("Errore preparedStatement o sintassi query: " + ex);
        }
    }

    @Override
    public List<Notification> getNotificationsByUserReceiverId(int idUserReceiver) throws DAOException {
        List<Notification> l = new ArrayList<Notification>();
        try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM notifications WHERE id_user_receiver = ?")) {
            ps.setInt(1, idUserReceiver);
            try (ResultSet rs = ps.executeQuery()) {
                Notification notification = null;
                while (rs.next()) {
                    notification = new Notification(
                            rs.getInt("id"),
                            rs.getInt("id_user_sender"),
                            rs.getInt("id_user_receiver"),
                            rs.getString("message"),
                            rs.getDate("date")
                    );
                    l.add(notification);
                }
                return l;
            }
        } catch (SQLException ex) {
            throw new DAOException("Errore SQLException query notifiche by user receiver id", ex);
        }
    }

}
