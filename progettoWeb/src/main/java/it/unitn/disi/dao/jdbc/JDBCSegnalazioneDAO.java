package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.SegnalazioneDAO;
import it.unitn.disi.dao.SegnalazioneRispostaDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.Segnalazione;
import java.sql.Connection;
import java.sql.Timestamp;
import javax.servlet.ServletException;

public class JDBCSegnalazioneDAO extends JDBCDAO<Segnalazione, Integer> implements SegnalazioneDAO {

	private static final Class classe = Segnalazione.class;
	private static final String[] nomiColonne = new String[]{"id", "id_order", "title", "description", "datetime"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, String.class, String.class, Timestamp.class};

	private SegnalazioneRispostaDAO segnalazioneRispostaDAO;

	public JDBCSegnalazioneDAO(Connection con) {
		super(con);
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
		segnalazioneRispostaDAO = (SegnalazioneRispostaDAO) initDao(SegnalazioneRispostaDAO.class, daoFactory);
	}

/////////////////////////////////////////////// ADMIN /////////////////////////////////////////////////////////////////////////////////
	@Override
	public Segnalazione[] getSegnalazioniAdmin() throws DAOException {
		String query = "SELECT * FROM segnalazioni ORDER BY datetime DESC";
		Object[] parametriQuery = new Object[]{};
		Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Segnalazione s : segnalazioni) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return segnalazioni;
	}

	@Override
	public Segnalazione[] getOpenSegnalazioniAdmin() throws DAOException {
		String query = "SELECT s.* FROM segnalazioni AS s INNER JOIN segnalazioni_risposte AS sr ON (s.id = sr.id_segnalazione) ORDER BY datetime DESC;";
		Object[] parametriQuery = new Object[]{};
		Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Segnalazione s : segnalazioni) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return segnalazioni;
	}

	@Override
	public Segnalazione getSegnalazioneAdmin(int idSegnalazione) throws DAOException {
		String query = "SELECT * FROM segnalazioni WHERE id=?";
		Object[] parametriQuery = new Object[]{idSegnalazione};
		Segnalazione s = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		if (s != null) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return s;
	}

/////////////////////////////////////////////// USER /////////////////////////////////////////////////////////////////////////////////
	@Override
	public Segnalazione[] getSegnalazioniByIdUser(int idUser) throws DAOException {
		String query = "SELECT s.*"
				+ "FROM segnalazioni AS s"
				+ "INNER JOIN orders AS o ON (s.id_order=o.id)"
				+ "WHERE o.id_user=?"
				+ "ORDER BY s.datetime DESC;";
		Object[] parametriQuery = new Object[]{idUser};
		Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Segnalazione s : segnalazioni) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return segnalazioni;
	}

	@Override
	public Segnalazione[] getOpenSegnalazioniByIdUser(int idUser) throws DAOException {
		String query = "SELECT s.*"
				+ "FROM segnalazioni AS s"
				+ "INNER JOIN orders AS o ON (s.id_order=o.id)"
				+ "INNER JOIN segnalazioni_risposte AS sr ON (s.id = sr.id_segnalazione)"
				+ "WHERE o.id_user=?"
				+ "ORDER BY s.datetime DESC;";
		Object[] parametriQuery = new Object[]{idUser};
		Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Segnalazione s : segnalazioni) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return segnalazioni;
	}

	@Override
	public Segnalazione getSegnalazioneUser(int idSegnalazione, int idUser) throws DAOException {
		String query = "SELECT s.*"
				+ "FROM segnalazioni AS s"
				+ "INNER JOIN orders AS o ON (s.id_order=o.id)"
				+ "WHERE s.id=? AND o.id_user=?;";
		Object[] parametriQuery = new Object[]{idSegnalazione, idUser};
		Segnalazione s = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		if (s != null) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return s;
	}

/////////////////////////////////////////////// SELLER /////////////////////////////////////////////////////////////////////////////////
	@Override
	public Segnalazione[] getSegnalazioniByIdSeller(int idSeller) throws DAOException {
		String query = "SELECT s.*"
				+ "FROM segnalazioni AS s"
				+ "INNER JOIN orders AS o ON (s.id_order=o.id)"
				+ "INNER JOIN shops ON (o.id_shop = shops.id)"
				+ "WHERE shops.id_owner=?"
				+ "ORDER BY s.datetime DESC;";
		Object[] parametriQuery = new Object[]{idSeller};
		Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Segnalazione s : segnalazioni) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return segnalazioni;
	}

	@Override
	public Segnalazione[] getOpenSegnalazioniByIdSeller(int idSeller) throws DAOException {
		String query = "SELECT s.*"
				+ "FROM segnalazioni AS s"
				+ "INNER JOIN orders AS o ON (s.id_order=o.id)"
				+ "INNER JOIN shops ON (o.id_shop = shops.id)"
				+ "INNER JOIN segnalazioni_risposte AS sr ON (s.id = sr.id_segnalazione)"
				+ "WHERE shops.id_owner=?"
				+ "ORDER BY s.datetime DESC;";
		Object[] parametriQuery = new Object[]{idSeller};
		Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		for (Segnalazione s : segnalazioni) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return segnalazioni;
	}

	@Override
	public Segnalazione getSegnalazioneSeller(int idSegnalazione, int idSeller) throws DAOException {
		String query = "SELECT s.*"
				+ "FROM segnalazioni AS s"
				+ "INNER JOIN orders AS o ON (s.id_order=o.id)"
				+ "INNER JOIN shops ON (o.id_shop = shops.id)"
				+ "WHERE s.id=? AND shops.id_owner=?";
		Object[] parametriQuery = new Object[]{idSegnalazione, idSeller};
		Segnalazione s = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		if (s != null) {
			s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
		}
		return s;
	}

}
