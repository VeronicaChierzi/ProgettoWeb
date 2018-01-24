package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.OrderDAO;
import it.unitn.disi.dao.SegnalazioneDAO;
import it.unitn.disi.dao.SegnalazioneRispostaDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.Segnalazione;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;

public class JDBCSegnalazioneDAO extends JDBCDAO<Segnalazione, Integer> implements SegnalazioneDAO {

    private static final Class classe = Segnalazione.class;
    private static final String[] nomiColonne = new String[]{"id", "id_order", "title", "description", "datetime"};
    private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, String.class, String.class, Timestamp.class};

    private SegnalazioneRispostaDAO segnalazioneRispostaDAO;
    private OrderDAO orderDAO;

    public JDBCSegnalazioneDAO(Connection con) {
        super(con);
    }

    @Override
    public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
        segnalazioneRispostaDAO = (SegnalazioneRispostaDAO) initDao(SegnalazioneRispostaDAO.class, daoFactory);
        orderDAO = (OrderDAO) initDao(OrderDAO.class, daoFactory);
    }

    private void setPointers(Segnalazione s, boolean loadSegnalazioneRisposta, boolean loadOrder) throws DAOException {
        if (s != null) {
            if (loadSegnalazioneRisposta) {
                s.setSegnalazioneRisposta(segnalazioneRispostaDAO.getSegnalazioneRisposta(s.getId()));
            }
            if (loadOrder) {
                s.setOrder(orderDAO.getOrder(s.getIdOrder()));
            }
        }
    }

/////////////////////////////////////////////// ADMIN /////////////////////////////////////////////////////////////////////////////////
    @Override
    public Segnalazione[] getSegnalazioniAdmin() throws DAOException {
        String query = "SELECT * FROM segnalazioni ORDER BY datetime DESC";
        Object[] parametriQuery = new Object[]{};
        Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        for (Segnalazione s : segnalazioni) {
            setPointers(s, true, true);
        }
        return segnalazioni;
    }

    @Override
    public Segnalazione[] getOpenSegnalazioniAdmin() throws DAOException {
        String query = "SELECT s.* FROM segnalazioni AS s INNER JOIN segnalazioni_risposte AS sr ON (s.id = sr.id_segnalazione) ORDER BY datetime DESC;";
        Object[] parametriQuery = new Object[]{};
        Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        for (Segnalazione s : segnalazioni) {
            setPointers(s, true, true);
        }
        return segnalazioni;
    }

    @Override
    public Segnalazione getSegnalazioneAdmin(int idSegnalazione) throws DAOException {
        String query = "SELECT * FROM segnalazioni WHERE id=?";
        Object[] parametriQuery = new Object[]{idSegnalazione};
        Segnalazione s = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        if (s != null) {
            setPointers(s, true, true);
        }
        return s;
    }

/////////////////////////////////////////////// USER /////////////////////////////////////////////////////////////////////////////////
    @Override
    public Segnalazione[] getSegnalazioniByIdUser(int idUser) throws DAOException {
        String query = "SELECT s.* FROM segnalazioni AS s JOIN orders AS o ON (s.id_order=o.id) WHERE o.id_user=? ORDER BY s.datetime DESC;";
        Object[] parametriQuery = new Object[]{idUser};
        Segnalazione[] segnalazioni = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        for (Segnalazione s : segnalazioni) {
            setPointers(s, true, true);
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
            setPointers(s, true, true);
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
            setPointers(s, true, true);
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
            setPointers(s, true, true);
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
            setPointers(s, true, true);
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
            setPointers(s, true, true);
        }
        return s;
    }

    @Override
    public boolean addSegnalazioneUser(int idOrder, String title, String descr) throws DAOException {
        if (title == null || descr == null) {
            throw new DAOException("Title e descr sono campi obbligatori", new NullPointerException("Title e descr sono null"));
        }
        String query = "INSERT INTO public.segnalazioni(id_order, title, description, datetime) VALUES(?, ?, ?, ?);";
        try (PreparedStatement ps = CON.prepareStatement(query)) {
            ps.setInt(1, idOrder);
            ps.setString(2, title);
            ps.setString(3, descr);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
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
