package it.unitn.disi.entities;

public class UserSeller {

	private int id;
	private String name;
	private String partitaIva;

	public UserSeller() {
	}

	public UserSeller(int id, String name, String partitaIva) {
		this.id = id;
		this.name = name;
		this.partitaIva = partitaIva;
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
	 * @return the partitaIva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}

	/**
	 * @param partitaIva the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	// </editor-fold>

}
