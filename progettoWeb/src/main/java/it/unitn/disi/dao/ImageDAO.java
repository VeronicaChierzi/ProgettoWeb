package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Image;

public interface ImageDAO extends DAO<Image, Integer> {

	public Image getProductImage(int prodId) throws DAOException;

}
