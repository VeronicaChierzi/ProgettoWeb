package it.unitn.disi.servlet;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.orders.Order;
import it.unitn.disi.entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OrderListServlet", urlPatterns = {"/OrderListServlet"})
public class OrderListServlet extends MyServlet {

	private OrderDAO orderDAO;

	@Override
	public void init() throws ServletException {
		orderDAO = (OrderDAO) initDao(OrderDAO.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		try {
			Order[] orders = orderDAO.getOrdersByIdUser(user.getId());
			if (orders != null) {
				request.setAttribute("orders", orders);
				forward(request, response, "/orderList.jsp");
			}
		} catch (DAOException ex) {
			System.err.println("Errore nell'ottenere la lista di ordini(OrderListServlet): " + ex.getMessage());
			forward(request, response, "/orderList.jsp");
		}
	}

}
