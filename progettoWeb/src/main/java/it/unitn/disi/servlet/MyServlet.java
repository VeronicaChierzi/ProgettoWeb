package it.unitn.disi.servlet;

import it.unitn.disi.dao.DAO;
import it.unitn.disi.dao.exceptions.DAOFactoryException;
import it.unitn.disi.dao.factories.DAOFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MyServlet extends HttpServlet {
	
	protected DAO initDao(Class daoClass) throws ServletException {
		DAOFactory daoFactory = (DAOFactory) super.getServletContext().getAttribute("daoFactory");
		if (daoFactory == null) {
			throw new ServletException("Impossible to get dao factory");
		}
		try {
			DAO dao = daoFactory.getDAO(daoClass);
			return dao;
		} catch (DAOFactoryException ex) {
			throw new ServletException("Impossible to get dao", ex);
		}
	}
	
	protected void redirect(HttpServletResponse response, String page) throws IOException {
		String contextPath = getServletContext().getContextPath();
		if (!contextPath.endsWith("/")) {
			contextPath += "/";
		}
		response.sendRedirect(response.encodeRedirectURL(contextPath + page));
	}

	protected void forward(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
