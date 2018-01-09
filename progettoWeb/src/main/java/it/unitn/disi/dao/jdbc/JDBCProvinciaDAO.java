package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ProvinciaDAO;
import it.unitn.disi.dao.RegioneDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.locations.Provincia;
import it.unitn.disi.entities.locations.Regione;
import java.sql.Connection;
import javax.servlet.ServletException;

public class JDBCProvinciaDAO extends JDBCDAO<Provincia, Integer> implements ProvinciaDAO {

	private static final Class classe = Provincia.class;
	private static final String[] nomiColonne = new String[]{"id", "name", "id_regione"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, String.class, int.class};

	private RegioneDAO regioneDAO;

	public JDBCProvinciaDAO(Connection con) {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		regioneDAO = (RegioneDAO) initDao(RegioneDAO.class, daoFactory);
	}

	private void setPointers(Provincia p, boolean loadRegione) throws DAOException {
		if (p != null) {
			if (loadRegione) {
				p.setRegione(regioneDAO.getRegione(p.getIdRegione()));
			}
		}
	}

	@Override
	public Provincia getProvincia(int id, boolean loadRegione) throws DAOException {
		String query = "SELECT * FROM province WHERE id=?";
		Object[] parametriQuery = new Object[]{id};
		Provincia p = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		setPointers(p, loadRegione);
		return p;
	}

	@Override
	public Provincia[] getProvinceByIdRegione(int idRegione, Regione regione) throws DAOException {
		String query = "SELECT * FROM province WHERE id_regione = ? ORDER BY name ASC";
		Object[] parametriQuery = new Object[]{idRegione};
		Provincia[] province = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Provincia p : province) {
			p.setRegione(regione);
		}
		return province;
	}
}
