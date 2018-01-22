package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.RegioneDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.Regione;
import java.sql.Connection;

public class JDBCRegioneDAO extends JDBCDAO<Regione, Integer> implements RegioneDAO {

	private static final Class classe = Regione.class;
	private static final String[] nomiColonne = new String[]{"id", "name"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, String.class};

	public JDBCRegioneDAO(Connection con) {
		super(con);
	}

	@Override
	public Regione getRegione(int id) throws DAOException {
		String query = "SELECT * FROM regioni WHERE id=?";
		Object[] parametriQuery = new Object[]{id};
		Regione r = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return r;
	}

	@Override
	public Regione[] getRegioni() throws DAOException {
		String query = "SELECT * FROM regioni ORDER BY name ASC";
		Object[] parametriQuery = new Object[]{};
		Regione[] regioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return regioni;
	}
}
