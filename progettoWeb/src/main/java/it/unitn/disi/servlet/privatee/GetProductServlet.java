package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductServlet extends MyServlet {

	private ProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		productDAO = (ProductDAO) initDao(ProductDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Product product = productDAO.getProductByID(id);
			Model.Request.setAttribute(request, Model.Request.product, product);
		} catch (DAOException ex) {
			System.err.println("Errore DAOException in GetProductServlet: " + ex.getMessage());
			forward(request, response, MyPaths.Private.Jsp.ErrorPages.errorDAOException);
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
