package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.ReviewProduct;
import it.unitn.disi.entities.ShopProduct;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetShopsProductServlet extends MyServlet {

	private ShopProductDAO shopProductDAO;

	@Override
	public void init() throws ServletException {
		shopProductDAO = (ShopProductDAO) initDao(ShopProductDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idProduct = -1;
		try {
			idProduct = Integer.parseInt(request.getParameter("id"));
			try {
				ShopProduct[] shopsProduct = shopProductDAO.getShopsProductsByIdProduct(idProduct);
				Model.Request.setAttribute(request, "shopsProduct", shopsProduct);
			} catch (DAOException ex) {
				System.err.println("Errore DAOException in GetShopsProductServlet: " + ex.getMessage());
				forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
			}
		} catch (NumberFormatException e) {
			System.err.println("Errore NumberFormatException in GetProductServlet: " + e.getMessage());
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
