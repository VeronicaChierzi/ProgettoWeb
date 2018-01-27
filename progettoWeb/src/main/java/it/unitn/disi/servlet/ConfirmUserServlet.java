package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmUserServlet extends MyServlet {
    
    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDAO) initDao(UserDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("id") != null && request.getParameter("usn") != null) {
            //TODO - fare query che modifica il campo verificato dell'utente trovandolo per user_hash e username
            //Se la query ritorna vero allora siamo apposto, redirect verso la login
            String hash = request.getParameter("id");
            String usn = request.getParameter("usn");
            try {
                //System.out.println("Dega");
                if(userDao.confirmUser(hash, usn)) {
                    redirect(response, MyPaths.Jsp.allConfirmedUser);
                    //TODO - Mostrare un messaggio di avvenuta conferma della email + inviare un eventuale conferma per email
                } else {
                    redirect(response, "index.jsp");
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
