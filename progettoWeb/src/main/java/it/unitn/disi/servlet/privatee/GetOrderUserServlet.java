package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.SegnalazioneDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrderUserServlet extends MyServlet {

	private OrderDAO orderDAO;
	private SegnalazioneDAO segnalazioneDAO;

	@Override
	public void init() throws ServletException {
		orderDAO = (OrderDAO) initDao(OrderDAO.class);
		segnalazioneDAO = (SegnalazioneDAO) initDao(SegnalazioneDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idOrder = Integer.parseInt(request.getParameter("id"));
			User user = (User) Model.Session.getUserLogged(request); //necessario per verificare che sia un utente loggato
			try {
				Order o = orderDAO.getOrderUser(idOrder, user.getId());
				Model.Request.setAttribute(request, Model.Request.orderUser, o);
                                boolean a = segnalazioneDAO.isSegnalato(idOrder);
				Model.Request.setAttribute(request, Model.Request.isSegnalato, a);
			} catch (DAOException ex) {
				System.err.println("Errore DAOException in GetOrderUserServlet: " + ex.getMessage());
				forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
			}
		} catch (NumberFormatException e) {
			System.err.println("Errore NumberFormatException in GetOrderUserServlet: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Errore utente non loggato in GetOrderUserServlet: " + e.getMessage());
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
