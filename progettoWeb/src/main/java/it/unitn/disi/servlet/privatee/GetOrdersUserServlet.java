package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrdersUserServlet extends MyServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = (OrderDAO) initDao(OrderDAO.class);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String view = Model.Parameter.get(request, "view");
        try {
            User user = (User) Model.Session.getUserLogged(request); //necessario per verificare che sia un utente loggato
            try {
                Order[] orders;
                if(view.equalsIgnoreCase("all")) {
                    orders = orderDAO.getOrdersUser(user.getId());
                } else {
                    orders = orderDAO.getOrdersUserLimit(user.getId());
                }                
                Model.Request.setAttribute(request, Model.Request.ordersUser, orders);
            } catch (DAOException ex) {
                System.err.println("Errore DAOException in GetOrdersUserServlet: " + ex.getMessage());
                forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
            }
        } catch (Exception e) {
            System.err.println("Errore utente non loggato in GetOrdersUserServlet: " + e.getMessage());
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
