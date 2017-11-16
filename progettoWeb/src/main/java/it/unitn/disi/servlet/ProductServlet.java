package it.unitn.disi.servlet;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unitn.disi.dao.ProductDAO;

@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends MyServlet {

	private ProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		productDAO = (ProductDAO) initDao(ProductDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Product product = productDAO.getProduct(id);
			if (product != null) {
				request.setAttribute("product", product);
				forward(request, response, "/product.jsp");
			} else { //prodotto non trovato
				forward(request, response, "/product.jsp");
			}
		} catch (DAOException ex) {
			System.out.println(ex.getMessage());
			forward(request, response, "/product.jsp");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	// </editor-fold>

}
