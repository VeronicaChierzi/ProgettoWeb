/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.utils.HashUtil;
import java.io.IOException;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class ForgotPasswordServlet extends MyServlet {

    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDAO) initDao(UserDAO.class);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("emailReset");
        if (email != null) {
            try {
                boolean b = userDao.existsUser(email);
                if ((b == true)) {
                    //GENERARE NUOVO HASH DA METTERE SIA NEL DB CHE NELLA MAIL
                    //MANDARE LA MAIL
                    //FARE SERVLET PER CAMBIARE EFFETIVAMENTE LA PASSWORD  DOVE CONTROLLO CHE L'HASH E L'ID UTENTE CORRISPONDANO A QUELLI NEL DB
                    String hash = HashUtil.generateSecureHash(email);
                    userDao.setNewUserHash(userDao.getByEmail(email).getId(), hash);

                    Session s = (Session) getServletContext().getAttribute("mailSession");
                    Message msg = new MimeMessage(s);
                    try {
                        msg.setFrom(new InternetAddress("noreply.ksmr@gmail.com"));
                        msg.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(email, false));

                        msg.setSubject("Benvenuto su KSMR");

                        StringBuffer url = request.getRequestURL();
                        String uri = request.getRequestURI();
                        String ctx = request.getContextPath();
                        String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
                        String link = base + "cambiaPassword.jsp" + "?id=" + hash + "&email=" + email;
                        msg.setText("Per cambiare la tua password clicca sul seguente link:\n\n" + link);
                        msg.setSentDate(new Date());
                        Transport.send(msg);
                    } catch (MessagingException e) {
                        System.out.println(e.getMessage());
                    }

                    redirect(response, "user/profilo.jsp");
                    System.out.println("password cambiata");
                } else {
                    redirect(response, "login.jsp");
                    System.out.println("utente non esistente");
                }
            } catch (DAOException ex) {
                System.out.println(ex.getMessage());
                redirect(response, "login.jsp");
            }
        } else {
            redirect(response, "login.jsp");
        }

    }
}
