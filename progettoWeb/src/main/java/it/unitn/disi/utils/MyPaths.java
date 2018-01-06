package it.unitn.disi.utils;

public class MyPaths {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// PUBLIC ///////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static class Public {
		// contiene i percorsi pubblici (accessibili dal client)
		// contiene sia jsp, che servlet
		// tutti questi percorsi sono i mapping specificati nel file web.xml
		
		private static final String prefix = "/progettoWeb/";

////////////////////////////////////// JSP ///////////////////////////////////////////////////////////////////////////////////
		public static class Jsp {
			// contiene i percorsi delle jsp pubbliche, che sono sotto web-inf/public
			// i percorsi di queste pagine sono i mapping specificati nel file web.xml

			private static final String prefix = Public.prefix + "prova/";

			public static class All {

				public static final String cart = prefix + "Cart";
				public static final String home = prefix + "Home";
				public static final String product = prefix + "Product";
				public static final String productList = prefix + "ProductList";
				public static final String shop = prefix + "Shop";
				public static final String seller = prefix + "Seller";
			}

			public static class Anonymous {

				public static final String login = prefix + "Login";
				public static final String registration = prefix + "Registration";
				public static final String registrationSeller = prefix + "RegistrationSeller";
				public static final String forgottenPassword = prefix + "ForgottenPassword";
			}

			public static class User {

				public static final String profile = prefix + "Profile";
				public static final String changePassword = prefix + "ChangePassword";
				public static final String order = prefix + "OrderUser";
				public static final String orders = prefix + "OrdersUser";
				public static final String segnalazione = prefix + "SegnalazioneUser";
				public static final String segnalazioni = prefix + "SegnalazioniUser";
				public static final String segnalazioniOpen = prefix + "SegnalazioniOpenUser";
				public static final String registrationSellerLog = prefix + "RegistrationSellerLog";
			}

			public static class Seller {

				public static final String order = prefix + "OrderSeller";
				public static final String ordersSeller = prefix + "OrdersSeller";
				public static final String ordersShop = prefix + "OrdersShop";
				public static final String segnalazione = prefix + "SegnalazioneSeller";
				public static final String segnalazioni = prefix + "SegnalazioniSeller";
				public static final String segnalazioniOpen = prefix + "SegnalazioniOpenSeller";
				public static final String mySeller = prefix + "MySeller";
				public static final String myShop = prefix + "MyShop";
				public static final String sellProduct = prefix + "SellProduct";
			}

			public static class Admin {

				public static final String segnalazione = prefix + "SegnalazioneSeller";
				public static final String segnalazioni = prefix + "SegnalazioniSeller";
				public static final String segnalazioniOpen = prefix + "SegnalazioniOpenSeller";
			}

			public static class Debug {

				public static final String locationContainer = prefix + "LocationContainer";
				public static final String categoryContainer = prefix + "CategoryContainer";
				public static final String navbarComplete = prefix + "NavbarComplete";
			}
		}

////////////////////////////////////// SERVLET ///////////////////////////////////////////////////////////////////////////////
		public static class Servlet {
			// contiene i percorsi delle servlet pubbliche
			// queste servlet sono quelle che svolgono azioni (es. login, addToCart, buyCart, register, sellProduct, ...)
			// in genere le azioni possono fallire (es. login fallito) e possono rimandare a pagine diverse a seconda del risultato
			// queste servlet saranno mappate in web.xml e non saranno sotto il percorso web-inf

			private static final String prefix = Public.prefix + "";
			
			//da aggiungere Register Seller no Log
			//da aggiungere Register Seller
			public static class Anonymous {

				public static final String login = prefix + "LoginServlet";
				public static final String registration = prefix + "RegistrationServlet";
				public static final String registrationSeller = prefix + "RegistrationSellerServlet";
				public static final String forgottenPassword = prefix + "ForgottenPasswordServlet";
				public static final String confirmUser = prefix + "ConfirmUserServlet";
			}

			public static class User {

				public static final String addToCart = prefix + "AddToCartServlet";
				public static final String buyCart = prefix + "BuyCartServlet";
				public static final String changePassword = prefix + "ChangePasswordServlet";
				public static final String logout = prefix + "LogoutServlet";
				public static final String registrationSellerLog = prefix + "RegistrationSellerLogServlet";
			}

