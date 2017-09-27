package it.unitn.disi.servlet;

import it.unitn.disi.dao.CategoryContainerDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.categories.CategoryContainer;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CategoryContainerServlet", urlPatterns = {"/CategoryContainerServlet"})
public class CategoryContainerServlet extends MyServlet {

	private CategoryContainerDAO categoryContainerDAO;

	@Override
	public void init() throws ServletException {
		categoryContainerDAO = (CategoryContainerDAO) initDao(CategoryContainerDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ServletContext sc = request.getServletContext();
		if (sc.getAttribute("categoryContainer") == null) {
			try {
				CategoryContainer categoryContainer = categoryContainerDAO.getCategoryContainer();
				sc.setAttribute("categoryContainer", categoryContainer);
				session.setAttribute("locationMessage", "categoryContainer è stato caricato dal database. Dovrebbe comparire solo la prima volta!!!");
			} catch (DAOException ex) {
				System.err.println("Errore nel caricamento di categoryContainer: " + ex.getMessage());
				session.setAttribute("categoryContainerMessage", "Errore nel caricamento di categoryContainer");
			}
		} else {
			System.err.println("categoryContainer gia caricate");
			session.setAttribute("categoryContainerMessage", "categoryContainer gia caricato");
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

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
