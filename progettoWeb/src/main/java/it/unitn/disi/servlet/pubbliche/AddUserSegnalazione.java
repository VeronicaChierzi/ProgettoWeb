/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.dao.SegnalazioneDAO;
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

/**
 *
 * @author root
 */
public class AddUserSegnalazione extends MyServlet {

    private SegnalazioneDAO segnalazioneDAO;

    @Override
    public void init() throws ServletException {
        segnalazioneDAO = (SegnalazioneDAO) initDao(SegnalazioneDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idOrder = Model.Parameter.getInt(request, "idOrder");
        String title = (String) Model.Parameter.get(request, "title");
        String description = (String) Model.Parameter.get(request, "description");

        try {
            User u = (User) Model.Session.getUserLogged(request);
            if (segnalazioneDAO.addSegnalazioneUser(idOrder, title, description)) {
                Model.Messages.setBoolean(request, "correttaAggiuntaSegnalazione");
                redirect(response, MyPaths.Jsp.userSegnalazioni);
            } else {
                Model.Messages.setBoolean(request, "erroreAggiuntaSegnalazione");
                redirect(response, MyPaths.Jsp.userSegnalazioni);
            }
        } catch (Exception ex) {
            Logger.getLogger(AddUserSegnalazione.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
