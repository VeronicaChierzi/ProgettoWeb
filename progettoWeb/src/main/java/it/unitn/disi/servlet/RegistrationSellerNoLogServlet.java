/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.utils.PasswordValidator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Veronica Chierzi
 */
@WebServlet(name = "RegistrationSellerNoLogServlet", urlPatterns = {"/RegistrationSellerNoLogServlet"})

public class RegistrationSellerNoLogServlet extends MyServlet {

    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDAO) initDao(UserDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        String username = (String) request.getParameter("username");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String password2 = (String) request.getParameter("password2");
        String firstName = (String) request.getParameter("first_name");
        String lastName = (String) request.getParameter("last_name");
        String nomeNeg = (String) request.getParameter("nomeNeg");
        String partitaIva = (String) request.getParameter("partita_iva");
        
        session.setAttribute("username", username);
        session.setAttribute("email", email);
        session.setAttribute("first_name", firstName);
        session.setAttribute("last_name", lastName);
        session.setAttribute("nomeNeg", nomeNeg);
        
        if (password.equals(password2)) {
            PasswordValidator pwdv = new PasswordValidator();
            if (pwdv.validate(password)) {
                System.out.println("ok");
            } else {
                System.out.println("Password non rispetta parametri");
                redirect(response, "register.jsp"); //l'utente deve re-inserire 
                return;
            }

        } else {
            System.out.println("password diverse");
            redirect(response, "register.jsp"); //l'utente deve ri-registrarsi
            return;
        }
        
        try {
            boolean b = userDao.insertUser(username, email, password, firstName, lastName);
            
            if (b) { //utente inserito nel database
                System.out.println("utente nel db");
                User user = userDao.getByUsernameAndPassword(username, password);
                int idUser = user.getId();
                boolean v = userDao.insertUserSeller(idUser, nomeNeg, partitaIva);
                
                if (v){
                    System.out.println("negozio nel db");
                forward(request, response, "/LoginServlet");
                }
            } else { //utente non registrato. impostare messaggio di registrazione fallita da visualizzare
                redirect(response, "registerSellerNoLog.jsp");
            }
        } catch (DAOException ex) {
            //impossibile inserire nuovo utente
            System.err.println(ex.getMessage());
            redirect(response, "registerSellerNoLog.jsp");
        }

    }

}
