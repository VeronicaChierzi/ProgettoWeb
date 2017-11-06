package it.unitn.disi.utils;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Model {

	public static class Request {
		// HOW TO USE
		//Oggetto o = (Oggetto) Model.Request.getAttribute(request, Model.Request.nomeOggetto);
		//Model.Request.setAttribute(request, Model.Request.nomeOggetto, oggetto);

		public static final String product = "product";
		public static final String productList = "products";
		public static final String order = "order";
		public static final String orderList = "orderList";
		public static final String segnalazione = "segnalazione";
		public static final String segnalazioneList = "segnalazioneList";

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
