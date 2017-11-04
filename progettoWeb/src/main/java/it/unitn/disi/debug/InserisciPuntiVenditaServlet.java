/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.debug;

import it.unitn.disi.dao.factories.jdbc.JDBCDAOFactory;
import it.unitn.disi.utils.CoordinateUtil;
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
public class InserisciPuntiVenditaServlet extends HttpServlet {

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

        int ownerID[] = {9, 10, 11, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

        JDBCDAOFactory a = (JDBCDAOFactory) getServletContext().getAttribute("daoFactory");
        Connection c = a.getConnection();

        String input = request.getParameter("data");
        String queryInsert = "INSERT INTO public.shops(id_owner, latitude, longitude, address, id_comune) VALUES(?, ?, ?, ?, ?);";
        String querySelect = "SELECT id FROM public.comuni where \"name\" ilike ?;";

        String[] dati = input.split(",");

        for (int i = 0; i < dati.length; i += 2) {
            try (PreparedStatement ps = c.prepareStatement(queryInsert)) {
                //INDIRIZZO
                ps.setString(4, dati[i]);

                //COMUNE
                PreparedStatement getId = c.prepareStatement(querySelect);
                getId.setString(1, dati[i + 1]);
                ResultSet risId = getId.executeQuery();
                risId.next();
                int id = risId.getInt("id");
                ps.setInt(5, id);

                //ID OWNER
                ps.setInt(1, ownerID[i % ownerID.length]);

                //lat-lon
                float[] coo = CoordinateUtil.addressToCoordinates(dati[i]);
                ps.setFloat(2, coo[0]);
                ps.setFloat(3, coo[1]);

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
