package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.controllers.CartController;
import it.unitn.disi.dao.OrderDAO;
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

public class ConcludiOrderServlet extends MyServlet {

	private OrderDAO orderDAO;

	@Override
	public void init() throws ServletException {
		orderDAO = (OrderDAO) initDao(OrderDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idOrder = Model.Parameter.getInt(request, "id_order");
		int idShop = Model.Parameter.getInt(request, "id_shop");

		try {
			boolean b = orderDAO.concludiOrder(idOrder, idShop);
			if(b){
				Model.Messages.setBoolean(request, "ordineConcluso");
				redirect(response, MyPaths.Jsp.sellerOrder + "?id=" + idOrder);
			} else {
				Model.Messages.setBoolean(request, "impossibileConcludereOrdine");
				redirect(response, MyPaths.Jsp.sellerOrdersSeller);
			}
			return;
		} catch (DAOException ex) {
			System.err.println(ex.getMessage());
			throw new ServletException(ex.getMessage());
		}
	}
}
