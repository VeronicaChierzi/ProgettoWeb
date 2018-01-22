package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Notification;
import java.util.List;

public interface NotificationDAO extends DAO<Notification, Integer> {
    
    public Notification getNotificationById(int id) throws DAOException;
    
    public List<Notification> getNotificationsByUserReceiverId(int idUserReceiver) throws DAOException;    
    
    
    public boolean insertNotification(int name, int id_user_sender, int id_user_receiver, String message) throws DAOException;
}
