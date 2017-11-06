package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.LocationContainer;

public interface LocationDAO extends DAO<LocationContainer, Integer> {

	public LocationContainer getLocation() throws DAOException;

}