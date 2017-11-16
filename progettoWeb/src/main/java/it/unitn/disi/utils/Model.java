package it.unitn.disi.utils;

import it.unitn.disi.entities.User;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Model {

	public static class Parameter {
		// HOW TO USE
		//String s = Model.Parameter.get(request, Model.Parameter.nomeParametro);

		public static final String textSearch = "textSearch";

		// <editor-fold defaultstate="collapsed" desc="Getter">
		public static String get(HttpServletRequest request, String parameterName) {
			String s = request.getParameter(parameterName);
			if (s == null) {
				s = "";
			}
			return s;
		}
		// </editor-fold>
	}

	public static class Request {
		// HOW TO USE
		//Oggetto o = (Oggetto) Model.Request.getAttribute(request, Model.Request.nomeOggetto);
		//Model.Request.setAttribute(request, Model.Request.nomeOggetto, oggetto);

		public static final String product = "product";
		public static final String productList = "products";
		
		public static final String orderUser = "orderUser";
		public static final String ordersUser = "ordersUser";
		public static final String orderSeller = "ordersUser";
		public static final String ordersShop = "ordersShop"; //lista ordini di un punto vendita
		public static final String ordersSeller = "ordersSeller"; //lista completa ordini di un venditore
		
		public static final String segnalazioneUser = "segnalazioneUser";
		public static final String segnalazioniUser = "segnalazioniUser";
		public static final String segnalazioniOpenUser = "segnalazioniOpenUser";
		public static final String segnalazioneAdmin = "segnalazioneAdmin";
		public static final String segnalazioniAdmin = "segnalazioniAdmin";
		public static final String segnalazioniOpenAdmin = "segnalazioniOpenAdmin";
		public static final String segnalazioneSeller = "segnalazioneSeller";
		public static final String segnalazioniSeller = "segnalazioniSeller";
		public static final String segnalazioniOpenSeller = "segnalazioniOpenSeller";

		//private static final String shopName = "shop";
		//private static final String myShopsName = "myShops";
		// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
		public static Object getAttribute(HttpServletRequest request, String attributeName) {
			Object object = (Object) request.getAttribute(attributeName);
			return object;
		}

		public static void setAttribute(HttpServletRequest request, String attributeName, Object object) {
			request.setAttribute(attributeName, object);
		}
		// </editor-fold>
	}

	public static class Session {
		// HOW TO USE
		//Oggetto o = (Oggetto) Model.Session.getAttribute(request, Model.Session.nomeOggetto);
		//Model.Session.setAttribute(request, Model.Session.nomeOggetto, oggetto);

		public static final String user = "user";
		public static final String cart = "cart";

		// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
		public static Object getAttribute(HttpServletRequest request, String attributeName) {
			HttpSession session = request.getSession(true);
			Object object = (Object) session.getAttribute(attributeName);
			return object;
		}

		public static void setAttribute(HttpServletRequest request, String attributeName, Object object) {
			HttpSession session = request.getSession(true);
			session.setAttribute(attributeName, object);
		}
		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Get User and check Logged">
		public static User getUserLogged(HttpServletRequest request) throws Exception {
			User u = (User) getAttribute(request, Model.Session.user);
			if (u == null) {
				String errore = "ERRORE DI SICUREZZA: L'utente NON è LOGGATO e sta cercando di accedere ad una servlet riservata ad utenti loggati!!!";
				System.err.println(errore);
				throw new Exception(errore);
			}
			return u;
		}

		public static User getUserSellerLogged(HttpServletRequest request) throws Exception {
			User u = (User) getAttribute(request, Model.Session.user);
			if (u == null) {
				String errore = "ERRORE DI SICUREZZA: L'utente NON è LOGGATO e sta cercando di accedere ad una servlet riservata ad utenti loggati!!!";
				System.err.println(errore);
				throw new Exception(errore);
			} else if (u.isSeller()) {
				String errore = "ERRORE DI SICUREZZA: L'utente è loggato, ma NON è un VENDITORE e sta cercando di accedere ad una servlet riservata a venditori!!!";
				System.err.println(errore);
				throw new Exception(errore);
			}
			return u;
		}

		public static User getUserAdminLogged(HttpServletRequest request) throws Exception {
			User u = (User) getAttribute(request, Model.Session.user);
			if (u == null) {
				String errore = "ERRORE DI SICUREZZA: L'utente NON è LOGGATO e sta cercando di accedere ad una servlet riservata ad utenti loggati!!!";
				System.err.println(errore);
				throw new Exception(errore);
			} else if (u.isAdmin()) {
				String errore = "ERRORE DI SICUREZZA: L'utente è loggato, ma NON è un ADMIN e sta cercando di accedere ad una servlet riservata agli admin!!!";
				System.err.println(errore);
				throw new Exception(errore);
			}
			return u;
		}
		// </editor-fold>
	}

	public static class Application {
		// HOW TO USE
		//Oggetto o = (Oggetto)getAttribute(config, Model.Application.nomeOggetto);
		//Model.Application.setAttribute(sc, Model.Application.nomeOggetto, oggetto);

		public static final String categoryContainer = "categoryContainer";
		public static final String locationContainer = "locationContainer";

		// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
		public static Object getAttribute(ServletConfig servletConfig, String attributeName) {
			Object object = servletConfig.getServletContext().getAttribute(attributeName);
			return object;
		}

		public static void setAttribute(ServletConfig servletConfig, String attributeName, Object object) {
			servletConfig.getServletContext().setAttribute(attributeName, object);
		}
		//</editor-fold>
	}

	public static class Messages {
		// HOW TO USE
		//boolean b = Model.Messages.consumeAttribute(request, Model.Messages.nomeOggetto);
		//Model.Messages.setAttribute(request, Model.Messages.nomeOggetto);

		//setQualcosa richiamate nelle servlet per impostare il messaggio.
		//consumeQualcosa richiamate nelle jsp. se restituisce true, allora la jsp deve visualizzare un messaggio.
		//i "messaggi" sono boolean. Il messaggio da stampare sarà deciso nella jsp.
		public static final String loginFailed = "loginFailedMessage";
		public static final String registrationFailed = "registrationFailedMessage";
		public static final String registrationSellerFailed = "registrationSellerFailedMessage";
		public static final String registrationSellerLogFailed = "registrationSellerLogFailedMessage";

		// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
		public static void setAttribute(HttpServletRequest request, String attributeName) {
			HttpSession session = request.getSession(true);
			session.setAttribute(attributeName, true);
		}

		public static boolean consumeAttribute(HttpServletRequest request, String attributeName) {
			HttpSession session = request.getSession(true);
			Boolean b = (Boolean) session.getAttribute(attributeName);
			session.removeAttribute(attributeName);
			return (b != null && b); //espressione necessaria per evitare NullPointerException. se b è null, restituisce false.
		}
		// </editor-fold>
	}
}
