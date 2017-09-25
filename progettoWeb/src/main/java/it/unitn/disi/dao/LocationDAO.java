package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.Location;

public interface LocationDAO extends DAO<Location, Integer> {

	public Location getLocation() throws DAOException;

}