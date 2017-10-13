package it.unitn.disi.servlet;

import it.unitn.disi.dao.exceptions.DAOException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ErrorServlet", urlPatterns = {"/ErrorServlet"})
public class ErrorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOException ex = new DAOException("errore dao");
		throw new IOException("DAOException: " + ex.getMessage());
	}

}
