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

public class RegistrationServlet extends MyServlet {

	private UserDAO userDao;

	@Override
	public void init() throws ServletException {
		userDao = (UserDAO) initDao(UserDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = Model.Parameter.get(request, "username");
		String email = Model.Parameter.get(request, "email");
		String password = Model.Parameter.get(request, "password");
		String password2 = Model.Parameter.get(request, "password2");
		String firstName = Model.Parameter.get(request, "first_name");
		String lastName = Model.Parameter.get(request, "last_name");

		//registra utente
		try {
			boolean b = UserController.registration(userDao, getServletContext(), request, username, email, password, password2, firstName, lastName);
			if (b) { //utente registrato e loggato
				redirect(response, MyPaths.Jsp.userProfile);
				return;
			} else { //registrazione fallita
				setInputField(request, username, email, firstName, lastName);
				Model.Messages.setBoolean(request, Model.Messages.registrationFailedBoolean);
				redirect(response, MyPaths.Jsp.anonymousRegistration);
				return;
			}
		} catch (DAOException ex) { //impossibile inserire nuovo utente
			System.err.println("Errore DAOException in RegistrationServlet: " + ex.getMessage());
			forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
		}
	}

	private void setInputField(HttpServletRequest request, String username, String email, String firstName, String lastName) {
		Model.Messages.setString(request, "username", username);
		Model.Messages.setString(request, "email", email);
		Model.Messages.setString(request, "first_name", firstName);
		Model.Messages.setString(request, "last_name", lastName);
	}
}
