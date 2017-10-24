package it.unitn.disi.servlet;

import it.unitn.disi.dao.SegnalazioneDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Segnalazione;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SegnalazioneListServlet", urlPatterns = {"/SegnalazioneListServlet"})
public class SegnalazioneListServlet extends MyServlet {
	
	private SegnalazioneDAO segnalazioneDAO;

	@Override
	public void init() throws ServletException {
		segnalazioneDAO = (SegnalazioneDAO) initDao(SegnalazioneDAO.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		try {
			Segnalazione[] segnalazioni = segnalazioneDAO.getAllSegnalazioni();
			if (segnalazioni != null) {
				request.setAttribute("segnalazioni", segnalazioni);
				forward(request, response, "/WEB-INF/jsp/admin/SegnalazioneList.jsp");
			}
		} catch (DAOException ex) {
			System.err.println(ex.getMessage());
			forward(request, response, "/WEB-INF/jsp/admin/SegnalazioneList.jsp");
		}
	}
}
