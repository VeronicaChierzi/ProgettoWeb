package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductListServlet extends MyServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = (ProductDAO) initDao(ProductDAO.class);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = Model.Parameter.get(request, Model.Parameter.textSearch);
        String category = Model.Parameter.get(request, Model.Parameter.category);
        if (search != "") {
            try {
                Product[] productList = productDAO.searchProducts(search);
                if (productList != null) {
                    Model.Request.setAttribute(request, "products", productList);
                }
            } catch (DAOException ex) {
                System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
            }
        } else if(category != "" && !Pattern.matches("[a-zA-Z]+", category)) {
            try {
                
                Product[] productList = productDAO.getProductsByCategory(Integer.parseInt(category));
                if (productList != null) {
                    Model.Request.setAttribute(request, "products", productList);
                }
            } catch (DAOException ex) {
                System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
            }
        } else { //restituisco comunque tutti i prodotti
            try {
                Product[] productList = productDAO.searchProducts(search);
                if (productList != null) {
                    Model.Request.setAttribute(request, "products", productList);
                }
            } catch (DAOException ex) {
                System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
            }
        }

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
