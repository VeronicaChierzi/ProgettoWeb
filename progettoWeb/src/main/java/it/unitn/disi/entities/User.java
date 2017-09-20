/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.entities;

/**
 *
 * @author root
 */
public class User {
    
    private int id;
    private String username, password, firstName, lastName;

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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
    
}
