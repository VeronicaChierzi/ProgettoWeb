package it.unitn.disi.servlet;

import it.unitn.disi.controllers.CartController;
import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BuyServlet", urlPatterns = {"/BuyServlet"})
public class BuyServlet extends MyServlet {

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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			CartController.checkCart(session, user, shopProductDAO);
			orderDAO.buyCart(CartController.getOrCreateCart(session));
			redirect(response, "index.jsp");
		} catch (DAOException ex) {
			System.err.println("Errore buy servlet: " + ex.getMessage());
			redirect(response, "cart.jsp");
		}
	}

}
