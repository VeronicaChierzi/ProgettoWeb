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
public class InserisciProdottiServlet extends HttpServlet {

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
        String queryInsertProdotto = "INSERT INTO public.products(\"name\", description, id_subcategory, price_standard) VALUES(?, ?, ?, ?);";
        String querySelectSubcategories = "select id from subcategories where \"name\" ILIKE ?;";
        String querySelectProdotto = "select id from products where \"name\" ilike ? order by \"id\" desc limit 1;";
        String queryInsertImmagine = "INSERT INTO images(id_product, \"path\", alt) values (?, ?, ?);";

        String[] prodotti = input.split("==");
        
        /*prodotti[i] :
        *   i = 0: nome
        *   i = 1: descrizione
        *   i = 2: prezzo
        *   i = 3: categoria
        *   i = 4: sottoCategoria
        *   i = 5: filenameImmagine
        */

        for (int i = 0; i < prodotti.length; i+=6) {
            try (PreparedStatement ps = c.prepareStatement(queryInsertProdotto)) {
                ps.setString(1, prodotti[i]);
                ps.setString(2, prodotti[i+1]);
                ps.setDouble(4, Double.parseDouble(prodotti[i+2]));
                
                
                PreparedStatement getIdSubcat = c.prepareStatement(querySelectSubcategories);
                getIdSubcat.setString(1, prodotti[i+4]);
                ResultSet risIdSubcat = getIdSubcat.executeQuery();
                
                System.out.println(getIdSubcat);
                
                risIdSubcat.next();
                int idSubcat = risIdSubcat.getInt("id");
                
                
                                

                System.out.println(ps);
                ps.setInt(3, idSubcat);                

                try {
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    System.err.println("Impossibile eseguire query qui: " + ex.getMessage());

                }
                
                
                
                //PRODOTTO INSERITO
                
                PreparedStatement getIdProdInserito = c.prepareStatement(querySelectProdotto);
                getIdProdInserito.setString(1, prodotti[i]);
                ResultSet risIdProdInserito = getIdProdInserito.executeQuery();
                risIdProdInserito.next();
                int idProdInserito = risIdProdInserito.getInt("id");
                

                
                PreparedStatement psImm = c.prepareStatement(queryInsertImmagine);
                psImm.setInt(1, idProdInserito); 
                psImm.setString(2, "/progettoWeb/" + prodotti[i+3] + "/" + prodotti[i+4] + "/" + prodotti[i+5]);
                psImm.setString(3, prodotti[i]);
                
                try {
                    psImm.executeUpdate();
                } catch (SQLException ex) {
                    System.err.println("Impossibile eseguire query 2: " + ex.getMessage());

                }
                
            } catch (SQLException ex) {
                System.err.println("Impossibile eseguire query 3: " + ex.getMessage());
            }
        }
    }

}
