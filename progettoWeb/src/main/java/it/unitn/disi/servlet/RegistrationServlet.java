package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.utils.HashUtil;
import static it.unitn.disi.utils.HashUtil.MD5;
import it.unitn.disi.utils.PasswordValidator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends MyServlet {

    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDAO) initDao(UserDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String password2 = (String) request.getParameter("password2");
        String firstName = (String) request.getParameter("first_name");
        String lastName = (String) request.getParameter("last_name");

        if (password.equals(password2)) {
            PasswordValidator pwdv = new PasswordValidator();
            if (pwdv.validate(password)) {
                System.out.println("ok");
            } else {
                System.out.println("Password non rispetta parametri");
                redirect(response, "register.jsp"); //l'utente deve re-inserire 
                return;
            }
        } else {
            System.out.println("password diverse");
            redirect(response, "register.jsp"); //l'utente deve ri-registrarsi
            return;
        }
        try {
            String user_hash = HashUtil.generateSecureHash(username);

            boolean b = userDao.insertUser(username, email, HashUtil.generatePasswordHash(password), firstName, lastName, user_hash);
            if (b) { //utente inserito nel database

                Session s = (Session) getServletContext().getAttribute("mailSession");
                Message msg = new MimeMessage(s);
                try {
                    msg.setFrom(new InternetAddress(username));
                    msg.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(email, false));

                    msg.setSubject("Benvenuto su KSMR");

                    StringBuffer url = request.getRequestURL();
                    String uri = request.getRequestURI();
                    String ctx = request.getContextPath();
                    String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
                    String link = base + "ConfirmUser" + "?id=" + user_hash + "&usn=" + username;
                    msg.setText("Attiva il tuo account cliccando sul seguente link:\n\n" + link);
                    msg.setSentDate(new Date());
                    Transport.send(msg);
                } catch (MessagingException me) {
                    me.printStackTrace(System.err);
                }

                forward(request, response, "/LoginServlet");
            } else { //utente non registrato. impostare messaggio di registrazione fallita da visualizzare
                redirect(response, "register.jsp");
            }

        } catch (DAOException ex) {
            //impossibile inserire nuovo utente
            System.err.println(ex.getMessage());
            redirect(response, "register.jsp");
        }
    }

    
}
