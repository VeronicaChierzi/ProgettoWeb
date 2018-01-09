package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.Provincia;
import it.unitn.disi.entities.locations.Regione;

public interface ProvinciaDAO extends DAO<Provincia, Integer> {

	public Provincia getProvincia(int id, boolean loadRegione) throws DAOException;

	public Provincia[] getProvinceByIdRegione(int idRegione, Regione regione) throws DAOException;

}
