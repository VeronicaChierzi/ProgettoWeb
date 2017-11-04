package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.HashUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends MyServlet {

    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDAO) initDao(UserDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usernameEmail = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        try {
            User user1 = userDao.getByUsernameAndPassword(usernameEmail, HashUtil.generatePasswordHash(password));
            User user2 = userDao.getByEmailAndPassword(usernameEmail, HashUtil.generatePasswordHash(password));
            if ((user1 != null) ) {
                //utente loggato con username
                session.setAttribute("user", user1);
                redirect(response, "user/profilo.jsp");
                System.out.println("username");
            } else  if (user2!=null){
                //utente loggato con email
                session.setAttribute("user", user2);
                redirect(response, "user/profilo.jsp");
                System.out.println("email");
            } else {
                //query eseguita senza errori, ma che non ha dato nessun risultato
                //utente non loggato. impostare messaggio di login fallito da visualizzare
                session.setAttribute("loginFallito", "Login fallito: username o password errati");
				Model.Messages.setLoginFailed(request);
                redirect(response, "login.jsp");
                System.err.println("errato");
            }
        } catch (DAOException ex) {
            //la query ha generato un errore
            //utente non loggato. impostare messaggio di login fallito da visualizzare
            session.setAttribute("loginFallito", "Login fallito: " + ex.getMessage());
            redirect(response, "login.jsp");
        }
    }

}
