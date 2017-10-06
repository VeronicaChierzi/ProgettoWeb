/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webservice;

import com.google.gson.Gson;
import it.unitn.disi.dao.exceptions.DAOFactoryException;
import it.unitn.disi.dao.factories.jdbc.JDBCDAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.xml.ws.WebServiceContext;
import org.unbescape.html.HtmlEscape;

/**
 * REST Web Service
 *
 * @author root
 */
@Path("products")
public class AutocompleteServices {

    @Context
    private UriInfo ctx;

    @Context
    private HttpServletRequest request;

    @Resource
    private WebServiceContext context;

    /**
     * Creates a new instance of AutocompleteServices
     */
    public AutocompleteServices() {
    }

    /**
     * Retrieves representation of an instance of
     * it.unitn.science.computerScience.webDeveloping.exercise1.services.AutoCompleteService
     *
     * @param term
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{term}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getProducts(@PathParam("term") String term) {
        Gson gson = new Gson();
        List<String> prodotti = new ArrayList<>();
        /*LANGUAGES.stream().filter((language) -> (language.toLowerCase().contains(term.toLowerCase()))).forEach((_item) -> {
            results.add(_item);
        });*/

        JDBCDAOFactory a = null;
        Connection c = null;
        try {
            a = JDBCDAOFactory.getInstance();
            c = a.getConnection();
        } catch (DAOFactoryException ex) {
            Logger.getLogger(AutocompleteServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*String querySelectProdotto = "SELECT \"name\"\n"
                + "FROM products\n"
                + "WHERE to_tsvector(\"name\") @@ plainto_tsquery(?)\n"
                + "ORDER BY \"name\" ASC\n"
                + "LIMIT 5;";*/
        //String querySelectProdotto = "select name from products where \"name\" ilike ? OR \"name\" ilike ? order by \"name\" asc limit 5;";
        String querySelectProdotto = "select ris.\"name\"\n"
                + "from\n"
                + "((SELECT \"name\" FROM products WHERE to_tsvector(\"name\") @@ plainto_tsquery(?) LIMIT 5)\n"
                + "UNION\n"
                + "(select \"name\" from products where \"name\" ilike ? or \"name\" ilike ? order by \"name\" asc limit 5)) as ris\n"
                + "ORDER BY ris.\"name\" asc limit 7;";

        try {
            PreparedStatement getProds = c.prepareStatement(querySelectProdotto);
            getProds.setString(1, HtmlEscape.escapeHtml5(term));
            getProds.setString(2, HtmlEscape.escapeHtml5(term) + "%");
            getProds.setString(3, "%" + HtmlEscape.escapeHtml5(term) + "%");
            
            
            System.out.println(HtmlEscape.escapeHtml5(term));

            ResultSet risProds = getProds.executeQuery();
            System.out.println(getProds);
            
            

            while (risProds.next()) {
                prodotti.add(HtmlEscape.unescapeHtml(risProds.getString("name")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AutocompleteServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gson.toJson(prodotti);
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getProducts() {
        Gson gson = new Gson();
        return gson.toJson(new ArrayList());
    }
}
