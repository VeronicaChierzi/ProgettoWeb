/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.dao.ShopDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Shop;
import it.unitn.disi.entities.User;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.servlet.privatee.GetMyShopServlet;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class ChangeShopParamsServlet extends MyServlet {

    private ShopDAO shopDAO;

    @Override
    public void init() throws ServletException {
        shopDAO = (ShopDAO) initDao(ShopDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orario = Model.Parameter.get(request, Model.Parameter.orarioShop);
        String id = Model.Parameter.get(request, Model.Parameter.idShop);

        if (!id.equals("") && !Pattern.matches("[a-zA-Z]+", id)) {
            int idShop = Integer.parseInt(id);
            User u = null;
            try {
                u = Model.Session.getUserLogged(request);
            } catch (Exception ex) {
                Logger.getLogger(GetMyShopServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (u != null) {
                try {
                    Shop[] s = shopDAO.getShopsByUserSeller(u.getId()); //controllo se può fare la modifica
                    boolean modifica = false;
                    for (Shop a : s) {
                        if (a.getIdOwner() == u.getId()) {
                            modifica = true;
                        }
                    }
                    if (modifica) {
                        if (shopDAO.changeOrarioShop(idShop, orario)) {
                            Model.Messages.setBoolean(request, "orarioCambiatoCorrettamente");
                            redirect(response, MyPaths.Jsp.sellerMySeller);
                        } else {
                            Model.Messages.setBoolean(request, "orarioNonCambiatoErroreDB");
                            redirect(response, MyPaths.Jsp.sellerMySeller);
                        }
                    } else {
                        Model.Messages.setBoolean(request, "orarioNonCambiatoPermessi");
                        redirect(response, MyPaths.Jsp.sellerMySeller);
                    }
                } catch (DAOException ex) {
                    System.err.println(ex.getMessage());
                    throw new ServletException(ex.getMessage());
                }
            }
        } else {
            Model.Messages.setBoolean(request, "orarioNonCambiatoNonLoggato");
            redirect(response, MyPaths.Jsp.sellerMySeller);
        }

    }
}
