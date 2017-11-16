package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.SegnalazioneRisposta;

public interface SegnalazioneRispostaDAO extends DAO<SegnalazioneRisposta, Integer> {

	public SegnalazioneRisposta getSegnalazioneRisposta(int idSegnalazione) throws DAOException;

}
