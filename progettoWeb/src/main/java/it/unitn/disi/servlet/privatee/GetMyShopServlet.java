/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Shop;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class GetMyShopServlet extends MyServlet {

    private ShopDAO shopDAO;

    @Override
    public void init() throws ServletException {
        shopDAO = (ShopDAO) initDao(ShopDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = null;
        try {
            u = Model.Session.getUserLogged(request);
        } catch (Exception ex) {
            Logger.getLogger(GetMyShopServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Shop[] neg = null;
        if (u != null) {
            try {
                neg = shopDAO.getShopsByUserSeller(u.getUserSeller().getId());
                Model.Request.setAttribute(request, "shops", neg);
            } catch (DAOException ex) {
                Logger.getLogger(GetMyShopServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
