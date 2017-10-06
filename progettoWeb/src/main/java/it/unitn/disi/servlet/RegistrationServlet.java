package it.unitn.disi.servlet;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.utils.PasswordValidator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession(true);

        String username = (String) request.getParameter("username");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String password2 = (String) request.getParameter("password2");
        String firstName = (String) request.getParameter("first_name");
        String lastName = (String) request.getParameter("last_name");

        session.setAttribute("username", username);
        session.setAttribute("email", email);
        session.setAttribute("first_name", firstName);
        session.setAttribute("last_name", lastName);

        System.out.println(password);
        System.out.println(password2);

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
            boolean b = userDao.insertUser(username, email, password, firstName, lastName);
            if (b) { //utente inserito nel database
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
