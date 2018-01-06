package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.controllers.UserController;
import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationSellerLogServlet extends MyServlet {

	private UserDAO userDao;

	@Override
	public void init() throws ServletException {
		userDao = (UserDAO) initDao(UserDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nomeNeg = Model.Parameter.get(request, "nome_neg");
			String partitaIva = Model.Parameter.get(request, "partita_iva");

			//controllato che l'utente sia loggato e che non sia un venditore
			User user = (User) Model.Session.getUserLogged(request); //necessario per verificare che sia un utente loggato
			if (user.isSeller()) {
				Model.Messages.setBoolean(request, Model.Messages.registrationSellerLogFailedBoolean);
				Model.Messages.setString(request, Model.Messages.registrationSellerLogFailedString, "Sei già un venditore");
				redirect(response, MyPaths.Jsp.userRegistrationSellerLog);
				return;
			}

			//registra l'utente come venditore
			try {
				boolean b = UserController.registrationSellerLog(userDao, getServletContext(), request, nomeNeg, partitaIva);
				if (b) { //utente registrato
					redirect(response, MyPaths.Jsp.userProfile);
					return;
				} else { //registrazione fallita
					setInputField(request, nomeNeg, partitaIva);
					Model.Messages.setBoolean(request, Model.Messages.registrationFailedBoolean);
					redirect(response, MyPaths.Jsp.userRegistrationSellerLog);
					return;
				}
			} catch (DAOException ex) { //impossibile inserire nuovo utente
				System.err.println("Errore DAOException in RegistrationSellerLogServlet: " + ex.getMessage());
				forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
			}
		} catch (Exception e) {
			System.err.println("Errore utente non loggato in GetOrderSellerServlet: " + e.getMessage());
		}
	}

	private void setInputField(HttpServletRequest request, String nomeNeg, String partitaIva) {
		Model.Messages.setString(request, "nome_neg", nomeNeg);
		Model.Messages.setString(request, "partita_iva", partitaIva);
	}

}
