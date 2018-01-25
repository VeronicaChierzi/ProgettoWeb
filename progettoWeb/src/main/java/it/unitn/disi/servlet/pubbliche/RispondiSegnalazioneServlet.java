/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.dao.SegnalazioneDAO;
import it.unitn.disi.dao.SegnalazioneRispostaDAO;
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
public class RispondiSegnalazioneServlet extends MyServlet {

    private SegnalazioneDAO segnalazioneDAO;
    private SegnalazioneRispostaDAO segnalazioneRispostaDAO;

    @Override
    public void init() throws ServletException {
        segnalazioneDAO = (SegnalazioneDAO) initDao(SegnalazioneDAO.class);
        segnalazioneRispostaDAO = (SegnalazioneRispostaDAO) initDao(SegnalazioneRispostaDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idSegnalazione = Model.Parameter.getInt(request, "idSegnalazione"); //controllare esista con getSegnalazioneAdmin
        String message = (String) Model.Parameter.get(request, "message");
        String decisione = (String) Model.Parameter.get(request, "decisione");
        String rimborso = (String) Model.Parameter.get(request, "rimborso"); //parse con regex
        String negVal = (String) Model.Parameter.get(request, "negVal"); //parse con regex

        boolean rimborsoB = false;
        if (rimborso.equalsIgnoreCase("false") || rimborso.equalsIgnoreCase("true")) {
            rimborsoB = Boolean.parseBoolean(rimborso);
        }
        boolean negValB = false;
        if (negVal.equalsIgnoreCase("false") || negVal.equalsIgnoreCase("true")) {
            negValB = Boolean.parseBoolean(negVal);
        }

        try {
            User u = (User) Model.Session.getUserAdminLogged(request);
            if (segnalazioneDAO.getSegnalazioneAdmin(idSegnalazione) != null) {
                if (segnalazioneRispostaDAO.addRispostaByAdmin(idSegnalazione, u.getUserAdmin().getId(), message, decisione.charAt(0)+"", rimborsoB, negValB)) {
                    Model.Messages.setBoolean(request, "rispostaCorretta");
                    redirect(response, MyPaths.Jsp.adminSegnalazioni);
                } else {
                    Model.Messages.setBoolean(request, "rispostaErrore");
                    redirect(response, MyPaths.Jsp.adminSegnalazioni);
                }
            } else {
                Model.Messages.setBoolean(request, "segnalazioneNonEsiste");
                redirect(response, MyPaths.Jsp.adminSegnalazioni);
            }

        } catch (Exception ex) {
            Logger.getLogger(AddUserSegnalazione.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
