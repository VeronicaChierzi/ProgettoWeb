package it.unitn.disi.entities;

public class Shop {

	private int id;
	private int idOwner;
	private double latitude;
	private double longitude;
	private String address;
	private int idComune;

	public Shop(int id, int idOwner, double latitude, double longitude, String address, int idComune) {
		this.id = id;
		this.idOwner = idOwner;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.idComune = idComune;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIdComune() {
		return idComune;
	}

	public void setIdComune(int idComune) {
		this.idComune = idComune;
	}
}
