package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.SegnalazioneDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Segnalazione;
import it.unitn.disi.entities.SegnalazioneRisposta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCSegnalazioneDAO extends JDBCDAO<Segnalazione, Integer> implements SegnalazioneDAO {

	public JDBCSegnalazioneDAO(Connection con) {
		super(con);
	}

	@Override
	public Segnalazione[] getAllSegnalazioni() throws DAOException {
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

	@Override
	public Segnalazione[] getOpenSegnalazioni() throws DAOException {
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

	@Override
	public Segnalazione getSegnalazione(int idSegnalazione) throws DAOException {
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

	private SegnalazioneRisposta getSegnalazioneRisposta(int idSegnalazione) throws DAOException {
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
}
