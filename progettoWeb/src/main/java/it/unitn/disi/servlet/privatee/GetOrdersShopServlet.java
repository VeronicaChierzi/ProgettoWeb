package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.OrderDAO;
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

public class GetOrdersShopServlet extends MyServlet {

	private OrderDAO orderDAO;

	@Override
	public void init() throws ServletException {
		orderDAO = (OrderDAO) initDao(OrderDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idShop = Integer.parseInt(request.getParameter("id"));
			User user = (User) Model.Session.getUserSellerLogged(request); //necessario per verificare che sia un venditore
			try {
				Order[] orders = orderDAO.getOrdersShop(idShop, user.getId());
				Model.Request.setAttribute(request, Model.Request.ordersShop, orders);
			} catch (DAOException ex) {
				System.err.println("Errore DAOException in GetOrdersShopServlet: " + ex.getMessage());
				forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
			}
		} catch (NumberFormatException e) {
			System.err.println("Errore NumberFormatException in GetOrdersShopServlet: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Errore utente non loggato in GetOrdersShopServlet: " + e.getMessage());
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
