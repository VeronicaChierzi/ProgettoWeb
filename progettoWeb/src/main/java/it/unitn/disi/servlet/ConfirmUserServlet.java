/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class ConfirmUserServlet extends MyServlet {
    
    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDAO) initDao(UserDAO.class);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getParameter("id") != null && request.getParameter("usn") != null) {
            
            //TODO - fare query che modifica il campo verificato dell'utente trovandolo per user_hash e username
            //Se la query ritorna vero allora siamo apposto, redirect verso la login
            String hash = request.getParameter("id");
            String usn = request.getParameter("usn");
            try {
                if(userDao.confirmUser(hash, usn)) {
                    redirect(response, "");
                    //TODO - Mostrare un messaggio di avvenuta conferma della email + inviare un eventuale conferma per email
                }
            } catch (DAOException ex) {
                Logger.getLogger(ConfirmUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } else {
            redirect(response, "login.jsp");
            
            //Gestire errore se necessario con eccezione e redirect su pagina di errore
        }
        
        
    }
}
