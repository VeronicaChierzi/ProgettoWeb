/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet.privatee;

import it.unitn.disi.controllers.CartController;
import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
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
public class PaymentServlet extends MyServlet {

    private ShopProductDAO shopProductDAO;

    @Override
    public void init() throws ServletException {
        shopProductDAO = (ShopProductDAO) initDao(ShopProductDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = Model.Session.getUserLogged(request);
            if (user != null) {
                try {
                    if (Model.Session.getAttribute(request, Model.Session.cart) != null) {
                        CartController.updateCart(request.getSession(), shopProductDAO);
                    } else {
                        System.err.println("Errore utente senza carrello PaymentServlet.");
                        Model.Messages.setBoolean(request, "erroreAcquisto");
                        redirect(response, MyPaths.Jsp.userOrders);
                    }

                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in PaymentServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else {
                //redirect home
                System.err.println("Utente non loggato ma voleva pagare in PaymentServlet");
                redirect(response, MyPaths.Jsp.allHome);
            }

        } catch (Exception ex) {
            System.err.println("Errore utente non loggato in PaymentServlet: " + ex.getMessage());
            redirect(response, MyPaths.Jsp.allHome);
        }
    }

}
