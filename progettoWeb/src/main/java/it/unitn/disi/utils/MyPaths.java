package it.unitn.disi.utils;

public class MyPaths {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// JSP ///////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static class Jsp {
		// contiene i percorsi delle jsp pubbliche e private, che sono tutte sotto web-inf/View.../Jsp
		// i percorsi delle pagine pubbliche sono i mapping specificati nel file web.xml
		// i percorsi delle pagine nascoste sono i percorsi sotto web-inf con cartelle e file con estensioni

		// <editor-fold defaultstate="collapsed" desc="Prefix e Funzioni">
		private static boolean viewTest;

		private static final String prefixViewFinal = "/progettoWeb/ViewFinal/";
		private static final String prefixViewTest = "/progettoWeb/ViewTest/";

		private static final String prefixHiddenViewFinal = "/WEB-INF/ViewFinal/jsp/";
		private static final String prefixHiddenViewTest = "/WEB-INF/ViewTest/jsp/";

		private static String p; //currentPrefix;
		private static String ph; //currentPrefixHidden;

		static {
			if(MyUtils.debugMyPathsMode){
				System.err.println("MyPaths");
			}
			if (MyUtils.useViewTest) {
				if(MyUtils.debugMyPathsMode){
					System.err.println("Mode Test");
				}
				setModeTest();
			} else {
				if(MyUtils.debugMyPathsMode){
					System.err.println("Mode Final");
				}
				setModeFinal();
			}
			//System.err.println(allHome);
		}

		private static void loadPrefixs() {
			p = viewTest ? prefixViewTest : prefixViewFinal;
			ph = viewTest ? prefixHiddenViewTest : prefixHiddenViewFinal;
		}

		public static void setModeFinal() {
			viewTest = false;
			loadPrefixs();
			loadPaths();
		}

		public static void setModeTest() {
			viewTest = true;
			loadPrefixs();
			loadPaths();
		}
		// </editor-fold>

		// pagine pubbliche
		public static String adminSegnalazione;
		public static String adminSegnalazioni;
		public static String adminSegnalazioniOpen;
		public static String allCart;
		public static String allHome;
		public static String allPaginaDiDecisione;
		public static String allProduct;
		public static String allProductList;
		public static String allShop;
		public static String allSeller;
		public static String anonymousForgottenPassword;
		public static String anonymousLogin;
		public static String anonymousRegistration;
		public static String anonymousRegistrationSeller;
		public static String debugCategoryContainer;
		public static String debugLocationContainer;
		public static String debugNavbarComplete;
		public static String payment;
		public static String sellerMySeller;
		public static String sellerMyShop;
		public static String sellerOrder;
		public static String sellerOrdersSeller;
		public static String sellerOrdersShop;
		public static String sellerSegnalazione;
		public static String sellerSegnalazioni;
		public static String sellerSegnalazioniOpen;
		public static String sellerSellProduct;
		public static String userChangePassword;
		public static String userOrder;
		public static String userOrders;
		public static String userProfile;
		public static String userRegistrationSellerLog;
		public static String userSegnalazione;
		public static String userSegnalazioni;
		public static String userSegnalazioniOpen;
		//public static final String allConfirmUser "confirmUser.jsp"; "ConfirmUser";

		// pagine/file nascosti
		public static String _utilsCategoryList;
		public static String _utilsFooter;
		public static String _utilsHeader;
		public static String _utilsNavbar;
		public static String _utilsSearchBar;
		public static String _errorPagesErrorDaoException; // pagina di errore quando una servlet riceve DAOException
		public static String _errorPagesPrintException;

