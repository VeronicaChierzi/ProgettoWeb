package it.unitn.disi.entities.locations;

public class Regione {

	private int id;
	private String name;

	private Provincia[] province;

	public Regione() {
	}

	public Regione(int id, String name) {
		this.id = id;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the province
	 */
	public Provincia[] getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(Provincia[] province) {
		this.province = province;
	}
	// </editor-fold>
}
