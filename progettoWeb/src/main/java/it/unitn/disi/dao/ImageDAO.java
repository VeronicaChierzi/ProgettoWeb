/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Image;

/**
 *
 * @author root
 */
public interface ImageDAO extends DAO<Image, Integer> {
    
    public Image getProductImage(int prodId) throws DAOException;
    
}
