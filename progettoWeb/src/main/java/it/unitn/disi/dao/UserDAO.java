/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.dao;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import java.util.List;

/**
 *
 * @author root
 */
public interface UserDAO extends DAO<User, Integer> {
    /**
     * Returns the number of {@link User users} stored on the persistence system
     * of the application.
     *
     * @return the number of records present into the storage system.
     * @throws DAOException if an error occurred during the information
     * retrieving.
     *
     * @author Stefano Chirico
     * @since 1.0.170403
     */
    @Override
    public Long getCount() throws DAOException;

    /**
     * Returns the {@link User user} with the primary key equals to the one
     * passed as parameter.
     *
     * @param primaryKey the {@code id} of the {@code user} to get.
     * @return the {@code user} with the id equals to the one passed as
     * parameter or {@code null} if no entities with that id is not present into
     * the storage system.
     * @throws DAOException if an error occurred during the information
     * retrieving.
     *
     * @author Stefano Chirico
     * @since 1.0.170403
     */
    @Override
    public User getByPrimaryKey(Integer primaryKey) throws DAOException;
    
    /**
     * Returns the {@link User user} with the given {@code username} and
     * {@code password}.
     * @param username the username of the user to get.
     * @param password the password of the user to get.
     * @return the {@link User user} with the given {@code username} and
     * {@code password}..
     * @throws DAOException if an error occurred during the information
     * retrieving.
     * 
     * @author Stefano Chirico
     * @since 1.0.170403
     */
    public User getByUsernameAndPassword(String username, String password) throws DAOException;

    /**
     * Returns the list of all the valid {@link User users} stored by the
     * storage system.
     *
     * @return the list of all the valid {@code users}.
     * @throws DAOException if an error occurred during the information
     * retrieving.
     *
     * @author Stefano Chirico
     * @since 1.0.170403
     */
    @Override
    public List<User> getAll() throws DAOException;
}