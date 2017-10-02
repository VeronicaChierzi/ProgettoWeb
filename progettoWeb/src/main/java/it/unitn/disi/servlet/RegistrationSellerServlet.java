package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.UserSeller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegistrationSellerServlet", urlPatterns = {"/RegistrationSellerServlet"})
public class RegistrationSellerServlet extends MyServlet {

	private UserDAO userDao;

	@Override
	public void init() throws ServletException {
		userDao = (UserDAO) initDao(UserDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeNeg = (String) request.getParameter("nomeNeg");
		String partitaIva = (String) request.getParameter("partita_iva");
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");	// L'utente deve essere loggato!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		int idUser = user.getId();
		//user.isSeller() deve essere false!!!!!! se è già un venditore, la query dovrebbe crashare
		try {
			boolean b = userDao.insertUserSeller(idUser, nomeNeg, partitaIva);
			if (b) { //utente inserito nel database
				//getUserSeller();
				UserSeller userSeller = userDao.getUserSeller(user.getId());
				user.setUserSeller(userSeller);
				
				forward(request, response, "/index.jsp");
			} else { //utente non registrato. impostare messaggio di registrazione fallita da visualizzare
				System.err.println("a");
				session.setAttribute("registrazioneVenditoreFallita", true);
				redirect(response, "registerSeller.jsp");
			}
		} catch (DAOException ex) {
			//impossibile inserire nuovo utente
				System.err.println("bbbb");
			System.err.println(ex.getMessage());
			session.setAttribute("registrazioneVenditoreFallita", true);
			redirect(response, "registerSeller.jsp");
		}
	}

}
