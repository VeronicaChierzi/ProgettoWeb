package it.unitn.disi.entities;

public class SegnalazioneRisposta {
	
	private int idSegnalazione;
	private int idAdmin;
	private String message;
	private String decisione;	//contiene 'a' per approvata, 'r' per rigettata, 'n' per non procedo
	private boolean restituireSoldi;
	private boolean valutazioneNegativaVenditore;

	public SegnalazioneRisposta(int idSegnalazione, int idAdmin, String message, String decisione, boolean restituireSoldi, boolean valutazioneNegativaVenditore) {
		this.idSegnalazione = idSegnalazione;
		this.idAdmin = idAdmin;
		this.message = message;
		this.decisione = decisione;
		this.restituireSoldi = restituireSoldi;
		this.valutazioneNegativaVenditore = valutazioneNegativaVenditore;
	}

	// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
	/**
	 * @return the idSegnalazione
	 */
	public int getIdSegnalazione() {
		return idSegnalazione;
	}

	/**
	 * @param idSegnalazione the idSegnalazione to set
	 */
	public void setIdSegnalazione(int idSegnalazione) {
		this.idSegnalazione = idSegnalazione;
	}

	/**
	 * @return the idAdmin
	 */
	public int getIdAdmin() {
		return idAdmin;
	}

	/**
	 * @param idAdmin the idAdmin to set
	 */
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the decisione
	 */
	public String getDecisione() {
		return decisione;
	}

	/**
	 * @param decisione the decisione to set
	 */
	public void setDecisione(String decisione) {
		this.decisione = decisione;
	}

	/**
	 * @return the restituireSoldi
	 */
	public boolean isRestituireSoldi() {
		return restituireSoldi;
	}

	/**
	 * @param restituireSoldi the restituireSoldi to set
	 */
	public void setRestituireSoldi(boolean restituireSoldi) {
		this.restituireSoldi = restituireSoldi;
	}

	/**
	 * @return the valutazioneNegativaVenditore
	 */
	public boolean isValutazioneNegativaVenditore() {
		return valutazioneNegativaVenditore;
	}

	/**
	 * @param valutazioneNegativaVenditore the valutazioneNegativaVenditore to set
	 */
	public void setValutazioneNegativaVenditore(boolean valutazioneNegativaVenditore) {
		this.valutazioneNegativaVenditore = valutazioneNegativaVenditore;
	}
	// </editor-fold>

}
