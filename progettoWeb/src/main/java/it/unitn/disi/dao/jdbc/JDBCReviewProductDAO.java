package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ReviewProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.ReviewProduct;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Luca Degani
 */
public class JDBCReviewProductDAO extends JDBCDAO<ReviewProduct, Integer> implements ReviewProductDAO {

    private static final Class classe = ReviewProduct.class;
    private static final String[] nomiColonne = new String[]{"id", "id_order", "id_product", "title", "text", "datetime", "rate"};
    private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, int.class, String.class, String.class, Timestamp.class, int.class};

    public JDBCReviewProductDAO(Connection con) {
        super(con);
    }

    @Override
    public ReviewProduct getReviewById(int id) throws DAOException {
        String query = "SELECT * FROM reviews_products WHERE id=?";
        Object[] parametriQuery = new Object[]{id};
        ReviewProduct r = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        return r;
    }

    @Override
    public ReviewProduct[] getReviewsByProductId(int prodId) throws DAOException {
        String query = "SELECT * FROM reviews_products WHERE id_product=?";
        Object[] parametriQuery = new Object[]{prodId};
        ReviewProduct[] r = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        return r;
    }

    @Override
    public boolean addReview(int id_order, int id_product, int rate, String title, String text) throws DAOException{
        if (title == null || text == null) {
            throw new DAOException("Title e Text sono campi obbligatori", new NullPointerException("Title e text sono null"));
        }
        String query = "INSERT INTO public.reviews_products(id_order, id_product, title, text, datetime, rate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = CON.prepareStatement(query)) {
            ps.setInt(1, id_order);
            ps.setInt(2, id_product);
            ps.setString(3, title);
            ps.setString(4, text);
            ps.setDate(5, new Date(System.currentTimeMillis()));
            ps.setInt(6, rate);

            int result = -1; //quantità di righe modificate dalla query insert
            try {
                result = ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DAOException("Errore esecuzione query: " + ex);
            }
            boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
            return b;
        } catch (SQLException ex) {
            throw new DAOException("Errore preparedStatement o sintassi query: " + ex);
        }
    }

    @Override
    public boolean existReview(int id_order, int id_product) throws DAOException {
        String query = "SELECT * FROM reviews_products WHERE id_order=? AND id_product=?";
        Object[] parametriQuery = new Object[]{id_order, id_product};
        ReviewProduct r = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        if(r != null) {
            return true;
        }
        return false;
    }


}
