/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.debug;

import it.unitn.disi.dao.factories.jdbc.JDBCDAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class InserisciSottocategorieServlet extends HttpServlet {

    

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
        String queryInsert = "INSERT INTO subcategories(name, id_category) VALUES (?, ?);";
        String querySelect = "select id from categories where \"name\" LIKE ?;";

        String[] subCategorie = input.split(",");

        for (int i = 0; i < subCategorie.length; i+=2) {
            try (PreparedStatement ps = c.prepareStatement(queryInsert)) {
                ps.setString(1, subCategorie[i+1]);
                
                PreparedStatement getId = c.prepareStatement(querySelect);
                getId.setString(1, subCategorie[i]);
                ResultSet risId = getId.executeQuery();
                risId.next();
                int id = risId.getInt("id");
                
                ps.setInt(2, id);
                

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
