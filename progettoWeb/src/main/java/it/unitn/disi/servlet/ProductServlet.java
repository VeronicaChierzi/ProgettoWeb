package it.unitn.disi.servlet;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unitn.disi.dao.AProductDAO;

@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends MyServlet {

	private AProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		productDAO = (AProductDAO) initDao(AProductDAO.class);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession(true);
		try {
			Product product = productDAO.getProductByID(id);
			if (product != null) {
				request.setAttribute("product", product);
				redirect(response, "user/profilo.jsp");
			} else {
				//query eseguita senza errori, ma che non ha dato nessun risultato
				//utente non loggato. impostare messaggio di login fallito da visualizzare
				session.setAttribute("loginFallito", "Login fallito: username o password errati");
				redirect(response, "login.jsp");
			}
		} catch (DAOException ex) {
			//la query ha generato un errore
			//utente non loggato. impostare messaggio di login fallito da visualizzare
			session.setAttribute("loginFallito", "Login fallito: " + ex.getMessage());
			redirect(response, "login.jsp");
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
