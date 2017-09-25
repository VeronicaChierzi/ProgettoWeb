package it.unitn.disi.entities.locations;

public class Provincia {

	private int id;
	private String name;
	private int idRegione;

	private Regione regione;
	private Comune[] comuni;

	public Provincia() {
	}

	public Provincia(int id, String name, int idRegione, Regione regione) {
		this.id = id;
		this.name = name;
		this.idRegione = idRegione;
		this.regione = regione;
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
	 * @return the idRegione
	 */
	public int getIdRegione() {
		return idRegione;
	}

	/**
	 * @param idRegione the idRegione to set
	 */
	public void setIdRegione(int idRegione) {
		this.idRegione = idRegione;
	}

	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	/**
	 * @return the comuni
	 */
	public Comune[] getComuni() {
		return comuni;
	}

	/**
	 * @param comuni the comuni to set
	 */
	public void setComuni(Comune[] comuni) {
		this.comuni = comuni;
	}
	// </editor-fold>
}
