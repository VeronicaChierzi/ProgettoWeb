package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.controllers.UserController;
import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends MyServlet {

	private UserDAO userDao;

	@Override
	public void init() throws ServletException {
		userDao = (UserDAO) initDao(UserDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usernameEmail = Model.Parameter.get(request, "username");
		String password = Model.Parameter.get(request, "password");
		try {
			boolean loggato = UserController.login(userDao, request, usernameEmail, password);
			if (loggato == true) {
				redirect(response, MyPaths.Public.Jsp.User.profile);
			} else {
				redirect(response, MyPaths.Public.Jsp.Anonymous.login);
			}
		} catch (DAOException ex) { //utente non loggato a causa di un eccezione nell'eseguire la query
			System.err.println("Errore DAOException in LoginServlet: " + ex.getMessage());
			forward(request, response, MyPaths.Private.Jsp.ErrorPages.errorDAOException);
		}
	}
}
