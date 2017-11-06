package it.unitn.disi.utils;

import it.unitn.disi.entities.Product;
import it.unitn.disi.entities.Segnalazione;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.categories.CategoryContainer;
import it.unitn.disi.entities.locations.LocationContainer;
import it.unitn.disi.entities.orders.Order;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Model {

	public static class Request {

		private static final String productName = "product";
		private static final String productListName = "products";
		private static final String orderName = "order";
		private static final String orderListName = "orderList";
		private static final String segnalazioneName = "segnalazione";
		private static final String segnalazioneListName = "segnalazioneList";

		//private static final String shopName = "shop";
		//private static final String myShopsName = "myShops";

		// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
		private static Object getAttribute(HttpServletRequest request, String attributeName) {
			Object object = (Object) request.getAttribute(attributeName);
			return object;
		}

		private static void setAttribute(HttpServletRequest request, String attributeName, Object object) {
			request.setAttribute(attributeName, object);
		}

		public static Product getProduct(HttpServletRequest request) {
			return (Product) getAttribute(request, productName);
		}

		public static void setProduct(HttpServletRequest request, Product product) {
			setAttribute(request, productName, product);
		}

		public static List<Product> getProductList(HttpServletRequest request) {
			return (List<Product>) getAttribute(request, productListName);
		}

		public static void setProductList(HttpServletRequest request, List<Product> products) {
			setAttribute(request, productListName, products);
		}

		public static Order getOrder(HttpServletRequest request) {
			return (Order) getAttribute(request, orderName);
		}

		public static void setOrder(HttpServletRequest request, Order order) {
			setAttribute(request, orderName, order);
		}

		public static List<Order> getOrderList(HttpServletRequest request) {
			return (List<Order>) getAttribute(request, orderListName);
		}

		public static void setOrderList(HttpServletRequest request, List<Order> orders) {
			setAttribute(request, orderListName, orders);
		}

		public static Segnalazione getSegnalazione(HttpServletRequest request) {
			return (Segnalazione) getAttribute(request, segnalazioneName);
		}

		public static void setSegnalazione(HttpServletRequest request, Segnalazione segnalazione) {
			setAttribute(request, segnalazioneName, segnalazione);
		}

		public static List<Segnalazione> getSegnalazioneList(HttpServletRequest request) {
			return (List<Segnalazione>) getAttribute(request, segnalazioneListName);
		}

		public static void setSegnalazioneList(HttpServletRequest request, List<Segnalazione> segnalazioni) {
			setAttribute(request, segnalazioneListName, segnalazioni);
		}
		// </editor-fold>
	}

	public static class Session {

		private static final String userName = "user";

		// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
		private static Object getAttribute(HttpServletRequest request, String attributeName) {
			HttpSession session = request.getSession(true);
			Object object = (Object) session.getAttribute(attributeName);
			return object;
		}

		private static void setAttribute(HttpServletRequest request, String attributeName, Object object) {
			HttpSession session = request.getSession(true);
			session.setAttribute(attributeName, object);
		}

		public static void setUser(HttpServletRequest request, User user) {
			setAttribute(request, userName, user);
		}

		public static User getUser(HttpServletRequest request) {
			return (User) getAttribute(request, userName);
		}
		// </editor-fold>
	}

	public static class Application {

		public static final String categoryContainer = "categoryContainer";
		public static final String locationContainer = "locationContainer";

		// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
		public static Object getAttribute(ServletConfig servletConfig, String attributeName) {
			//ServletContext sc = request.getServletContext();
			Object object = servletConfig.getServletContext().getAttribute(attributeName);
			return object;
		}

		public static void setAttribute(ServletConfig servletConfig, String attributeName, Object object) {
			//ServletContext sc = request.getServletContext();
			servletConfig.getServletContext().setAttribute(attributeName, object);
		}

		/*
		public static CategoryContainer getCategoryContainer(ServletConfig servletConfig) {
			return (CategoryContainer) getAttribute(servletConfig, categoryContainerName);
		}

		public static void setCategoryContainer(ServletConfig servletConfig, CategoryContainer categoryContainer) {
			setAttribute(servletConfig, categoryContainerName, categoryContainer);
		}
		
		public static LocationContainer getLocationContainer(ServletConfig servletConfig) {
			return (LocationContainer) getAttribute(servletConfig, locationContainerName);
		}

		public static void setLocationContainer(ServletConfig servletConfig, LocationContainer locationContainer) {
			setAttribute(servletConfig, locationContainerName, locationContainer);
		}
		*/
		//</editor-fold>
	}

	public static class Messages {
		//setQualcosa richiamate nelle servlet per impostare il messaggio.
		//consumeQualcosa richiamate nelle jsp. se restituisce true, allora la jsp deve visualizzare un messaggio.
		//i "messaggi" sono boolean. Il messaggio da stampare sarà deciso nella jsp.

		private static final String categoryContainerError = "categoryContainerErrorMessage"; //"Errore nel caricamento di categoryContainer"
		private static final String categoryContainerLoaded = "categoryContainerLoadedMessage"; //"categoryContainer è stato caricato dal database. Dovrebbe comparire solo la prima volta!!!"
		private static final String categoryContainerAlreadyLoaded = "categoryContainerAlreadyLoadedMessage"; //"categoryContainer già caricato"
		private static final String loginFailed = "loginFailedMessage";

		// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
		private static void setAttribute(HttpServletRequest request, String attributeName) {
			HttpSession session = request.getSession(true);
			session.setAttribute(attributeName, true);
		}

		private static boolean consumeAttribute(HttpServletRequest request, String attributeName) {
			HttpSession session = request.getSession(true);
			Boolean b = (Boolean) session.getAttribute(attributeName);
			session.removeAttribute(attributeName);
			return (b != null && b); //espressione necessaria per evitare NullPointerException. se b è null, restituisce false.
		}

		public static void setCategoryContainerError(HttpServletRequest request) {
			setAttribute(request, categoryContainerError);
		}

		public static boolean consumeCategoryContainerError(HttpServletRequest request) {
			return consumeAttribute(request, categoryContainerError);
		}

		public static void setCategoryContainerLoaded(HttpServletRequest request) {
			setAttribute(request, categoryContainerLoaded);
		}

		public static boolean consumeCategoryContainerLoaded(HttpServletRequest request) {
			return consumeAttribute(request, categoryContainerLoaded);
		}

		public static void setCategoryContainerAlreadyLoaded(HttpServletRequest request) {
			setAttribute(request, categoryContainerAlreadyLoaded);
		}

		public static boolean consumeCategoryContainerAlreadyLoaded(HttpServletRequest request) {
			return consumeAttribute(request, categoryContainerAlreadyLoaded);
		}

		public static void setLoginFailed(HttpServletRequest request) {
			setAttribute(request, loginFailed);
		}

		public static boolean consumeLoginFailed(HttpServletRequest request) {
			return consumeAttribute(request, loginFailed);
		}
		// </editor-fold>
	}
}
