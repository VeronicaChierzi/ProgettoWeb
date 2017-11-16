package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.SegnalazioneRispostaDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.SegnalazioneRisposta;
import java.sql.Connection;

public class JDBCSegnalazioneRispostaDAO extends JDBCDAO<SegnalazioneRisposta, Integer> implements SegnalazioneRispostaDAO {

	private static final Class classe = SegnalazioneRisposta.class;
	private static final String[] nomiColonne = new String[]{"id_segnalazione", "id_user_admin", "message", "descrizione", "restituire_soldi", "valutazione_negativa_venditore"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, String.class, String.class, boolean.class, boolean.class};

	public JDBCSegnalazioneRispostaDAO(Connection con) {
		super(con);
	}

	@Override
	public SegnalazioneRisposta getSegnalazioneRisposta(int idSegnalazione) throws DAOException {
		String query = "SELECT * FROM segnalazioni_risposte WHERE id_segnalazione=?";
		Object[] parametriQuery = new Object[]{idSegnalazione};
		SegnalazioneRisposta sr = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return sr;
	}

}
