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

        String categoria = Model.Parameter.get(request, Model.Parameter.categoria); //dalla ricerca avanzata
        String rate = Model.Parameter.get(request, Model.Parameter.rate); //dalla ricerca avanzata

        boolean limitaCat = false;
        int catLimit = 0;
        if (!categoria.equals("") && !Pattern.matches("[a-zA-Z]+", categoria)) {
            catLimit = Integer.parseInt(categoria);
            limitaCat = true;
            if (catLimit < 0) {
                limitaCat = false;
            }
        }

        boolean limitaRate = false;
        int rateLimit = 0;
        if (!rate.equals("") && !Pattern.matches("[a-zA-Z]+", rate)) {
            limitaRate = true;
            rateLimit = Integer.parseInt(rate);
            if (rateLimit <= 0 || rateLimit > 5) {
                rateLimit = 0;
                limitaRate = false;
            }
        }

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
                if (limitaCat && limitaRate) {
                    try {
                        Product[] productList = productDAO.searchProductsByCategoryAndRating(search, catLimit, offset, rateLimit);
                        ProductUtil.mergeSortPrice(productList);
                        if (productList != null) {
                            Model.Request.setAttribute(request, "count", productList.length);
                            Model.Request.setAttribute(request, "products", productList);
                        }
                    } catch (DAOException ex) {
                        System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                        forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                    }
                } else if (limitaCat) {
                    try {
                        Product[] productList = productDAO.searchProductsByCategory(search, catLimit, offset);
                        ProductUtil.mergeSortPrice(productList);
                        if (productList != null) {
                            Model.Request.setAttribute(request, "count", productList.length);
                            Model.Request.setAttribute(request, "products", productList);
                        }
                    } catch (DAOException ex) {
                        System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                        forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                    }
                } else if (limitaRate) {
                    try {
                        Product[] productList = productDAO.searchProductsByRating(search, offset, rateLimit);
                        ProductUtil.mergeSortPrice(productList);
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

            } else if (sort.equalsIgnoreCase("price")) {
                if (limitaCat && limitaRate) {
                    try {
                        Product[] productList = productDAO.searchProductsByCategoryAndRating(search, catLimit, offset, rateLimit);
                        ProductUtil.mergeSortPrice(productList);

                        if (productList != null) {
                            Model.Request.setAttribute(request, "count", productList.length);
                            Model.Request.setAttribute(request, "products", productList);
                        }
                    } catch (DAOException ex) {
                        System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                        forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                    }
                } else if (limitaCat) {
                    try {
                        Product[] productList = productDAO.searchProductsByCategory(search, catLimit, offset);
                        ProductUtil.mergeSortPrice(productList);

                        if (productList != null) {
                            Model.Request.setAttribute(request, "count", productList.length);
                            Model.Request.setAttribute(request, "products", productList);
                        }
                    } catch (DAOException ex) {
                        System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                        forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                    }
                } else if (limitaRate) {
                    try {
                        Product[] productList = productDAO.searchProductsByRating(search, offset, rateLimit);
                        ProductUtil.mergeSortPrice(productList);

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
            } else if (sort.equalsIgnoreCase("review")) {
                if (limitaCat && limitaRate) {
                    try {
                        Product[] productList = productDAO.searchProductsByCategoryAndRating(search, catLimit, offset, rateLimit);
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
                } else if (limitaCat) {
                    try {
                        Product[] productList = productDAO.searchProductsByCategory(search, catLimit, offset);
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
                } else if (limitaRate) {
                    try {
                        Product[] productList = productDAO.searchProductsByRating(search, offset, rateLimit);
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
                }

            } else {
                if (limitaCat && limitaRate) {
                    try {
                        Product[] productList = productDAO.searchProductsByCategoryAndRating(search, catLimit, offset, rateLimit);
                        ProductUtil.mergeSortPrice(productList);
                        if (productList != null) {
                            Model.Request.setAttribute(request, "count", productList.length);
                            Model.Request.setAttribute(request, "products", productList);
                        }
                    } catch (DAOException ex) {
                        System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                        forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                    }
                } else if (limitaCat) {
                    try {
                        Product[] productList = productDAO.searchProductsByCategory(search, catLimit, offset);
                        ProductUtil.mergeSortPrice(productList);
                        if (productList != null) {
                            Model.Request.setAttribute(request, "count", productList.length);
                            Model.Request.setAttribute(request, "products", productList);
                        }
                    } catch (DAOException ex) {
                        System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                        forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                    }
                } else if (limitaRate) {
                    try {
                        Product[] productList = productDAO.searchProductsByRating(search, offset, rateLimit);
                        ProductUtil.mergeSortPrice(productList);
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
            if (limitaCat && limitaRate) {
                try {
                    Product[] productList = productDAO.searchProductsByCategoryAndRating(search, catLimit, offset, rateLimit);
                    ProductUtil.mergeSortPrice(productList);
                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else if (limitaCat) {
                try {
                    System.out.println("aasasasasasaskmsdvkjadv");
                    Product[] productList = productDAO.searchProductsByCategory(search, catLimit, offset);
                    ProductUtil.mergeSortPrice(productList);
                    if (productList != null) {
                        Model.Request.setAttribute(request, "count", productList.length);
                        Model.Request.setAttribute(request, "products", productList);
                    }
                } catch (DAOException ex) {
                    System.err.println("Errore DAOException in GetProductListServlet: " + ex.getMessage());
                    forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
                }
            } else if (limitaRate) {
                try {
                    Product[] productList = productDAO.searchProductsByRating(search, offset, rateLimit);
                    ProductUtil.mergeSortPrice(productList);
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
