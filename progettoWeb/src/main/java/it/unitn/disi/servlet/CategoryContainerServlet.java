package it.unitn.disi.servlet;

import it.unitn.disi.dao.CategoryContainerDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.categories.CategoryContainer;
import it.unitn.disi.utils.Model;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryContainerServlet", urlPatterns = {"/CategoryContainerServlet"})
public class CategoryContainerServlet extends MyServlet {

	private CategoryContainerDAO categoryContainerDAO;

	@Override
	public void init() throws ServletException {
		categoryContainerDAO = (CategoryContainerDAO) initDao(CategoryContainerDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (Model.Application.getCategoryContainer(request) == null) {
			try {
				CategoryContainer categoryContainer = categoryContainerDAO.getCategoryContainer();
				Model.Application.setCategoryContainer(request, categoryContainer);
				Model.Messages.setCategoryContainerLoaded(request);
			} catch (DAOException ex) {
				System.err.println("Errore nel caricamento di categoryContainer: " + ex.getMessage());
				Model.Messages.setCategoryContainerError(request);
			}
		} else {
			System.err.println("categoryContainer gia caricate");
			Model.Messages.setCategoryContainerAlreadyLoaded(request);
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
