package it.unitn.disi.debug;

import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.jdbc.JDBCDAOFactory;
import it.unitn.disi.utils.CoordinateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luca Degani
 * @date 23-10-2017
 */
public class GeneraVenditaPuntiVenditaServlet extends HttpServlet {

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
        ArrayList<Integer> idPuntiVendita = new ArrayList<>();

        String queryInsert = "INSERT INTO public.shops_products(id_product, id_shop, price, quantity) VALUES(?, ?, ?, ?);";
        String querySelect = "select distinct id from (select id from products order by random() limit ?) as a;";

        JDBCDAOFactory a = (JDBCDAOFactory) getServletContext().getAttribute("daoFactory");
        Connection c = a.getConnection();

        String selectIdPuntiVendita = "SELECT id FROM public.shops;";
        try {
            PreparedStatement getId = c.prepareStatement(selectIdPuntiVendita);
            ResultSet risSselectIdPuntiVendita = getId.executeQuery();
            while (risSselectIdPuntiVendita.next()) {
                idPuntiVendita.add(risSselectIdPuntiVendita.getInt("id"));
            }

            for (int i = 0; i < idPuntiVendita.size(); i++) {

                int nProdotti = ThreadLocalRandom.current().nextInt(50, 100);
                ArrayList<Integer> idProdotti = new ArrayList<>(nProdotti);

                PreparedStatement getRandomProductsId = c.prepareStatement(querySelect);
                getRandomProductsId.setInt(1, nProdotti);
                ResultSet risSelectRandomProductsId = getRandomProductsId.executeQuery();
                while (risSelectRandomProductsId.next()) {
                    idProdotti.add(risSelectRandomProductsId.getInt("id"));
                }
                

                for (int j = 0; j < idProdotti.size(); j++) {

                    PreparedStatement ps = c.prepareStatement(queryInsert);
                    ps.setInt(2, idPuntiVendita.get(i));
                    ps.setInt(1, idProdotti.get(j));

                    //DECIDERE PREZZO
                    double prezzoProdStandard;
                    PreparedStatement getPrezzoProdotto = c.prepareStatement("select price_standard from products where id = ? limit 1;");
                    getPrezzoProdotto.setInt(1, idProdotti.get(j));
                    ResultSet risGetPrezzoProdotto = getPrezzoProdotto.executeQuery();
                    risGetPrezzoProdotto.next();
                    prezzoProdStandard = risGetPrezzoProdotto.getDouble("price_standard");
                    
                    double perc = ThreadLocalRandom.current().nextInt(0, 5) +0.0d;
                    int segno = ThreadLocalRandom.current().nextInt(0, 2);
                    
                    double prezzoProd = (segno == 0 ? prezzoProdStandard + prezzoProdStandard * perc / 10.0 : prezzoProdStandard - prezzoProdStandard * perc / 10.0);
                    
                    ps.setDouble(3, prezzoProd);
                    
                    //DECIDERE QTA
                    ps.setInt(4, ThreadLocalRandom.current().nextInt(0, 31));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
