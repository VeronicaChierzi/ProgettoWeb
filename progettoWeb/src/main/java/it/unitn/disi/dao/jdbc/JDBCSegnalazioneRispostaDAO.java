package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.SegnalazioneRispostaDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.SegnalazioneRisposta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JDBCSegnalazioneRispostaDAO extends JDBCDAO<SegnalazioneRisposta, Integer> implements SegnalazioneRispostaDAO {

    private static final Class classe = SegnalazioneRisposta.class;
    private static final String[] nomiColonne = new String[]{"id_segnalazione", "id_user_admin", "message", "decisione", "restituire_soldi", "valutazione_negativa_venditore"};
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

    @Override
    public boolean addRispostaByAdmin(int idSegnalazione, int idUser, String message, String decisione, boolean restSoldi, boolean valutazioneNegativa) throws DAOException {
        if (message == null || decisione == null) {
            throw new DAOException("Messaggio e decisioni sono campi obbligatori", new NullPointerException("Messaggio e decisione sono null"));
        }
        String query = "INSERT INTO public.segnalazioni_risposte(id_segnalazione, id_user_admin, message, decisione, restituire_soldi, valutazione_negativa_venditore)VALUES(?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = CON.prepareStatement(query)) {
            ps.setInt(1, idSegnalazione);
            ps.setInt(2, idUser);
            ps.setString(3, message);
            ps.setString(4, decisione);
            ps.setBoolean(5, restSoldi);
            ps.setBoolean(6, valutazioneNegativa);
            int result = -1; //quantità di righe modificate dalla query insert
            try {
                result = ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DAOException("Errore esecuzione query: " + ex);
            }
            boolean b = (result > 0); //se ha modificato almeno 1 riga, restituisce true
            return b;
        } catch (SQLException ex) {
            throw new DAOException("Errore preparedStatement o sintassi query: " + ex);
        }
    }

}
