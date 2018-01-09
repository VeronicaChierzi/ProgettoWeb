package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.Regione;

public interface RegioneDAO extends DAO<Regione, Integer> {

	public Regione getRegione(int id) throws DAOException;

	public Regione[] getRegioni() throws DAOException;

}
