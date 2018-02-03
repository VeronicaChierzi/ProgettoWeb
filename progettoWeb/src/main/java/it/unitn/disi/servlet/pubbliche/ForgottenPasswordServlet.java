package it.unitn.disi.servlet.pubbliche;

import it.unitn.disi.dao.UserDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.servlet.MyServlet;
import it.unitn.disi.utils.HashUtil;
import it.unitn.disi.utils.Model;
import it.unitn.disi.utils.MyPaths;
import it.unitn.disi.utils.MyUtils;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgottenPasswordServlet extends MyServlet {

	private UserDAO userDao;

	@Override
	public void init() throws ServletException {
		userDao = (UserDAO) initDao(UserDAO.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = Model.Parameter.get(request, "email");
		String richiediNuovaPassword = Model.Parameter.get(request, "richiediNuovaPassword");
		try {
			if (richiediNuovaPassword.equals("si")) {
				boolean b = userDao.existsUser(email);
				if (b == true) {
					//GENERARE NUOVO HASH DA METTERE SIA NEL DB CHE NELLA MAIL
					//MANDARE LA MAIL
					//FARE SERVLET PER CAMBIARE EFFETIVAMENTE LA PASSWORD  DOVE CONTROLLO CHE L'HASH E L'ID UTENTE CORRISPONDANO A QUELLI NEL DB
					//String hash = HashUtil.generateSecureHash(email);
					String nuovaPassword = generateRandomPassword();
					String hashPassword = HashUtil.generatePasswordHash(nuovaPassword);
					userDao.setNewPassword(userDao.getByEmail(email).getId(), hashPassword);

					Session s = (Session) getServletContext().getAttribute("mailSession");
					Message msg = new MimeMessage(s);
					try {
						msg.setFrom(new InternetAddress("noreply.ksmr@gmail.com"));
						msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
						msg.setSubject("Richiesta nuova password - KSMR");
						//StringBuffer url = request.getRequestURL();
						//String uri = request.getRequestURI();
						//String ctx = request.getContextPath();
						//String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
						//String link = base + "cambiaPassword.jsp" + "?id=" + nuovaPassword + "&email=" + email;
						if (MyUtils.debugUserController) {
							System.err.println("Generata nuova password: " + nuovaPassword);
						}
						msg.setText("La tua password nuova password è:\n\n" + nuovaPassword);
						msg.setSentDate(new Date());
						Transport.send(msg);
					} catch (MessagingException e) {
						System.out.println(e.getMessage());
					}
					//System.err.println("La password è stata cambiata");
                                        Model.Messages.setBoolean(request, "passwordCambiata");
					redirect(response, MyPaths.Jsp.anonymousLogin);
				} else {
					Model.Messages.setBoolean(request, "forgottenPassword-emailNonValida");
					System.err.println("Email non esistente");
					redirect(response, MyPaths.Jsp.anonymousForgottenPassword);
				}
			} else {
				System.err.println("Reset password NON richiesto");
				redirect(response, MyPaths.Jsp.anonymousForgottenPassword);
			}
		} catch (DAOException ex) { //utente non loggato a causa di un eccezione nell'eseguire la query
			System.err.println("Errore DAOException in ForgottenPasswordServlet: " + ex.getMessage());
			forward(request, response, MyPaths.Jsp._errorPagesErrorDaoException);
		}
	}

	public static String generateRandomPassword() {
		Random random = new Random(System.currentTimeMillis());
		String a = "0123456789";
		String b = "abcdefghijklmnopqrstuwxyz";
		String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String d = ".#?!@$%^&*-";
		String all = "0123456789abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.#?!@$%^&*-";
		StringBuilder sb = new StringBuilder(10);
		sb.append(all.charAt(random.nextInt(all.length())));
		sb.append(a.charAt(random.nextInt(a.length())));
		sb.append(all.charAt(random.nextInt(all.length())));
		sb.append(b.charAt(random.nextInt(b.length())));
		sb.append(all.charAt(random.nextInt(all.length())));
		sb.append(all.charAt(random.nextInt(all.length())));
		sb.append(c.charAt(random.nextInt(c.length())));
		sb.append(all.charAt(random.nextInt(all.length())));
		sb.append(d.charAt(random.nextInt(d.length())));
		sb.append(all.charAt(random.nextInt(all.length())));
		return sb.toString();
	}

}
