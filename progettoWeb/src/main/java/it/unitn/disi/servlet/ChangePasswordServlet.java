/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.utils.HashUtil;
import it.unitn.disi.utils.PasswordValidator;
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
public class ChangePasswordServlet extends MyServlet {

    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDAO) initDao(UserDAO.class);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null && request.getParameter("email") != null && request.getParameter("password") != null) {
            String hash = request.getParameter("id");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (new PasswordValidator().validate(password)) {
                try {
                    if (userDao.hasRightHash(email, hash)) {
                        userDao.changePassword(email, HashUtil.generatePasswordHash(password), hash);
                        userDao.setNewUserHash(userDao.getByEmail(email).getId(), HashUtil.generateSecureHash(email));
                        redirect(response, "/login.jsp");
                    } else {
                        redirect(response, "/index.jsp");
                    }
                } catch (DAOException ex) {
                    Logger.getLogger(ConfirmUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                redirect(response, "cambiaPassword.jsp" + "?id=" + hash + "&email=" + email);
            }

        } else {
            redirect(response, "login.jsp");

            //Gestire errore se necessario con eccezione e redirect su pagina di errore
        }

    }

}
