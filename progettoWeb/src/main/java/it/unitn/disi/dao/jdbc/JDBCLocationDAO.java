package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.LocationDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.locations.Comune;
import it.unitn.disi.entities.locations.LocationContainer;
import it.unitn.disi.entities.locations.Provincia;
import it.unitn.disi.entities.locations.Regione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCLocationDAO extends JDBCDAO<LocationContainer, Integer> implements LocationDAO {

	private static final int ESTIMATED_SIZE_REGIONI = 20;
	private static final int ESTIMATED_SIZE_PROVINCE = 110;
	private static final int ESTIMATED_SIZE_COMUNI = 8096;
	private static final int HASHMAP_INITIAL_CAPACITY_REGIONI = (int) (ESTIMATED_SIZE_REGIONI / 0.75f) + 1;
	private static final int HASHMAP_INITIAL_CAPACITY_PROVINCE = (int) (ESTIMATED_SIZE_PROVINCE / 0.75f) + 1;
	private static final int HASHMAP_INITIAL_CAPACITY_COMUNI = (int) (ESTIMATED_SIZE_COMUNI / 0.75f) + 1;

	public JDBCLocationDAO(Connection con) {
		super(con);
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
		regioni = getRegioni();
		for (Regione r : regioni) {
			Provincia[] province = getProvinceByIdRegione(r.getId(), r);
			r.setProvince(province);
			regioniHash.put(r.getId(), r);
			for (Provincia p : province) {
				Comune[] comuni = getComuniByIdProvincia(p.getId(), p);
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

	private Regione[] getRegioni() throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM regioni ORDER BY name ASC")) {
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Regione> regioni_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
				while (rs.next()) {
					Regione r = new Regione(
							rs.getInt("id"),
							rs.getString("name")
					);
					regioni_temp.add(r);
				}
				Regione[] regioni = new Regione[regioni_temp.size()];
				regioni = regioni_temp.toArray(regioni); //trasforma arrayList in un array statico
				return regioni;
			}
		} catch (SQLException ex) {
			throw new DAOException("Impossible to get the list of regioni", ex);
		}
	}

	private Provincia[] getProvinceByIdRegione(int idRegione, Regione regione) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM province WHERE id_regione = ? ORDER BY name ASC")) {
			ps.setInt(1, idRegione);
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Provincia> province_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
				while (rs.next()) {
					Provincia p = new Provincia(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("id_regione"),
							regione
					);
					province_temp.add(p);
				}
				Provincia[] province = new Provincia[province_temp.size()];
				province = province_temp.toArray(province); //trasforma arrayList in un array statico
				return province;
			}
		} catch (SQLException ex) {
			throw new DAOException("Impossible to get the list of province", ex);
		}
	}

	private Comune[] getComuniByIdProvincia(int idProvincia, Provincia provincia) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM comuni WHERE id_provincia = ? ORDER BY name ASC")) {
			ps.setInt(1, idProvincia);
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Comune> comuni_temp = new ArrayList<>(); //uso ArrayList perchè non posso ricavare direttamente la lunghezza da ResultSet
				while (rs.next()) {
					Comune c = new Comune(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("id_provincia"),
							provincia
					);
					comuni_temp.add(c);
				}
				Comune[] comuni = new Comune[comuni_temp.size()];
				comuni = comuni_temp.toArray(comuni); //trasforma arrayList in un array statico
				return comuni;
			}
		} catch (SQLException ex) {
			throw new DAOException("Impossible to get the list of comuni", ex);
		}
	}
}