			public static class Seller {

				public static final String sellProduct = prefix + "SellProductServlet";
				//inserire nuovo punto vendita???
				//modificare informazioni venditore/punto vendita???
				//segnare un ordine ricevuto come spedito???
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// PRIVATE ///////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static class Private {
		// contiene i percorsi private (accessibili solo dal server)
		// contiene sia jsp, che servlet
		// tutti questi percorsi sono sotto WEB-INF (anche le servlet sono mappate sotto WEB-INF)

		private static final String prefix = "/WEB-INF/";

////////////////////////////////////// JSP ///////////////////////////////////////////////////////////////////////////////////
		public static class Jsp {
			// contiene i percorsi delle jsp pubbliche, che sono sotto web-inf/public
			// i percorsi di queste pagine sono i mapping specificati nel file web.xml
			
			private static final boolean test = MyUtils.useTestJSP; //se impostato a true, utilizza le pagine di test
			private static final String testString = (test ? "test/" : "");
			private static final String prefix = Private.prefix + testString + "jsp/";

			public static class All {

				private static final String prefix = Jsp.prefix + "all/";

				public static final String cart = prefix + "cart.jsp";
				public static final String home = prefix + "home.jsp";
				public static final String product = prefix + "product.jsp";
				public static final String productList = prefix + "productList.jsp";
				public static final String shop = prefix + "shop.jsp";
				public static final String seller = prefix + "seller.jsp";
			}

			public static class Anonymous {

				private static final String prefix = Jsp.prefix + "anonymous/";

				public static final String login = prefix + "login.jsp";
				public static final String registration = prefix + "registration.jsp";
				public static final String registrationSeller = prefix + "registrationSeller.jsp";
				public static final String forgottenPassword = prefix + "forgottenPassword.jsp";
				public static final String confirmUser = prefix + "confirmUser.jsp";
			}

			public static class User {

				private static final String prefix = Jsp.prefix + "user/";

				public static final String profile = prefix + "profile.jsp";
				public static final String changePassword = prefix + "changePassword.jsp";
				public static final String order = prefix + "order.jsp";
				public static final String orders = prefix + "orders.jsp";
				public static final String segnalazione = prefix + "segnalazione.jsp";
				public static final String segnalazioni = prefix + "segnalazioni.jsp";
				public static final String segnalazioniOpen = prefix + "segnalazioniOpen.jsp";
				public static final String registrationSellerLog = prefix + "registrationSellerLog.jsp";
			}

			public static class Seller {

				private static final String prefix = Jsp.prefix + "seller/";

				public static final String order = prefix + "order.jsp";
				public static final String ordersSeller = prefix + "ordersSeller.jsp";
				public static final String ordersShop = prefix + "ordersShop.jsp";
				public static final String segnalazione = prefix + "segnalazione.jsp";
				public static final String segnalazioni = prefix + "segnalazioni.jsp";
				public static final String segnalazioniOpen = prefix + "segnalazioniOpen.jsp";
				public static final String mySeller = prefix + "mySeller.jsp";
				public static final String myShop = prefix + "myShop.jsp";
				public static final String sellProduct = prefix + "sellProduct.jsp";
			}

			public static class Admin {

				private static final String prefix = Jsp.prefix + "admin/";

				public static final String segnalazione = prefix + "segnalazione.jsp";
				public static final String segnalazioni = prefix + "segnalazioni.jsp";
				public static final String segnalazioniOpen = prefix + "segnalazioniOpen.jsp";
			}

			public static class Utils {

				private static final String prefix = Jsp.prefix + "utils/";

				public static final String header = prefix + "header.jsp";
				public static final String navbar = prefix + "navbar.jsp";
				public static final String searchBar = prefix + "searchBar.jsp";
				public static final String footer = prefix + "footer.jsp";
			}

			public static class Debug {

				private static final String prefix = Jsp.prefix + "debug/";

				public static final String locationContainer = prefix + "locationContainer.jsp";
				public static final String categoryContainer = prefix + "categoryContainer.jsp";
				public static final String navbarComplete = prefix + "navbarComplete.jsp";
			}

			public static class ErrorPages {

