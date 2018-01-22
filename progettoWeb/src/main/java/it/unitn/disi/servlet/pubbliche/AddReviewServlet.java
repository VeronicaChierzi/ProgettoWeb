package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.ReviewProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.unbescape.html.HtmlEscape;

/**
 *
 * @author Luca Degani
 */
public class AddReviewServlet extends MyServlet {

    private OrderDAO orderDAO;
    private ReviewProductDAO reviewProductDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = (OrderDAO) initDao(OrderDAO.class);
        reviewProductDAO = (ReviewProductDAO) initDao(ReviewProductDAO.class);
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

        int idProduct = Model.Parameter.getInt(request, "id_product");
        int idOrder = Model.Parameter.getInt(request, "id_order");
        String title = HtmlEscape.escapeHtml5(Model.Parameter.get(request, "title"));
        String text = HtmlEscape.escapeHtml5(Model.Parameter.get(request, "text"));
        int rate = Model.Parameter.getInt(request, "rate");

        User user = (User) Model.Session.getAttribute(request, Model.Session.user);

        if (user != null) {

            try {
                Object s = orderDAO.hasUserBought(user.getId(), idProduct);
                if (s != null) {
                    int orderIdCheck = (int) s;
                    if (idOrder == orderIdCheck) { //la aggiungo
                        if (!reviewProductDAO.existReview(idOrder, idProduct)) {
                            if (reviewProductDAO.addReview(idOrder, idProduct, rate, title, text)) {
                                Model.Messages.setBoolean(request, "recensioneAggiunta");
                            } else {
                                Model.Messages.setBoolean(request, "recensioneFallita");

                            }
                        } else {
                            Model.Messages.setBoolean(request, "recensioneFallita");
                        }
                    } else {
                        Model.Messages.setBoolean(request, "recensioneFallita");
                    }
                }
                redirect(response, MyPaths.Jsp.allProduct + "?id=" + idProduct);
                return;
            } catch (DAOException ex) {
                System.err.println(ex.getMessage());
                throw new ServletException(ex.getMessage());
            }
        }

    }
}
