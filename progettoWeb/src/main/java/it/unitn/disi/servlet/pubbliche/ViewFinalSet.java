package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewFinalSet", urlPatterns = {"/ViewFinal/Set"})
public class ViewFinalSet extends MyServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.err.println("Vista attuale impostata su ViewFinal");
		MyPaths.Jsp.setModeFinal();
		redirect(response, MyPaths.Jsp.allHome);
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
