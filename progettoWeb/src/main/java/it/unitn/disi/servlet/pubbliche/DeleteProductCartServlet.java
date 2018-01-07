package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.controllers.CartController;
import it.unitn.disi.dao.ShopProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.ShopProduct;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteProductCartServlet extends MyServlet {

	private ShopProductDAO shopProductDAO;

	@Override
	public void init() throws ServletException {
		shopProductDAO = (ShopProductDAO) initDao(ShopProductDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idProduct = Model.Parameter.getInt(request, "id_product");
		int idShop = Model.Parameter.getInt(request, "id_shop");

		HttpSession session = request.getSession(true);
		try {
			ShopProduct sp = shopProductDAO.getShopProduct(idProduct, idShop);
			CartController.deleteProduct(session, sp);
			Model.Messages.setBoolean(request, "rimossoProdottoCarrello");
			redirect(response, MyPaths.Jsp.allCart);
			return;
		} catch (DAOException ex) {
			System.err.println(ex.getMessage());
			throw new ServletException(ex.getMessage());
		}
	}
}
