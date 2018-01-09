package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.controllers.CartController;
import it.unitn.disi.dao.ProductDAO;
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

public class ChangeProductQuantityCartServlet extends MyServlet {

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
		int quantity = Model.Parameter.getInt(request, "quantity"); //nuova quantità

		HttpSession session = request.getSession(true);
		try {
			ShopProduct sp = shopProductDAO.getShopProduct(idProduct, idShop, false, false);
			//float realPrice = sp.getPrice(); //ricava dal database il prezzo reale
			//if (realPrice != clientPrice) {
			//stampa messaggio per l'utente. il prezzo è stato cambiato.
			//}
			CartController.changeQuantity(session, sp, quantity);
			Model.Messages.setBoolean(request, "quantitaProdottoCarrelloModificata");
			redirect(response, MyPaths.Jsp.allCart);
			return;
		} catch (DAOException ex) {
			System.err.println(ex.getMessage());
			throw new ServletException(ex.getMessage());
		}
	}
}
