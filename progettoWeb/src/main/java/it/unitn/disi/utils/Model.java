package it.unitn.disi.utils;

import it.unitn.disi.entities.Product;
import it.unitn.disi.entities.User;
import it.unitn.disi.entities.categories.CategoryContainer;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Model {

	public static class Request {

		private static final String productName = "product";
		private static final String productListName = "products";

		private static Object getAttribute(HttpServletRequest request, String attributeName) {
			Object object = (Object) request.getAttribute(attributeName);
			return object;
		}

		private static void setAttribute(HttpServletRequest request, String attributeName, Object object) {
			request.setAttribute(attributeName, object);
		}

		public static void setProduct(HttpServletRequest request, Product product) {
			setAttribute(request, productName, product);
		}

		public static Product getProduct(HttpServletRequest request) {
			return (Product) getAttribute(request, productName);
		}
		
		public static void setProductList(HttpServletRequest request, List<Product> products) {
			setAttribute(request, productListName, products);
		}

		public static List<Product> getProductList(HttpServletRequest request) {
			return (List<Product>) getAttribute(request, productListName);
		}

	}

	public static class Session {

		private static final String userName = "user";

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
	}

	public static class Application {

		private static final String categoryContainerName = "categoryContainer";

		private static Object getAttribute(HttpServletRequest request, String attributeName) {
			ServletContext sc = request.getServletContext();
			Object object = sc.getAttribute(categoryContainerName);
			return object;
		}

		private static void setAttribute(HttpServletRequest request, String attributeName, Object object) {
			ServletContext sc = request.getServletContext();
			sc.setAttribute(attributeName, object);
		}

		public static CategoryContainer getCategoryContainer(HttpServletRequest request) {
			return (CategoryContainer) getAttribute(request, categoryContainerName);
		}

		public static void setCategoryContainer(HttpServletRequest request, CategoryContainer categoryContainer) {
			setAttribute(request, categoryContainerName, categoryContainer);
		}
	}

	public static class Messages {
		//setQualcosa richiamate nelle servlet per impostare il messaggio.
		//consumeQualcosa richiamate nelle jsp. se restituisce true, allora la jsp deve visualizzare un messaggio.

		private static final String categoryContainerError = "categoryContainerErrorMessage"; //"Errore nel caricamento di categoryContainer"
		private static final String categoryContainerLoaded = "categoryContainerLoadedMessage"; //"categoryContainer è stato caricato dal database. Dovrebbe comparire solo la prima volta!!!"
		private static final String categoryContainerAlreadyLoaded = "categoryContainerAlreadyLoadedMessage"; //"categoryContainer già caricato"
		private static final String loginFailed = "loginFailedMessage";

		private static void setAttribute(HttpServletRequest request, String attributeName) {
			HttpSession session = request.getSession(true);
			session.setAttribute(attributeName, true);
		}

		private static boolean consumeAttribute(HttpServletRequest request, String attributeName) {
			HttpSession session = request.getSession(true);
			boolean b = (boolean) session.getAttribute(attributeName);
			session.removeAttribute(attributeName);
			return b;
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
	}
}
