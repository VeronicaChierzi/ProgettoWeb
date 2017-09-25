package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends MyServlet {

	private UserDAO userDao;

	@Override
	public void init() throws ServletException {
		userDao = (UserDAO) initDao(UserDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String firstName = (String) request.getParameter("first_name");
		String lastName = (String) request.getParameter("last_name");
		try {
			boolean b = userDao.insertUser(username, email, password, firstName, lastName);
			if (b) { //utente inserito nel database
				forward(request, response, "/LoginServlet");
			} else { //utente non registrato. impostare messaggio di registrazione fallita da visualizzare
				redirect(response, "register.jsp");
			}
		} catch (DAOException ex) {
			//impossibile inserire nuovo utente
			System.err.println(ex.getMessage());
			redirect(response, "register.jsp");
		}
	}
}
