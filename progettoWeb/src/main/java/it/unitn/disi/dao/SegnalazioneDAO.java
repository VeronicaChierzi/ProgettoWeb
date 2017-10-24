package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Segnalazione;

public interface SegnalazioneDAO extends DAO<Segnalazione, Integer> {

	public Segnalazione[] getAllSegnalazioni() throws DAOException;

	public Segnalazione[] getOpenSegnalazioni() throws DAOException;

	public Segnalazione getSegnalazione(int idSegnalazione) throws DAOException;

}
