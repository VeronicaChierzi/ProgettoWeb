package it.unitn.disi.servlet;

import it.unitn.disi.dao.ImageDAO;
import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductListServlet", urlPatterns = {"/ProductListServlet"})
public class ProductListServlet extends MyServlet {

    private ProductDAO productDAO;
    private ImageDAO imageDAO;

    @Override
    public void init() throws ServletException {
        productDAO = (ProductDAO) initDao(ProductDAO.class);
        imageDAO = (ImageDAO) initDao(ImageDAO.class);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //cercare prodotti
        String search = request.getParameter("textSearch");
        System.out.println("CIAO BELLO LUCA" + request == null);
        try {
            Product[] products = productDAO.searchProducts(search);
            
            if (products != null) {
                request.setAttribute("products", products);
                request.setAttribute("searchQuery", search);
                request.setAttribute("imageDAO", imageDAO);
                forward(request, response, "/productList.jsp");
            }
        } catch (DAOException ex) {
            System.err.println("Errore nell'ottenere la lista di prodotti(ProductListServlet): " + ex.getMessage());
        }
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        processRequest(request, response);
    }

}
