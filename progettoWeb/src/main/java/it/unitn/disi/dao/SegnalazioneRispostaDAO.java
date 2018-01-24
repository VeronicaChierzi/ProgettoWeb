package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.SegnalazioneRisposta;

public interface SegnalazioneRispostaDAO extends DAO<SegnalazioneRisposta, Integer> {

	public SegnalazioneRisposta getSegnalazioneRisposta(int idSegnalazione) throws DAOException;
        
        public boolean addRispostaByAdmin(int idSegnalazione, int idUser, String message, String decisione, boolean restSoldi, boolean valutazioneNegativa) throws DAOException;


}
