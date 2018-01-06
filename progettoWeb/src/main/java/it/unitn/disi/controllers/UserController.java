package it.unitn.disi.controllers;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.UserSeller;
import it.unitn.disi.utils.HashUtil;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyUtils;
import it.unitn.disi.utils.PasswordValidator;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class UserController {

	//effettua il login. Restituisce:
	// true se ha successo
	// false se le credenziali sono sbagliate (cioè se non trova l'utente nel database)
	// DAOException se c'è un errore nella query
	public static boolean login(UserDAO userDao, HttpServletRequest request, String usernameEmail, String password)
			throws DAOException {
		if (MyUtils.debugUserController) {
			System.err.println("Inizio tentativo di Login. usernameEmail: " + usernameEmail + ", password: " + password);
		}
		String hashPassword = HashUtil.generatePasswordHash(password);
		try {
			User user = userDao.getByUsernameEmailAndPassword(usernameEmail, hashPassword);
			if (user != null) { //utente loggato
				Model.Session.setAttribute(request, Model.Session.user, user);
				if (MyUtils.debugUserController) {
					System.err.println("Login riuscito!");
				}
				return true;
			} else { //utente non loggato
				Model.Messages.setBoolean(request, Model.Messages.loginFailed);
				if (MyUtils.debugUserController) {
					System.err.println("Login fallito!");
				}
				return false;
			}
		} catch (DAOException ex) { //utente non loggato a causa di un eccezione nell'eseguire la query
			System.err.println("Errore DAOException nel Login: " + ex.getMessage());
			throw ex;
		}
	}

	//effettua la registrazione dell'utente normale (non venditore) e poi effettua il login. Restituisce:
	// true se la registrazione ha successo
	// false se le registrazione fallisce
	// DAOException se c'è un errore nella query
	public static boolean registration(
			UserDAO userDao, ServletContext servletContext, HttpServletRequest request,
			String username, String email, String password, String password2, String firstName, String lastName
	) throws DAOException {
		if (MyUtils.debugUserController) {
			System.err.println("Inizio tentativo di Registrazione Utente. username: " + username + ", email: " + email + ", password: " + password + ", firstName: " + firstName + ", lastName: " + lastName);
		}
		if (UserController.checkPassword(request, password, password2) == false) {
			return false;
		}
		try {
			String user_hash = HashUtil.generateSecureHash(username);
			String hashPassword = HashUtil.generatePasswordHash(password);
			boolean b = userDao.insertUser(username, email, hashPassword, firstName, lastName, user_hash);
			if (b) { //utente registrato (inserito nel database)
				if (MyUtils.debugUserController) {
					System.err.println("Registrazione utente effettuata con successo");
				}
				//invio email di conferma
				UserController.sendConfirmEmail(servletContext, request, username, email, user_hash);
				//login
				boolean loggato = UserController.login(userDao, request, username, password);
				return loggato;
			} else { //utente non registrato
				if (MyUtils.debugUserController) {
					System.err.println("Registrazione utente fallita");
				}
				return false;
			}
		} catch (DAOException ex) { //impossibile inserire nuovo utente
			System.err.println("Errore DAOException in Registration: " + ex.getMessage());
			throw ex;
		}
	}

	//effettua la registrazione dell'utente normale (non venditore) e poi effettua il login. Restituisce:
	// true se la registrazione ha successo
	// false se le registrazione fallisce
	// DAOException se c'è un errore nella query
	public static boolean registrationSeller(
			UserDAO userDao, ServletContext servletContext, HttpServletRequest request,
			String username, String email, String password, String password2, String firstName, String lastName, String nomeNeg, String partitaIva
	) throws DAOException {
		if (MyUtils.debugUserController) {
			System.err.println("Inizio tentativo di Registrazione Utente e Venditore. username: " + username + ", email: " + email + ", password: " + password + ", firstName: " + firstName + ", lastName: " + lastName + ", nomeNeg: " + nomeNeg + ", partitaIva: " + partitaIva);
		}
		if (UserController.checkPassword(request, password, password2) == false) {
			return false;
		}
		try {
			String user_hash = HashUtil.generateSecureHash(username);
			String hashPassword = HashUtil.generatePasswordHash(password);
			boolean b = userDao.insertUser(username, email, hashPassword, firstName, lastName, user_hash);
			if (b) { //utente registrato (inserito nel database)
				if (MyUtils.debugUserController) {
					System.err.println("Registrazione utente effettuata con successo");
				}
				//invio email di conferma
				UserController.sendConfirmEmail(servletContext, request, username, email, user_hash);
				//login
				boolean loggato = UserController.login(userDao, request, username, password);
				if (loggato) {
					if (registrazioneVenditore(userDao, request, nomeNeg, partitaIva) == true) {
						return true; //utente venditore registrato
					} else {
						return false; //utente venditore non registrato
					}
				} else {
					return false; //utente non registrato
				}
			} else { //utente non registrato
				if (MyUtils.debugUserController) {
					System.err.println("Registrazione utente fallita");
				}
				return false;
			}
		} catch (DAOException ex) { //impossibile inserire nuovo utente
			System.err.println("Errore DAOException in Registration: " + ex.getMessage());
			throw ex;
		}
	}

	//effettua la registrazione come venditore di un utente normale (che non era venditore) e che era già loggato.
	// true se la registrazione ha successo
	// false se le registrazione fallisce
	// DAOException se c'è un errore nella query
	public static boolean registrationSellerLog(
			UserDAO userDao, ServletContext servletContext, HttpServletRequest request,
			String nomeNeg, String partitaIva
	) throws DAOException {
		if (MyUtils.debugUserController) {
			System.err.println("Inizio tentativo di Registrazione Venditore. nomeNeg: " + nomeNeg + ", partitaIva: " + partitaIva);
		}
		try {
			return registrazioneVenditore(userDao, request, nomeNeg, partitaIva);
		} catch (DAOException ex) { //impossibile inserire nuovo utente
			System.err.println("Errore DAOException in Registration: " + ex.getMessage());
			throw ex;
		}
	}

	private static boolean registrazioneVenditore(UserDAO userDao, HttpServletRequest request, String nomeNeg, String partitaIva)
			throws DAOException {
		try {
			User user = Model.Session.getUserLogged(request);
			boolean v = userDao.insertUserSeller(user.getId(), nomeNeg, partitaIva);
			if (v) { //utente venditore registrato
				UserSeller userSeller = userDao.getUserSeller(user.getId());
				user.setUserSeller(userSeller);
				return true;
			} else { //registrazione utente venditore fallita. effettua comunque il login come utente normale.
				System.err.println("Errore: La registrazione come venditore è fallita.");
				return false;
			}
		} catch (Exception ex) {
			System.err.println("Errore durante la registrazione come venditore: utente non loggato");
			throw new DAOException("Errore durante la registrazione come venditore: utente non loggato");
		}
	}

	//controlla che le 2 password siano uguali e che rispettino i criteri
	//restituisce false se le password sono diverse o se la password non rispetta i criteri
	private static boolean checkPassword(HttpServletRequest request, String password, String password2) {
		if (!password.equals(password2)) { //le 2 password sono diverse
			if (MyUtils.debugUserController) {
				System.err.println("Le password inserite sono diverse");
			}
			Model.Messages.setString(request, Model.Messages.registrationFailedString, "Le password inserite sono diverse");
			return false;
		} else { //le 2 password sono uguali
			PasswordValidator pwdv = new PasswordValidator();
			if (!pwdv.validate(password)) { //la password non rispetta i parametri
				if (MyUtils.debugUserController) {
					System.err.println("La password inserita non rispetta i criteri");
				}
				Model.Messages.setString(request, Model.Messages.registrationFailedString, "La password non rispetta i parametri");
				return false;
			}
		}
		if (MyUtils.debugUserController) {
			System.err.println("Password inserita correttamente");
		}
		return true;
	}

	//invia l'email di conferma dell'utente
	private static void sendConfirmEmail(ServletContext servletContext, HttpServletRequest request, String username, String email, String user_hash) {
		if (MyUtils.sendRegistrationConfirmEmail) { //se mandare una email di conferma
			if (MyUtils.debugUserController) {
				System.err.println("Iniziato tentativo di invio dell'email di conferma");
			}
			Session s = (Session) servletContext.getAttribute("mailSession");
			Message msg = new MimeMessage(s);
			try {
				msg.setFrom(new InternetAddress(username));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
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
				System.err.println("Errore: c'è stato un errore nell'invio della email per confermare la registrazione");
				me.printStackTrace(System.err);
			}
			if (MyUtils.debugUserController) {
				System.err.println("Email di conferma inviata con successo");
			}
		} else {
			if (MyUtils.debugUserController) {
				System.err.println("L'invio dell'email di conferma è disabilitato: Email non inviata");
			}
		}
	}
}
