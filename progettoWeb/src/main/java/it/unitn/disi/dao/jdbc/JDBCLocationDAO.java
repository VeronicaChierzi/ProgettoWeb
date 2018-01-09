package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ComuneDAO;
import it.unitn.disi.dao.LocationDAO;
import it.unitn.disi.dao.ProvinciaDAO;
import it.unitn.disi.dao.RegioneDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.locations.Comune;
import it.unitn.disi.entities.locations.LocationContainer;
import it.unitn.disi.entities.locations.Provincia;
import it.unitn.disi.entities.locations.Regione;
import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.ServletException;

public class JDBCLocationDAO extends JDBCDAO<LocationContainer, Integer> implements LocationDAO {

	private static final int ESTIMATED_SIZE_REGIONI = 20;
	private static final int ESTIMATED_SIZE_PROVINCE = 110;
	private static final int ESTIMATED_SIZE_COMUNI = 8096;
	private static final int HASHMAP_INITIAL_CAPACITY_REGIONI = (int) (ESTIMATED_SIZE_REGIONI / 0.75f) + 1;
	private static final int HASHMAP_INITIAL_CAPACITY_PROVINCE = (int) (ESTIMATED_SIZE_PROVINCE / 0.75f) + 1;
	private static final int HASHMAP_INITIAL_CAPACITY_COMUNI = (int) (ESTIMATED_SIZE_COMUNI / 0.75f) + 1;

	private RegioneDAO regioneDAO;
	private ProvinciaDAO provinciaDAO;
	private ComuneDAO comuneDAO;

	public JDBCLocationDAO(Connection con) {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		regioneDAO = (RegioneDAO) initDao(RegioneDAO.class, daoFactory);
		provinciaDAO = (ProvinciaDAO) initDao(ProvinciaDAO.class, daoFactory);
		comuneDAO = (ComuneDAO) initDao(ComuneDAO.class, daoFactory);
	}

	@Override
	public LocationContainer getLocation() throws DAOException {
		Regione[] regioni;
		HashMap<Integer, Regione> regioniHash = new HashMap<>(HASHMAP_INITIAL_CAPACITY_REGIONI);
		HashMap<Integer, Provincia> provinceHash = new HashMap<>(HASHMAP_INITIAL_CAPACITY_PROVINCE);
		HashMap<Integer, Comune> comuniHash = new HashMap<>(HASHMAP_INITIAL_CAPACITY_COMUNI);

		//per ogni regione, imposta l'array delle province e aggiunge la regione all'hashmap
		//per ogni provincia, imposta l'array dei comuni e aggiunge la provincia all'hashmap
		//per ogni comune, aggiunge il comune all'hashmap
		regioni = regioneDAO.getRegioni();
		for (Regione r : regioni) {
			Provincia[] province = provinciaDAO.getProvinceByIdRegione(r.getId(), r);
			r.setProvince(province);
			regioniHash.put(r.getId(), r);
			for (Provincia p : province) {
				Comune[] comuni = comuneDAO.getComuniByIdProvincia(p.getId(), p);
				p.setComuni(comuni);
				provinceHash.put(p.getId(), p);
				for (Comune c : comuni) {
					comuniHash.put(c.getId(), c);
				}
			}
		}
		LocationContainer l = new LocationContainer(regioni, regioniHash, provinceHash, comuniHash);
		return l;
	}
}
