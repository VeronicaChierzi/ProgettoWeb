package it.unitn.disi.utils;

import it.unitn.disi.entities.categories.Category;
import it.unitn.disi.entities.categories.Subcategory;
import it.unitn.disi.entities.locations.Comune;
import it.unitn.disi.entities.locations.Provincia;
import it.unitn.disi.entities.locations.Regione;

public class MyUtils {
	
	//se impostato a true, invia un'email quando un utente si registra.
	public static final boolean sendRegistrationConfirmEmail = false;

	//se impostato a true, utilizza le pagine jsp di test(senza grafica)
	public static final boolean useViewTest = true;

	//se impostato a true, stampa il debug del login/registrazione/logout dell'utente
	public static final boolean debugUserController = false;
	
	//se impostato a true, stampa un messaggio di debug quando MySerlvet effettua un forward, un redirect o un include
	//funziona solo sulle MyServlet. Le JSP non stampano nulla.
	public static final boolean debugServletForwardRedirectInclude = true;

	//se impostato a true, stampa un messaggio di debug quando si utilizzano le funzioni di DAOFunctions
	public static final boolean debugDAOFunctions = false;

	//se impostato a true, stampa un messaggio di debug quando si utilizzano le funzioni di DAOFunctions
	//anche per le undebuggableClass
	//(categoryContainer e locationContainer danno output fastidiosi, ri-eseguiti ad ogni avvio/deployment)
	private static final boolean debugDAOFunctionsUndebuggableClass = false;
	private static final Class[] undebuggableClass = new Class[]{ //classi da non stampare in DAOFunctions
		Category.class, Subcategory.class, Regione.class, Provincia.class, Comune.class
	};

	//controlla se la classe passata è da stampare
	public static boolean isDebuggableClass(Class classe) {
		if(debugDAOFunctionsUndebuggableClass){
			return true;
		}
		for (Class c : undebuggableClass) {
			if(classe==c){
				return false;
			}
		}
		return true;
	}

}
