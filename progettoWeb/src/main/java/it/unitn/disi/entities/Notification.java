/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.entities;

import java.sql.Date;

/**
 *
 * @author root
 */
public class Notification {
    
    private int id, id_user_sender, id_user_receiver;
    private String message;
    private Date date;
    // Aggiungere il tipo? Per distinguere se da utente registrato a admin, admin -> venditore, admin -> utente registrato (per rimborso) vedi terza pagina testo
    // Aggiungere l'ordine a cui si riferisce? Serve per determinare a chi mandare la notifica (venditore o acquirente)

    public Notification(int id, int id_user_sender, int id_user_receiver, String message, Date date) {
        this.id = id;
        this.id_user_sender = id_user_sender;
        this.id_user_receiver = id_user_receiver;
        this.message = message;
        this.date = date;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user_sender() {
        return id_user_sender;
    }

    public void setId_user_sender(int id_user_sender) {
        this.id_user_sender = id_user_sender;
    }

    public int getId_user_receiver() {
        return id_user_receiver;
    }

    public void setId_user_receiver(int id_user_receiver) {
        this.id_user_receiver = id_user_receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
