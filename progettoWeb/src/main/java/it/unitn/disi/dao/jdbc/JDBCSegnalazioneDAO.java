package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.SegnalazioneDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Segnalazione;
import it.unitn.disi.entities.SegnalazioneRisposta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class JDBCSegnalazioneDAO extends JDBCDAO<Segnalazione, Integer> implements SegnalazioneDAO {

	public JDBCSegnalazioneDAO(Connection con) {
		super(con);
	}

	@Override
	public Segnalazione[] getAllSegnalazioni() throws DAOException {
		Segnalazione[] segnalazioni = getSimpleAllSegnalazioni();
		for (Segnalazione s : segnalazioni) {
			SegnalazioneRisposta sr = getSegnalazioneRisposta(s.getId());
			s.setSegnalazioneRisposta(sr);
		}
		return segnalazioni;
	}

	private Segnalazione[] getSimpleAllSegnalazioni() throws DAOException {
		String query = "SELECT * FROM segnalazioni ORDER BY datetime DESC";
		Object[] parametriQuery = new Object[]{};
		Class classe = Segnalazione.class;
		String[] nomiColonne = new String[]{"id", "id_order", "title", "description", "datetime"};
		Class[] constructorParameterTypes = new Class[]{int.class, int.class, String.class, String.class, Timestamp.class};
		Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return segnalazioni;
	}

	@Override
	public Segnalazione[] getOpenSegnalazioni() throws DAOException {
		Segnalazione[] segnalazioni = getSimpleOpenSegnalazioni();
		for (Segnalazione s : segnalazioni) {
			SegnalazioneRisposta sr = getSegnalazioneRisposta(s.getId());
			s.setSegnalazioneRisposta(sr);
		}
		return segnalazioni;
	}

	private Segnalazione[] getSimpleOpenSegnalazioni() throws DAOException {
		String query = "SELECT s.* FROM segnalazioni AS s INNER JOIN segnalazioni_risposte AS sr ON (s.id = sr.id_segnalazione) ORDER BY datetime DESC;";
		Object[] parametriQuery = new Object[]{};
		Class classe = Segnalazione.class;
		String[] nomiColonne = new String[]{"id", "id_order", "title", "description", "datetime"};
		Class[] constructorParameterTypes = new Class[]{int.class, int.class, String.class, String.class, Timestamp.class};

		Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return segnalazioni;
	}

	@Override
	public Segnalazione getSegnalazione(int idSegnalazione) throws DAOException {
		Segnalazione s = getSimpleSegnalazione(idSegnalazione);
		s.setSegnalazioneRisposta(getSegnalazioneRisposta(s.getId()));
		return s;
	}

	private Segnalazione getSimpleSegnalazione(int idSegnalazione) throws DAOException {
		String query = "SELECT * FROM segnalazioni WHERE id=?";
		Object[] parametriQuery = new Object[]{idSegnalazione};
		Class classe = Segnalazione.class;
		String[] nomiColonne = new String[]{"id", "id_order", "title", "description", "datetime"};
		Class[] constructorParameterTypes = new Class[]{int.class, int.class, String.class, String.class, Timestamp.class};

		Segnalazione s = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return s;
	}

	private SegnalazioneRisposta getSegnalazioneRisposta(int idSegnalazione) throws DAOException {
		String query = "SELECT * FROM segnalazioni_risposte WHERE id_segnalazione=?";
		Object[] parametriQuery = new Object[]{idSegnalazione};
		Class classe = SegnalazioneRisposta.class;
		String[] nomiColonne = new String[]{"id_segnalazione", "id_user_admin", "message", "descrizione", "restituire_soldi", "valutazione_negativa_venditore"};
		Class[] constructorParameterTypes = new Class[]{int.class, int.class, String.class, String.class, boolean.class, boolean.class};

		SegnalazioneRisposta sr = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return sr;
	}

	// <editor-fold defaultstate="collapsed" desc="old">
	public Segnalazione[] getAllSegnalazioniOld() throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM segnalazioni ORDER BY datetime DESC")) {
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Segnalazione> segnalazioni_temp = new ArrayList<>();
				while (rs.next()) {
					Segnalazione s = new Segnalazione(
							rs.getInt("id"),
							rs.getInt("id_order"),
							rs.getString("title"),
							rs.getString("description"),
							rs.getTimestamp("datetime")
					);
					segnalazioni_temp.add(s);
					SegnalazioneRisposta sr = getSegnalazioneRisposta(s.getId());
					s.setSegnalazioneRisposta(sr);
				}
				Segnalazione[] segnalazioni = new Segnalazione[segnalazioni_temp.size()];
				segnalazioni = segnalazioni_temp.toArray(segnalazioni); //trasforma arrayList in un array statico
				return segnalazioni;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getAllSegnalazioni: " + ex.getMessage(), ex);
		}
	}

	public Segnalazione[] getOpenSegnalazioniOld() throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT s.* FROM segnalazioni AS s INNER JOIN segnalazioni_risposte AS sr ON (s.id = sr.id_segnalazione) ORDER BY datetime DESC;")) {
			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Segnalazione> segnalazioni_temp = new ArrayList<>();
				while (rs.next()) {
					Segnalazione s = new Segnalazione(
							rs.getInt("id"),
							rs.getInt("id_order"),
							rs.getString("title"),
							rs.getString("description"),
							rs.getTimestamp("datetime")
					);
					segnalazioni_temp.add(s);
					SegnalazioneRisposta sr = getSegnalazioneRisposta(s.getId());
					s.setSegnalazioneRisposta(sr);
				}
				Segnalazione[] segnalazioni = new Segnalazione[segnalazioni_temp.size()];
				segnalazioni = segnalazioni_temp.toArray(segnalazioni); //trasforma arrayList in un array statico
				return segnalazioni;
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException query getOpenSegnalazioni: " + ex.getMessage(), ex);
		}
	}

	public Segnalazione getSegnalazioneOld(int idSegnalazione) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM segnalazioni WHERE id=?")) {
			ps.setInt(1, idSegnalazione);
			try (ResultSet rs = ps.executeQuery()) {
				Segnalazione s = null;
				if (rs.next()) {
					s = new Segnalazione(
							rs.getInt("id"),
							rs.getInt("id_order"),
							rs.getString("title"),
							rs.getString("description"),
							rs.getTimestamp("datetime")
					);
				}
				if (rs.next()) {
					throw new DAOException("Errore: ci sono più segnalazioni con lo stesso id");
				}
				return s;
			} catch (SQLException ex) {
				throw new DAOException("Errore SQLException executeQuery getSegnalazione: " + ex.getMessage(), ex);
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException preparedStatment getSegnalazione: " + ex.getMessage(), ex);
		}
	}

	private SegnalazioneRisposta getSegnalazioneRispostaOld(int idSegnalazione) throws DAOException {
		try (PreparedStatement ps = CON.prepareStatement("SELECT * FROM segnalazioni_risposte WHERE id_segnalazione=?")) {
			ps.setInt(1, idSegnalazione);
			try (ResultSet rs = ps.executeQuery()) {
				SegnalazioneRisposta sr = null;
				if (rs.next()) {
					sr = new SegnalazioneRisposta(
							rs.getInt("id_segnalazione"),
							rs.getInt("id_user_admin"),
							rs.getString("message"),
							rs.getString("descrizione"),
							rs.getBoolean("restituire_soldi"),
							rs.getBoolean("valutazione_negativa_venditore")
					);
				}
				if (rs.next()) {
					throw new DAOException("Errore: ci sono più SegnalazioneRisposte collegate alla stessa Segnalazione");
				}
				return sr;
			} catch (SQLException ex) {
				throw new DAOException("Errore SQLException executeQuery getSegnalazioneRisposta: " + ex.getMessage(), ex);
			}
		} catch (SQLException ex) {
			throw new DAOException("Errore SQLException preparedStatment getSegnalazioneRisposta: " + ex.getMessage(), ex);
		}
	}
	// </editor-fold>

}
