package it.unitn.disi.servlet.startup;

import it.unitn.disi.dao.LocationDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.LocationContainer;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadLocationContainerServlet extends MyServlet {

	private LocationDAO locationDao;

	@Override
	public void init() throws ServletException {
		//System.err.println("LoadLocationContainerServlet init() inizio");
		locationDao = (LocationDAO) initDao(LocationDAO.class);
		loadLocationContainer();
		//System.err.println("LoadLocationContainerServlet init() fine");
	}
	
	private void loadLocationContainer(){
		//System.err.println("LoadLocationContainerServlet loadLocationContainer() inizio");
		ServletConfig sc = getServletConfig();
		if (Model.Application.getAttribute(sc, Model.Application.locationContainer) == null) {
			try {
				LocationContainer locationContainer = locationDao.getLocation();
				Model.Application.setAttribute(sc, Model.Application.locationContainer, locationContainer);
				System.err.println("Caricato LocationContainer");
			} catch (DAOException ex) {
				System.err.println("Errore nel caricamento di LocationContainer: " + ex.getMessage());
			}
		} else {
			System.err.println("LocationContainer già caricato");
		}
		//System.err.println("LoadLocationContainerServlet loadLocationContainer() fine");
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.err.println("LoadCategoryContainerServlet processRequest. Non dovrebbe essere richiamato!!!");
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
