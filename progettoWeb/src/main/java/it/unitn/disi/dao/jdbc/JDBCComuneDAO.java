package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ComuneDAO;
import it.unitn.disi.dao.ProvinciaDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.locations.Comune;
import it.unitn.disi.entities.locations.Provincia;
import java.sql.Connection;
import javax.servlet.ServletException;

public class JDBCComuneDAO extends JDBCDAO<Comune, Integer> implements ComuneDAO {

	private static final Class classe = Comune.class;
	private static final String[] nomiColonne = new String[]{"id", "name", "id_provincia"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, String.class, int.class};

	private ProvinciaDAO provinciaDAO;

	public JDBCComuneDAO(Connection con) {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		provinciaDAO = (ProvinciaDAO) initDao(ProvinciaDAO.class, daoFactory);
	}

	private void setPointers(Comune c, boolean loadProvincia) throws DAOException {
		if (c != null) {
			if (loadProvincia) {
				c.setProvincia(provinciaDAO.getProvincia(c.getIdProvincia(), true));
			}
		}
	}

	@Override
	public Comune getComune(int id, boolean loadProvincia) throws DAOException {
		String query = "SELECT * FROM comuni WHERE id=?";
		Object[] parametriQuery = new Object[]{id};
		Comune c = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		setPointers(c, loadProvincia);
		return c;
	}

	@Override
	public Comune[] getComuniByIdProvincia(int idProvincia, Provincia provincia) throws DAOException {
		String query = "SELECT * FROM comuni WHERE id_provincia = ? ORDER BY name ASC";
		Object[] parametriQuery = new Object[]{idProvincia};
		Comune[] comuni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Comune c : comuni) {
			c.setProvincia(provincia);
		}
		return comuni;
	}
}
