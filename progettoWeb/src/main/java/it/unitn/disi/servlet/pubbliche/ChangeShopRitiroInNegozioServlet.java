package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeShopRitiroInNegozioServlet extends MyServlet {

    private ShopDAO shopDAO;

    @Override
    public void init() throws ServletException {
        shopDAO = (ShopDAO) initDao(ShopDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		int idShop = Model.Parameter.getInt(request, "id_shop");
		boolean ritiroInNegozio = Model.Parameter.getBoolean(request, "ritiro_in_negozio");

		try {
			boolean b = shopDAO.changeRitiroInNegozio(idShop, ritiroInNegozio);
			if(b){
				Model.Messages.setBoolean(request, "ritiroInNegozioModificato");
				redirect(response, MyPaths.Jsp.sellerMySeller);				
			} else {
				Model.Messages.setBoolean(request, "ritiroInNegozioModificato");
				redirect(response, MyPaths.Jsp.sellerMySeller);				
			}
			return;
		} catch (DAOException ex) {
			System.err.println(ex.getMessage());
			throw new ServletException(ex.getMessage());
		}
    }
}
