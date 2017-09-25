package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends MyServlet {

	private UserDAO userDao;

	@Override
	public void init() throws ServletException {
		userDao = (UserDAO) initDao(UserDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		try {
			User user = userDao.getByUsernameAndPassword(username, password);
			if (user != null) {
				//utente loggato
				session.setAttribute("user", user);
				redirect(response, "user/profilo.jsp");
			} else {
				//query eseguita senza errori, ma che non ha dato nessun risultato
				//utente non loggato. impostare messaggio di login fallito da visualizzare
				session.setAttribute("loginFallito", "Login fallito: username o password errati");
				redirect(response, "login.jsp");
			}
		} catch (DAOException ex) {
			//la query ha generato un errore
			//utente non loggato. impostare messaggio di login fallito da visualizzare
			session.setAttribute("loginFallito", "Login fallito: " + ex.getMessage());
			redirect(response, "login.jsp");
		}
	}

}
