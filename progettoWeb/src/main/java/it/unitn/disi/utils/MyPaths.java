package it.unitn.disi.utils;

public class MyPaths {

	private static final boolean test = true;
	private static final String testString = (test ? "test/" : "");
	private static final String prefix = "/WEB-INF/" + testString;

	public static class All {
		private static final String prefix = MyPaths.prefix + "jsp/all/";

		public static final String home = prefix + "home.jsp";
		public static final String product = prefix + "product.jsp";
		public static final String productList = prefix + "productList.jsp";
		public static final String shop = prefix + "shop.jsp";
	}

	public static class Anonymous {
		private static final String prefix = MyPaths.prefix + "jsp/anonymous/";

		public static final String login = prefix + "login.jsp";
		public static final String register = prefix + "register.jsp";
		public static final String passwordDimenticata = prefix + "passwordDimenticata.jsp";
	}

	public static class User {
		private static final String prefix = MyPaths.prefix + "jsp/user/";

		public static final String profile = prefix + "profile.jsp";
		public static final String cart = prefix + "cart.jsp";
		public static final String logout = prefix + "logout.jsp";
		public static final String orderList = prefix + "orderList.jsp";
		public static final String order = prefix + "order.jsp";
		public static final String segnalazione = prefix + "segnalazione.jsp";
		public static final String segnalazioneList = prefix + "segnalazioneList.jsp";
	}

	public static class Seller {

		private static final String prefix = MyPaths.prefix + "jsp/user/";
		public static final String segnalazione = prefix + "segnalazione.jsp";
		public static final String segnalazioneList = prefix + "segnalazioneList.jsp";
	}

	public static class Admin {
		private static final String prefix = MyPaths.prefix + "jsp/admin/";

		public static final String segnalazione = prefix + "segnalazione.jsp";
		public static final String segnalazioneList = prefix + "segnalazioneList.jsp";
	}

	public static class Utils {
		private static final String prefix = MyPaths.prefix + "jsp/utils/";

		public static final String navbar = prefix + "navbar.jsp";
		public static final String footer = prefix + "footer.jsp";
	}
	
	public static class Debug {
		private static final String prefix = MyPaths.prefix + "jsp/debug/";

		public static final String locations = prefix + "location.jsp";
		public static final String categoryContainer = prefix + "categoryContainer.jsp";
	}

	public static class ErrorPages {
		private static final String prefix = MyPaths.prefix + "jsp/errorPages/";

		public static final String errorDAOException = prefix + "errorDAOException.jsp";
		public static final String error404 = prefix + "errorDAOException.jsp";
		public static final String error405 = prefix + "errorDAOException.jsp";
		public static final String error500 = prefix + "errorDAOException.jsp";
		public static final String errorIOException = prefix + "errorDAOException.jsp";
		public static final String errorNullPointerException = prefix + "errorDAOException.jsp";
	}

}
