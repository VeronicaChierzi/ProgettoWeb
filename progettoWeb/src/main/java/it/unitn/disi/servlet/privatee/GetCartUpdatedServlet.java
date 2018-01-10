package it.unitn.disi.servlet.privatee;

import it.unitn.disi.controllers.CartController;
import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCartUpdatedServlet extends MyServlet {

	private ShopProductDAO shopProductDAO;

	@Override
	public void init() throws ServletException {
		shopProductDAO = (ShopProductDAO) initDao(ShopProductDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CartController.updateCart(request.getSession(), shopProductDAO);
		} catch (DAOException ex) {
			System.err.println("Errore DAOException in GetCartServlet: " + ex.getMessage());
			forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
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
