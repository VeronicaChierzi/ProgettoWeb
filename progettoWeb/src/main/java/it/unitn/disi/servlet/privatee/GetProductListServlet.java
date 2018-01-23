package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import it.unitn.disi.utils.ProductUtil;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductListServlet extends MyServlet {

    private ProductDAO productDAO;
    private ShopDAO shopDAO;

    @Override
    public void init() throws ServletException {
        productDAO = (ProductDAO) initDao(ProductDAO.class);
        shopDAO = (ShopDAO) initDao(ShopDAO.class);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = Model.Parameter.get(request, Model.Parameter.textSearch);
        String category = Model.Parameter.get(request, Model.Parameter.category);
        String sort = Model.Parameter.get(request, Model.Parameter.sort);
        String off = Model.Parameter.get(request, Model.Parameter.offset);

        Model.Request.setAttribute(request, "shopDAO", shopDAO);

        int offset = 0;
        if (!off.equals("") && !Pattern.matches("[a-zA-Z]+", off)) {
            offset = Integer.parseInt(off);
            if (offset < 0) {
                offset = -offset;
            }
        }

        if (!search.equals("")) {
            if (sort.equals("")) {
                try {
                    Product[] productList = productDAO.searchProducts(search, offset);
                    ProductUtil.mergeSortPrice(productList);
                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else if (sort.equalsIgnoreCase("price")) {
                try {
                    Product[] productList = productDAO.searchProducts(search, offset);
                    ProductUtil.mergeSortPrice(productList);

                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else if (sort.equalsIgnoreCase("review")) {
                try {
                    Product[] productList = productDAO.searchProducts(search, offset);
                    ProductUtil.mergeSortReview(productList);
                    ProductUtil.rovesciaArray(productList);
                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else {
                try {
                    Product[] productList = productDAO.searchProducts(search, offset);
                    ProductUtil.mergeSortPrice(productList);
                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            }
        } else if (!category.equals("") && !Pattern.matches("[a-zA-Z]+", category)) {
            if (sort.equals("")) {
                try {
                    Product[] productList = productDAO.getProductsByCategory(Integer.parseInt(category), offset);
                    ProductUtil.mergeSortPrice(productList);
                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else if (sort.equalsIgnoreCase("price")) {
                try {
                    Product[] productList = productDAO.getProductsByCategory(Integer.parseInt(category), offset);
                    ProductUtil.mergeSortPrice(productList);

                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else if (sort.equalsIgnoreCase("review")) {
                try {
                    Product[] productList = productDAO.getProductsByCategory(Integer.parseInt(category), offset);
                    ProductUtil.mergeSortReview(productList);
                    ProductUtil.rovesciaArray(productList);

                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else {
                try {
                    Product[] productList = productDAO.getProductsByCategory(Integer.parseInt(category), offset);
                    ProductUtil.mergeSortPrice(productList);
                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            }
        } else { //restituisco comunque tutti i prodotti
            try {
                Product[] productList = productDAO.searchProducts(search, offset);
                ProductUtil.mergeSortPrice(productList);

                if (productList != null) {
                    Model.Request.setAttribute(request, "count", productList.length);
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
