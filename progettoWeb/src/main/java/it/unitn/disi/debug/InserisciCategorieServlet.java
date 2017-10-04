/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.debug;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.jdbc.JDBCDAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class InserisciCategorieServlet extends HttpServlet {

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

        JDBCDAOFactory a = (JDBCDAOFactory) getServletContext().getAttribute("daoFactory");
        Connection c = a.getConnection();

        String input = request.getParameter("data");
        String query = "INSERT INTO categories(name) VALUES (?);";

        String[] categorie = input.split(",");

        for (int i = 0; i < categorie.length; i++) {
            try (PreparedStatement ps = c.prepareStatement(query)) {
                ps.setString(1, categorie[i]);

                try {
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    System.err.println("Impossibile eseguire query: " + ex.getMessage());

                }
            } catch (SQLException ex) {
                System.err.println("Impossibile eseguire query: " + ex.getMessage());
            }
        }

    }

}
