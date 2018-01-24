package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Segnalazione;

public interface SegnalazioneDAO extends DAO<Segnalazione, Integer> {

	public Segnalazione[] getSegnalazioniAdmin() throws DAOException;

	public Segnalazione[] getOpenSegnalazioniAdmin() throws DAOException;

	public Segnalazione getSegnalazioneAdmin(int idSegnalazione) throws DAOException;

	
	public Segnalazione[] getSegnalazioniByIdUser(int idUser) throws DAOException;

	public Segnalazione[] getOpenSegnalazioniByIdUser(int idUser) throws DAOException;

	public Segnalazione getSegnalazioneUser(int idSegnalazione, int idUser) throws DAOException;
        
        public boolean addSegnalazioneUser(int idOrder, String title, String descr) throws DAOException;


	public Segnalazione[] getSegnalazioniByIdSeller(int idSeller) throws DAOException;

	public Segnalazione[] getOpenSegnalazioniByIdSeller(int idSeller) throws DAOException;

	public Segnalazione getSegnalazioneSeller(int idSegnalazione, int idSeller) throws DAOException;

}
