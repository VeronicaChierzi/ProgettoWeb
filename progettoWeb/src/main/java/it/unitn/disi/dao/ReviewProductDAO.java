/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.ReviewProduct;

/**
 *
 * @author root
 */
public interface ReviewProductDAO extends DAO<ReviewProduct, Integer> {

	public ReviewProduct getReviewById(int id) throws DAOException;

	public ReviewProduct[] getReviewsByProductId(int prodId) throws DAOException;
        
        public boolean addReview(int id_order, int id_product, int rate, String title, String text) throws DAOException;
        
        public boolean existReview(int id_order, int id_product) throws DAOException;

}
