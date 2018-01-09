package it.unitn.disi.entities;

import it.unitn.disi.entities.orders.Order;
import java.sql.Timestamp;

public class Segnalazione {

	private int id;
	private int idOrder;
	private String title;
	private String description;
	private Timestamp datetime;

	private SegnalazioneRisposta segnalazioneRisposta;
	private Order order;

	public Segnalazione(int id, int idOrder, String title, String description, Timestamp datetime) {
		this.id = id;
		this.idOrder = idOrder;
		this.title = title;
		this.description = description;
		this.datetime = datetime;
	}

	public boolean isOpen() {
		return (segnalazioneRisposta == null);
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
	 * @return the idOrder
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * @param idOrder the idOrder to set
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

	/**
	 * @return the segnalazioneRisposta
	 */
	public SegnalazioneRisposta getSegnalazioneRisposta() {
		return segnalazioneRisposta;
	}

	/**
	 * @param segnalazioneRisposta the segnalazioneRisposta to set
	 */
	public void setSegnalazioneRisposta(SegnalazioneRisposta segnalazioneRisposta) {
		this.segnalazioneRisposta = segnalazioneRisposta;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	// </editor-fold>

}
