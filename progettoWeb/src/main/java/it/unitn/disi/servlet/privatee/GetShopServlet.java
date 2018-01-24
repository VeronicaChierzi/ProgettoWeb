/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Shop;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class GetShopServlet extends MyServlet {
    
    private ShopDAO shopDAO;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        shopDAO = (ShopDAO) initDao(ShopDAO.class);
        userDAO = (UserDAO) initDao(UserDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            try {
                Shop s = shopDAO.getShop(id, true, true);
                User u = userDAO.getUser(s.getUserSeller().getId());
                Model.Request.setAttribute(request, Model.Request.shop, s);
                Model.Request.setAttribute(request, Model.Request.user, u);
            } catch (DAOException ex) {
                System.err.println("Errore DAOException in GetShoptServlet: " + ex.getMessage());
                forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
            }
        } catch (NumberFormatException e) {
            System.err.println("Errore NumberFormatException in GetShoptServlet: " + e.getMessage());
        }
        
    }

}