				private static final String prefix = Jsp.prefix + "errorPages/";

				public static final String error404 = prefix + "error404.jsp"; //Not Found
				public static final String error405 = prefix + "error405.jsp"; //Method forbidden
				public static final String error500 = prefix + "error500.jsp"; //Internal Server Error
				public static final String errorDAOException = prefix + "errorDAOException.jsp"; //raggiunta solo con un forward diretto. perchè le servet catturano DAOException e buttano ServletException che diventa error 500.
				public static final String errorIOException = prefix + "errorIOException.jsp";
				public static final String errorNullPointerException = prefix + "errorNullPointerException.jsp";
				public static final String errorServletException = prefix + "errorServletException.jsp";//non utilizzata
				public static final String printException = prefix + "printException.jsp";
			}
		}

////////////////////////////////////// SERVLET ///////////////////////////////////////////////////////////////////////////////
		public static class Servlet {
			// contiene i percorsi delle servlet private
			// queste servlet sono quelle che leggono dati dal database (es. getProduct, getProductList, getOrders, ...)
			// in genere le query riescono sempre.
			// se "falliscono", restituiscono null e la jsp visualizzerà un messaggio (es. "non è stato trovato nessun prodotto").
			// altrimenti potrebbero fallire se la query è scritta male e lanciano un eccezione che causa un errore 500.

			// queste servlet sono richiamate dalle jsp che hanno bisogno di leggere dati dal database.
			// Quindi queste servlet non effettuano mai il forward
			// leggono dati, creano oggetti e li salvano nel model. in genere sono dati di request (oppure application).
			// poi il controllo ritorna alla jsp che aveva chiamato la servlet.
			// queste servlet saranno mappate sotto il percorso WEB-INF/servlet/ nel file web.xml
			private static final String prefix = Private.prefix + "servlet/";

			public static class All {
				public static final String getProduct = Servlet.prefix + "GetProductServlet";
				public static final String getProductList = Servlet.prefix + "GetProductListServlet";
				public static final String getShop = Servlet.prefix + "GetShopServlet";
				//public static final String getProductReview = prefix + "GetProductReview";
				//get recensioni prodotto X (o dentro getProduct?)
			}

			public static class User {
				public static final String getOrder = Servlet.prefix + "GetOrderUserServlet";
				public static final String getOrders = Servlet.prefix + "GetOrdersUserServlet";
				public static final String getSegnalazione = Servlet.prefix + "GetSegnalazioneUserServlet";
				public static final String getSegnalazioni = Servlet.prefix + "GetSegnalazioniUserServlet";
				public static final String getSegnalazioniOpen = Servlet.prefix + "GetSegnalazioniOpenUserServlet";
			}

			public static class Seller {
				public static final String getMyShops = Servlet.prefix + "GetMyShopsServlet";
				//public static final String getMySellerInfo = prefix + "GetMySellerInfo"; //già effettuato nel login???
				public static final String getOrder = Servlet.prefix + "GetOrderSellerServlet";
				public static final String getOrdersSeller = Servlet.prefix + "GetOrdersSellerServlet";
				public static final String getOrdersShop= Servlet.prefix + "GetOrdersShopServlet";
				public static final String getSegnalazione = Servlet.prefix + "GetSegnalazioneSellerServlet";
				public static final String getSegnalazioni = Servlet.prefix + "GetSegnalazioniSellerServlet";
				public static final String getSegnalazioniOpen = Servlet.prefix + "GetSegnalazioniOpenSellerServlet";
			}
			
			public static class Admin {
				public static final String getSegnalazione = Servlet.prefix + "GetSegnalazioneAdminServlet";
				public static final String getSegnalazioni = Servlet.prefix + "GetSegnalazioniAdminServlet";
				public static final String getSegnalazioniOpen = Servlet.prefix + "GetSegnalazioniOpenAdminServlet";
			}

			public static class Init {
				//servlet richiamate soltanto all'avvio del server perchè i dati rimangono costanti (categorie e location)
				//salvano i dati in application
				public static final String loadCategoryContainer = Servlet.prefix + "LoadCategoryContainerServlet";
				public static final String loadLocationContainer = Servlet.prefix + "LoadLocationContainerServlet";
			}
		}
	}

}
