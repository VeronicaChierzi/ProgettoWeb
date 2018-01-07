package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.controllers.CartController;
import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyCartServlet extends MyServlet {

	private OrderDAO orderDAO;
	private ShopProductDAO shopProductDAO;

	@Override
	public void init() throws ServletException {
		orderDAO = (OrderDAO) initDao(OrderDAO.class);
		shopProductDAO = (ShopProductDAO) initDao(ShopProductDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = Model.Session.getUserLogged(request);
			try {
				HttpSession session = request.getSession();
				if(CartController.buyCart(session, user, shopProductDAO, orderDAO))
				redirect(response, MyPaths.Jsp.allHome);
			} catch (DAOException ex) {
				System.err.println("Errore buy servlet: " + ex.getMessage());
				Model.Messages.setBoolean(request, "buyCartFailed");
				redirect(response, MyPaths.Jsp.allCart);
			}
		} catch (Exception e) {
			System.err.println("Errore utente non loggato in BuyCartServlet: " + e.getMessage());
		}
	}

}
