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

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends MyServlet {

	private LocationDAO locationDao;
	private Location location;

	@Override
	public void init() throws ServletException {
		locationDao = (LocationDAO) initDao(LocationDAO.class);
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("Richiamata Servlet LocationServlet at " + request.getContextPath() + "<br/>");

			ServletContext sc = request.getServletContext();
			if (sc.getAttribute("location") == null) {
				System.err.println("load loca");
				out.println("Caricamento Location dal database. Dovrebbe comparire solo la prima volta!!!<br/>");
				System.err.println("ok4");
				try {
					location = locationDao.getLocation();
					System.err.println("ok5");
					sc.setAttribute("location", location);
					System.err.println("ok6");
				} catch (DAOException ex) {
					System.err.println("not ok");
					out.println("Errore nel caricamento di Location dal database<br/>");
				}
			} else {
				System.err.println("not load loca");
				out.println("Location già caricata<br/>");
			}
			System.err.println("finished servlet");
			System.err.println("SI");
			//forward(request, response, "index.jsp");
			System.err.println("NO");
			//forward(request, response, "location.jsp");
			redirect(response, "diocane.jsp");
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
