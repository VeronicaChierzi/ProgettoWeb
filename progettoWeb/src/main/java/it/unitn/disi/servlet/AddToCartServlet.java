package it.unitn.disi.servlet;

import it.unitn.disi.controllers.CartController;
import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.ShopProduct;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends MyServlet {

	private ProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		productDAO = (ProductDAO) initDao(ProductDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idProduct = Integer.parseInt(request.getParameter("id_product"));
		int idShop = Integer.parseInt(request.getParameter("id_shop"));
		float clientPrice = Float.parseFloat(request.getParameter("current_price"));
		int quantityToAdd = Integer.parseInt(request.getParameter("quantity"));

		HttpSession session = request.getSession(true);
		//Cart cart = CartController.getOrCreateCart(session);
		try {
			ShopProduct sp = productDAO.getShopProduct(idProduct, idShop);
			float realPrice = sp.getPrice(); //ricava dal database il prezzo reale
			if (realPrice != clientPrice) {
				//stampa messaggio per l'utente. il prezzo è stato cambiato.
			}
			CartController.addToCart(session, sp, quantityToAdd);
			redirect(response, "paginaDiDecisione.jsp");
		} catch (DAOException ex) {
			System.err.println(ex.getMessage());
			throw new ServletException(ex.getMessage());
			//redirect?
		}
	}
}
