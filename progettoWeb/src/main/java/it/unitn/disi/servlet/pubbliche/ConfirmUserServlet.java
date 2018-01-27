package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
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
		String id = Model.Parameter.get(request, "id");
		String usn = Model.Parameter.get(request, "usn");
        if(id != null && !id.equals("") && usn != null && !usn.equals("")) {
            try {
                if(userDao.confirmUser(id, usn)) {
					User user = (User) Model.Session.getAttribute(request, Model.Session.user);
					if(user!=null){
						user.setVerificato(true);
					}
					System.err.println("Utente confermato!");
                    redirect(response, MyPaths.Jsp.allConfirmedUser);
                } else {
					System.err.println("Errore: impossibile confermare l'utente");
                    redirect(response, MyPaths.Jsp.allHome);
                }
            } catch (DAOException ex) {
				System.err.println("DAOException in ConfirmUserServlet");
                Logger.getLogger(ConfirmUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
			System.err.println("Errore: impossibile confermare l'utente (i parametri id e usn non sono stati inviati)");
			redirect(response, MyPaths.Jsp.allHome);
        }
    }
}
