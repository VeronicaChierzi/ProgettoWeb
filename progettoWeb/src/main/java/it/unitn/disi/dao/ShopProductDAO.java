/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Shop;
import java.util.List;

/**
 *
 * @author root
 */
public interface ShopProductDAO {
    
    public List<Shop> getAllShopsThatSellsAProduct(int prodId) throws DAOException;
    
}
