package it.unitn.disi.entities;

import it.unitn.disi.entities.locations.Comune;

public class Shop {

    private int id;
    private int idOwner;
    private double latitude;
    private double longitude;
    private String address;
    private int idComune;
    private String orario;

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    private UserSeller userSeller;
    private Comune comune;

    public Shop(int id, int idOwner, double latitude, double longitude, String address, int idComune, String orario) {
        this.id = id;
        this.idOwner = idOwner;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.idComune = idComune;
        this.orario = orario;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters e Setters">
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
     * @return the idOwner
     */
    public int getIdOwner() {
        return idOwner;
    }

    /**
     * @param idOwner the idOwner to set
     */
    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the idComune
     */
    public int getIdComune() {
        return idComune;
    }

    /**
     * @param idComune the idComune to set
     */
    public void setIdComune(int idComune) {
        this.idComune = idComune;
    }

    /**
     * @return the userSeller
     */
    public UserSeller getUserSeller() {
        return userSeller;
    }

    /**
     * @param userSeller the userSeller to set
     */
    public void setUserSeller(UserSeller userSeller) {
        this.userSeller = userSeller;
    }

    /**
     * @return the comune
     */
    public Comune getComune() {
        return comune;
    }

    /**
     * @param comune the comune to set
     */
    public void setComune(Comune comune) {
        this.comune = comune;
    }
    // </editor-fold>

}
