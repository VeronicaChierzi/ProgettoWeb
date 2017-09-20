/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.exceptions.DAOFactoryException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class LoginServlet extends HttpServlet {

    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = (DAOFactory) super.getServletContext().getAttribute("daoFactory");
        if (daoFactory == null) {
            throw new ServletException("Impossible to get dao factory for user storage systemaaaa");
        }
        try {
            userDao = daoFactory.getDAO(UserDAO.class);
        } catch (DAOFactoryException ex) {
            throw new ServletException("Impossible to get dao factory for user storage system", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String contextPath = getServletContext().getContextPath();
        if (!contextPath.endsWith("/")) {
            contextPath += "/";
        }

        try {
            User user = userDao.getByUsernameAndPassword(username, password);
            if (user == null) {
                response.sendRedirect(response.encodeRedirectURL(contextPath + "login.html"));
            } else {
                request.getSession().setAttribute("user", user);
                /*if (user.getUsername().equals("stefano.chirico@unitn.it")) {
                    response.sendRedirect(response.encodeRedirectURL(contextPath + "restricted/users.html"));
                } else {
                    response.sendRedirect(response.encodeRedirectURL(contextPath + "restricted/todos.html?id=" + user.getId()));
                }*/
                response.sendRedirect(response.encodeRedirectURL(contextPath + "user/profilo.jsp"));
            }
        } catch (DAOException ex) {
            //TODO: log exception
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
