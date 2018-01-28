package it.unitn.disi.servlet.privatee;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.ReviewProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Product;
import it.unitn.disi.entities.ReviewProduct;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductServlet extends MyServlet {

	private ProductDAO productDAO;
	private ReviewProductDAO reviewProductDAO;
	private OrderDAO orderDAO;

	@Override
	public void init() throws ServletException {
		productDAO = (ProductDAO) initDao(ProductDAO.class);
		reviewProductDAO = (ReviewProductDAO) initDao(ReviewProductDAO.class);
		orderDAO = (OrderDAO) initDao(OrderDAO.class);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = -1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			String idShopS = Model.Parameter.get(request, "id_shop");
			int idShop;
			if(idShopS==null || idShopS.equals("")){
				idShop = -1;
			} else {
				idShop = Integer.parseInt(idShopS);
			}
			try {
				Product product = productDAO.getProduct(id, idShop);
				//Product product = productDAO.getProduct(id, true, true, false);
				Model.Request.setAttribute(request, Model.Request.product, product);
			} catch (DAOException ex) {
				System.err.println("Errore DAOException in GetProductServlet: " + ex.getMessage());
				forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
			}
		} catch (NumberFormatException e) {
			System.err.println("Errore NumberFormatException in GetProductServlet: " + e.getMessage());
		}

		//prendo le reviews di quel prodotto
		if (id != -1) {
			ReviewProduct[] r = null;
			try {
				r = reviewProductDAO.getReviewsByProductId(id);
			} catch (DAOException ex) {
				System.err.println("Errore DAOException in GetProductServlet while gathering reviews: " + ex.getMessage());
				forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
			}
			Model.Request.setAttribute(request, Model.Request.productReviews, r);
		}

		//se è loggato e se ha comprato quel prodotto
		User user = (User) Model.Session.getAttribute(request, Model.Session.user);

		if (user != null) {
			try {
				int orderId = orderDAO.hasUserBought(user.getId(), id);
				if (orderId != -1) {
					Model.Request.setAttribute(request, Model.Request.orderId, orderId);
				}
			} catch (DAOException ex) {
				System.err.println("Errore DAOException in GetProductServlet while checking if user has already bougth this item: " + ex.getMessage());
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