		private static void loadPaths() {
			//nomi percorsi pubblici (visualizzabili direttamente dal client)
			adminSegnalazione = p + "SegnalazioneAdmin";
			adminSegnalazioni = p + "SegnalazioniAdmin";
			adminSegnalazioniOpen = p + "SegnalazioniOpenAdmin";
			allCart = p + "Cart";
			allHome = p + "Home";
			allPaginaDiDecisione = p + "PaginaDiDecisione";
			allProduct = p + "Product";
			allProductList = p + "ProductList";
			allSeller = p + "Seller";
			allShop = p + "Shop";
			anonymousForgottenPassword = p + "ForgottenPassword";
			anonymousLogin = p + "Login";
			anonymousRegistration = p + "Registration";
			anonymousRegistrationSeller = p + "RegistrationSeller";
			debugCategoryContainer = p + "CategoryContainer";
			debugLocationContainer = p + "LocationContainer";
			debugNavbarComplete = p + "NavbarComplete";
			payment = p + "Payment";
			sellerMySeller = p + "MySeller";
			sellerMyShop = p + "MyShop";
			sellerOrder = p + "OrderSeller";
			sellerOrdersSeller = p + "OrdersSeller";
			sellerOrdersShop = p + "OrdersShop";
			sellerSegnalazione = p + "SegnalazioneSeller";
			sellerSegnalazioni = p + "SegnalazioniSeller";
			sellerSegnalazioniOpen = p + "SegnalazioniOpenSeller";
			sellerSellProduct = p + "SellProduct";
			userChangePassword = p + "ChangePassword";
			userOrder = p + "OrderUser";
			userOrders = p + "OrdersUser";
			userProfile = p + "Profile";
			userRegistrationSellerLog = p + "RegistrationSellerLog";
			userSegnalazione = p + "SegnalazioneUser";
			userSegnalazioni = p + "SegnalazioniUser";
			userSegnalazioniOpen = p + "SegnalazioniOpenUser";

			//nomi percorsi privati (visualizzabili solamente dal server)
			_utilsCategoryList = ph + "utils/categoryList.jsp";
			_utilsFooter = ph + "utils/footer.jsp";
			_utilsHeader = ph + "utils/header.jsp";
			_utilsNavbar = ph + "utils/navbar.jsp";
			_utilsSearchBar = ph + "utils/searchBar.jsp";
			_errorPagesErrorDaoException = ph + "errorPages/errorDaoException.jsp";
			_errorPagesPrintException = ph + "errorPages/printException.jsp";
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// SERVLET ///////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static class Servlet {
		// contiene i percorsi delle servlet pubbliche e private.
		// le servlet pubbliche sono mappate nel file web.xml sotto il percorso "progettoWeb/NomeServlet" e sono accessibili anche dal client tramite url
		// le servlet privatee  sono mappate nel file web.xml sotto il percorso "/WEB-INF/servlet/NomeServlet" e sono accessibili solo dal lato server
		// le servlet di startup non sono specificate qui, ma solo nel file web.xml, perchè vengono richiamate soltanto all'avvio dell'applicazione

////////////////////////////////////// PUBBLICHE ///////////////////////////////////////////////////////////////////////////////
		public static class Pubbliche {
			// contiene i percorsi delle servlet pubbliche (accessibili anche dal client tramite url "progettoWeb/NomeServlet")
			// queste servlet sono quelle che svolgono azioni (es. login, addToCart, buyCart, register, sellProduct, ...)
			// in genere le azioni possono fallire (es. login fallito) e possono rimandare a pagine diverse a seconda del risultato
			// queste servlet saranno mappate in web.xml e non saranno sotto il percorso web-inf

			private static final String p = "/progettoWeb/";

			public static final String addReview = p + "AddReviewServlet";
			public static final String addToCart = p + "AddToCartServlet";
			public static final String addUserSegnalazione = p + "AddUserSegnalazione";
			public static final String buyCart = p + "BuyCartServlet";
			public static final String changePassword = p + "ChangePasswordServlet";
			public static final String changeProductQuantityCartServlet = p + "ChangeProductQuantityCartServlet";
			public static final String changeShopParams = p + "ChangeShopParamsServlet";
			public static final String deleteProductCartServlet = p + "DeleteProductCartServlet";
			public static final String confirmUser = p + "ConfirmUserServlet";
			public static final String forgottenPassword = p + "ForgottenPasswordServlet";
			public static final String login = p + "LoginServlet";
			public static final String logout = p + "LogoutServlet";
			public static final String rispondiSegnalazione = p + "RispondiSegnalazioneServlet";
			public static final String registration = p + "RegistrationServlet";
			public static final String registrationSeller = p + "RegistrationSellerServlet";
			public static final String registrationSellerLog = p + "RegistrationSellerLogServlet";

			public static final String sellProduct = p + "SellProductServlet";
			//inserire nuovo punto vendita???
			//modificare informazioni venditore/punto vendita???
			//segnare un ordine ricevuto come spedito???
		}

		public static class Privatee {
			// contiene i percorsi delle servlet private (accessibili solo dal server) (servlet mappate sotto WEB-INF/servlet/NomeServlet)
			// queste servlet sono quelle che leggono dati dal database (es. getProduct, getProductList, getOrders, ...)
			// in genere le query riescono sempre.
			// se "falliscono", restituiscono null e la jsp visualizzerà un messaggio (es. "non è stato trovato nessun prodotto").
			// altrimenti potrebbero fallire se la query è scritta male e lanciano un eccezione che causa un errore 500.

			// queste servlet sono richiamate dalle jsp che hanno bisogno di leggere dati dal database.
			// Quindi queste servlet non effettuano mai il forward/redirect
			// leggono dati, creano oggetti e li salvano nel model. in genere sono dati di request.
			// poi il controllo ritorna alla jsp che aveva chiamato la servlet.
			// queste servlet saranno mappate sotto il percorso WEB-INF/servlet/ nel file web.xml
			private static final String p = "/WEB-INF/servlet/";

			public static final String adminGetSegnalazione = p + "GetSegnalazioneAdminServlet";
			public static final String adminGetSegnalazioni = p + "GetSegnalazioniAdminServlet";
			public static final String adminGetSegnalazioniOpen = p + "GetSegnalazioniOpenAdminServlet";
			public static final String allGetCartUpdatedServlet = p + "GetCartUpdatedServlet";
			public static final String allGetProduct = p + "GetProductServlet";
			public static final String allGetProductList = p + "GetProductListServlet";
			public static final String allGetSeller = p + "GetSellerServlet";
			public static final String allGetShop = p + "GetShopServlet";
			public static final String sellerGetMySeller = p + "GetMyShopServlet";
			public static final String sellerGetMyShop = p + "GetMyShopServlet";
			public static final String sellerGetOrder = p + "GetOrderSellerServlet";
			public static final String sellerGetOrdersSeller = p + "GetOrdersSellerServlet";
			public static final String sellerGetOrdersShop = p + "GetOrdersShopServlet";
			public static final String sellerGetSegnalazione = p + "GetSegnalazioneSellerServlet";
			public static final String sellerGetSegnalazioni = p + "GetSegnalazioniSellerServlet";
			public static final String sellerGetSegnalazioniOpen = p + "GetSegnalazioniOpenSellerServlet";
			public static final String userGetOrder = p + "GetOrderUserServlet";
			public static final String userGetOrders = p + "GetOrdersUserServlet";
			public static final String userGetSegnalazione = p + "GetSegnalazioneUserServlet";
			public static final String userGetSegnalazioni = p + "GetSegnalazioniUserServlet";
			public static final String userGetSegnalazioniOpen = p + "GetSegnalazioniOpenUserServlet";
			public static final String allPaymentServlet = p + "PaymentServlet";
		}
	}
}

//public static final String getProductReview = prefix + "GetProductReview";
//get recensioni prodotto X (o dentro getProduct?)
//public static final String getMySellerInfo = prefix + "GetMySellerInfo"; //già effettuato nel login???
/*
//le pagine di errore sono pubbliche o private?

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
 */


/*
//servlet richiamate soltanto all'avvio del server perchè i dati rimangono costanti (categorie e location)
//salvano i dati in application
public static final String initLoadCategoryContainer = p + "LoadCategoryContainerServlet";
public static final String initLoadLocationContainer = p + "LoadLocationContainerServlet";
*/
