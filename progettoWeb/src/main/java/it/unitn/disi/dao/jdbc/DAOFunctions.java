package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.exceptions.DAOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFunctions {

	public static <T> T getOne(
			String query, Object[] queryParameters,
			Class classe, String[] nomiColonne, Class[] constructorParameterTypes,
			Connection CON) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			myPrepareStatement(ps, queryParameters);
			try (ResultSet rs = ps.executeQuery()) {
				T t = null;
				Constructor<T> constructor = myGetConstructor(classe, constructorParameterTypes);
				if (rs.next()) {
					Object[] parametri = new Object[nomiColonne.length];
					for (int i = 0; i < nomiColonne.length; i++) {
						parametri[i] = rs.getObject(nomiColonne[i]);
					}
					t = myNewInstance(constructor, parametri);
				}
				if (rs.next()) {
					String errore = "Errore: la query ha trovato più oggetti. Doveva trovare soltanto 1 oggetto.";
					System.err.println(errore);
					throw new DAOException(errore);
				}
				if (t == null) {
					String errore = "Warning: la query ha trovato 0 oggetti";
					System.err.println(errore);
				}
				return t;
			} catch (SQLException ex) {
				throw new DAOException("Errore SQLException executeQuery getOne: " + ex.getMessage(), ex);
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException prepareStatement getOne: " + ex.getMessage(), ex);
		}
	}

	public static <T> T[] getMany(
			String query, Object[] queryParameters,
			Class classe, String[] nomiColonne, Class[] constructorParameterTypes,
			Connection CON) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement(query)) {
			myPrepareStatement(ps, queryParameters);
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<T> tempListT = new ArrayList<>();
				Constructor<T> constructor = myGetConstructor(classe, constructorParameterTypes);
				while (rs.next()) {
					Object[] parametri = new Object[nomiColonne.length];
					for (int i = 0; i < nomiColonne.length; i++) {
						parametri[i] = rs.getObject(nomiColonne[i]);
					}
					T t = myNewInstance(constructor, parametri);
					tempListT.add(t);
				}
				if (tempListT.isEmpty()) {
					String errore = "Warning: la query ha trovato 0 oggetti";
					System.err.println(errore);
				}
				T[] res = (T[]) Array.newInstance(classe, tempListT.size());
				res = tempListT.toArray(res);
				return res;
			} catch (SQLException ex) {
				throw new DAOException("Errore SQLException executeQuery getMany: " + ex.getMessage(), ex);
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException prepareStatement getMany: " + ex.getMessage(), ex);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="myFunctions private">
	//prepara la query, sostituendo i '?' con i valori passati.
	private static void myPrepareStatement(PreparedStatement ps, Object[] queryParameters) throws DAOException {
		try {
			for (int i = 0; i < queryParameters.length; i++) {
				if (queryParameters[i] instanceof Integer) {
					ps.setInt(i + 1, (Integer) queryParameters[i]);
				} else if (queryParameters[i] instanceof Float) {
					ps.setFloat(i + 1, (Float) queryParameters[i]);
				} else if (queryParameters[i] instanceof String) {
					ps.setString(i + 1, (String) queryParameters[i]);
				} else if (queryParameters[i] instanceof Timestamp) {
					ps.setTimestamp(i + 1, (Timestamp) queryParameters[i]);
				} else {
					String errore
							= "Errore: il parametro non appartiene ad un tipo supportato(int, float, string, timestamp)."
							+ "\n  Parametro class: " + queryParameters[i].getClass()
							+ "\n  Parametro value: " + queryParameters[i].toString();
					System.err.println(errore);
					throw new DAOException(errore);
				}
			}
		} catch (SQLException ex) {
			String errore = "Errore SQLException in myPrepareStatement: " + ex.getMessage();
			System.err.println(errore);
			throw new DAOException(errore);
		}
	}

	//restituisce il costruttore corretto e gestisce tutte le eccezioni. Se c'è un'eccezione lancia una DAOException
	private static <T> Constructor<T> myGetConstructor(Class classe, Class[] constructorParameterTypes) throws DAOException {
		try {
			Constructor<T> constructor = classe.getConstructor(constructorParameterTypes);
			return constructor;
		} catch (NoSuchMethodException ex) {
			String errore = "Errore NoSuchMethodException myGetConstructor: " + ex.getMessage();
			System.err.println(errore);
			Logger.getLogger(DAOFunctions.class.getName()).log(Level.SEVERE, null, ex);
			throw new DAOException(errore, ex);
		} catch (SecurityException ex) {
			String errore = "Errore SecurityException myGetConstructor: " + ex.getMessage();
			System.err.println(errore);
			Logger.getLogger(DAOFunctions.class.getName()).log(Level.SEVERE, null, ex);
			throw new DAOException(errore, ex);
		}
	}

	//crea una nuova istanza e gestisce tutte le eccezioni. Se c'è un'eccezione lancia una DAOException
	private static <T> T myNewInstance(Constructor<T> constructor, Object[] parametri) throws DAOException {
		try {
			T t = constructor.newInstance(parametri);
			return t;
		} catch (InstantiationException ex) {
			String errore = "Errore InstantiationException myNewInstance: " + ex.getMessage();
			System.err.println(errore);
			Logger.getLogger(DAOFunctions.class.getName()).log(Level.SEVERE, null, ex);
			throw new DAOException(errore, ex);
		} catch (IllegalAccessException ex) {
			String errore = "Errore IllegalAccessException myNewInstance: " + ex.getMessage();
			System.err.println(errore);
			Logger.getLogger(DAOFunctions.class.getName()).log(Level.SEVERE, null, ex);
			throw new DAOException(errore, ex);
		} catch (IllegalArgumentException ex) {
			String errore = "Errore IllegalArgumentException myNewInstance: " + ex.getMessage();
			System.err.println(errore);
			Logger.getLogger(DAOFunctions.class.getName()).log(Level.SEVERE, null, ex);
			throw new DAOException(errore, ex);
		} catch (InvocationTargetException ex) {
			String errore = "Errore InvocationTargetException myNewInstance: " + ex.getMessage();
			System.err.println(errore);
			Logger.getLogger(DAOFunctions.class.getName()).log(Level.SEVERE, null, ex);
			throw new DAOException(errore, ex);
		}
	}
	// </editor-fold>

}


/*
	ESEMPIO DI UTILIZZO
	da scrivere in JDBCqualcosaDAO.java

	@Override
	public void prova(int id) throws DAOException {
		String query = "SELECT * FROM users WHERE id = ?";
		Object[] parametriQuery = new Object[]{id};
		Class classe = User.class;
		String[] nomiColonne = new String[]{"id", "username", "email", "password", "first_name", "last_name", "user_hash", "verificato"};
		Class[] constructorParameterTypes = new Class[]{int.class, String.class, String.class, String.class, String.class, String.class, String.class, boolean.class};

		User s = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
	}
 */
