package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ImageDAO;
import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.ReviewProductDAO;
import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.Product;
import java.sql.Connection;
import javax.servlet.ServletException;
import org.unbescape.html.HtmlEscape;

public class JDBCProductDAO extends JDBCDAO<Product, Integer> implements ProductDAO {

    private static final Class classe = Product.class;
    private static final String[] nomiColonne = new String[]{"id", "name", "description", "id_subcategory"};
    private static final Class[] constructorParameterTypes = new Class[]{int.class, String.class, String.class, int.class};

    private ShopProductDAO shopProductDAO;
    private ImageDAO imageDAO;
    private ReviewProductDAO reviewProductDAO;

    public JDBCProductDAO(Connection con) throws ServletException {
        super(con);
    }

    @Override
    public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
        shopProductDAO = (ShopProductDAO) initDao(ShopProductDAO.class, daoFactory);
        imageDAO = (ImageDAO) initDao(ImageDAO.class, daoFactory);
        reviewProductDAO = (ReviewProductDAO) initDao(ReviewProductDAO.class, daoFactory);
    }

    private void setPointers(Product p, boolean loadMinShopProduct, boolean loadImage, boolean loadReviews) throws DAOException {
        if (p != null) {
            if (loadMinShopProduct) {
                p.setShopProduct(shopProductDAO.getMinShopProduct(p.getId(), p));
            }
            if (loadImage) {
                p.setImage(imageDAO.getProductImage(p.getId()));
            }
            if(loadReviews) {
                p.setReview(reviewProductDAO.getReviewsByProductId(p.getId()));
            }
        }
    }

    @Override
    public Product getProduct(int id, boolean loadMinShopProduct, boolean loadImage, boolean loadReview) throws DAOException {
        String query = "SELECT * FROM products WHERE id = ?";
        Object[] parametriQuery = new Object[]{id};
        Product p = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        setPointers(p, loadMinShopProduct, loadImage, loadReview);
        return p;
    }

    @Override
    public Product[] searchProducts(String text, int offset) throws DAOException {
        String query = "select distinct on (\"name\") * from\n"
                + "((SELECT * FROM products WHERE to_tsvector(\"name\") @@ plainto_tsquery(?))\n"
                + "UNION\n"
                + "(select * from products where \"name\" ilike ? or \"name\" ilike ? order by \"name\" asc)) as ris\n"
                + "ORDER BY ris.\"name\" asc LIMIT 10 OFFSET ?;";
        Object[] parametriQuery = new Object[]{
            HtmlEscape.escapeHtml5(text),
            HtmlEscape.escapeHtml5(text) + "%",
            "%" + HtmlEscape.escapeHtml5(text) + "%",
            offset
        };
        Product[] productList = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        for (Product p : productList) {
            setPointers(p, true, true, true);
        }
        return productList;
    }

    @Override
    public Product[] getProductsByCategory(int cat, int offset) throws DAOException {
        String query = "SELECT * FROM products WHERE id_subcategory = ? ORDER BY \"name\" asc LIMIT 10 OFFSET ?;";
        Object[] parametriQuery = new Object[]{cat, offset};
        Product[] productList = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        for (Product p : productList) {
            setPointers(p, true, true, true);
        }
        return productList;
    }
    
}
