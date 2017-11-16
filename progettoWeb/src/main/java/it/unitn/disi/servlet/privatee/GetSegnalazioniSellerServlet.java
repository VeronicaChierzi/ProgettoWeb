package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.SegnalazioneDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Segnalazione;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSegnalazioniSellerServlet extends MyServlet {

	private SegnalazioneDAO segnalazioneDAO;

	@Override
	public void init() throws ServletException {
		segnalazioneDAO = (SegnalazioneDAO) initDao(SegnalazioneDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = (User) Model.Session.getUserSellerLogged(request); //necessario per verificare che sia un venditore
			try {
				Segnalazione[] s = segnalazioneDAO.getSegnalazioniByIdSeller(user.getId());
				Model.Request.setAttribute(request, Model.Request.segnalazioniSeller, s);
			} catch (DAOException ex) {
				System.err.println("Errore DAOException in GetSegnalazioniSellerServlet: " + ex.getMessage());
				forward(request, response, MyPaths.Private.Jsp.ErrorPages.errorDAOException);
			}
		} catch (NumberFormatException e) {
			System.err.println("Errore NumberFormatException in GetSegnalazioniSellerServlet: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Errore utente non loggato o non venditore in GetSegnalazioniSellerServlet: " + e.getMessage());
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods">
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	// </editor-fold>
}
