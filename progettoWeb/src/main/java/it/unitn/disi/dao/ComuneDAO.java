package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.Comune;
import it.unitn.disi.entities.locations.Provincia;

public interface ComuneDAO extends DAO<Comune, Integer> {

	public Comune getComune(int id, boolean loadProvincia) throws DAOException;

	public Comune[] getComuniByIdProvincia(int idProvincia, Provincia provincia) throws DAOException;

}
