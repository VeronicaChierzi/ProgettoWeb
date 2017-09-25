package it.unitn.disi.servlet;

import it.unitn.disi.dao.LocationDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.Location;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends MyServlet {

	private LocationDAO locationDao;

	@Override
	public void init() throws ServletException {
		locationDao = (LocationDAO) initDao(LocationDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ServletContext sc = request.getServletContext();
		if (sc.getAttribute("location") == null) {
			try {
				Location location = locationDao.getLocation();
				sc.setAttribute("location", location);
				session.setAttribute("locationMessage", "Location è stato caricato dal database. Dovrebbe comparire solo la prima volta!!!");
			} catch (DAOException ex) {
				System.err.println("Errore nel caricamento di Location");
				session.setAttribute("locationMessage", "Errore nel caricamento di Location");
			}
		} else {
			System.err.println("Location gia caricate");
			session.setAttribute("locationMessage", "Location gia caricate");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods">
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
		System.err.println("get");
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
		System.err.println("post");
		processRequest(request, response);
	}
	// </editor-fold>

}
