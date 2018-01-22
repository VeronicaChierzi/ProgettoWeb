/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.entities;

import java.sql.Timestamp;

/**
 *
 * @author root
 */
public class ReviewProduct {
    private int id;
    private int id_order;
    private int id_product;
    private int rate;
    private String title;
    private String text;
    private Timestamp datetime;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public ReviewProduct(int id, int id_order, int id_product, String title, String text, Timestamp datetime, int rate) {
        this.id = id;
        this.id_order = id_order;
        this.id_product = id_product;
        this.rate = rate;
        this.title = title;
        this.text = text;
        this.datetime = datetime;
    }

    /**
     * @return the id_order
     */
    public int getId_order() {
        return id_order;
    }

    /**
     * @param id_order the id_order to set
     */
    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    /**
     * @return the id_product
     */
    public int getId_product() {
        return id_product;
    }

    /**
     * @param id_product the id_product to set
     */
    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the datetime
     */
    public Timestamp getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
    
    
    
    
}
