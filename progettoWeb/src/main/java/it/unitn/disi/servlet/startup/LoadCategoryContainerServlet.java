package it.unitn.disi.servlet.startup;

import it.unitn.disi.dao.CategoryContainerDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.categories.CategoryContainer;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadCategoryContainerServlet extends MyServlet {

	private CategoryContainerDAO categoryContainerDAO;

	@Override
	public void init() throws ServletException {
		//System.err.println("LoadCategoryContainerServlet init() inizio");
		categoryContainerDAO = (CategoryContainerDAO) initDao(CategoryContainerDAO.class);
		loadCategoryContainer();
		//System.err.println("LoadCategoryContainerServlet init() fine");
	}
	
	private void loadCategoryContainer(){
		//System.err.println("LoadCategoryContainerServlet loadCategoryContainer() inizio");
		ServletConfig sc = getServletConfig();
		if (Model.Application.getAttribute(sc, Model.Application.categoryContainer) == null) {
			try {
				CategoryContainer categoryContainer = categoryContainerDAO.getCategoryContainer();
				Model.Application.setAttribute(sc, Model.Application.categoryContainer, categoryContainer);
				System.err.println("Caricato CategoryContainer");
			} catch (DAOException ex) {
				System.err.println("Errore nel caricamento di CategoryContainer: " + ex.getMessage());
			}
		} else {
			System.err.println("CategoryContainer già caricato");
		}
		//System.err.println("LoadCategoryContainerServlet loadCategoryContainer() fine");
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