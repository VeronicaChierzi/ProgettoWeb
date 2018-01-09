package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.DAO;
import it.unitn.disi.dao.exceptions.DAOFactoryException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.utils.MyUtils;
import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.ServletException;

public abstract class JDBCDAO<ENTITY_CLASS, PRIMARY_KEY_CLASS> implements DAO<ENTITY_CLASS, PRIMARY_KEY_CLASS> {

	/**
	 * The JDBC {@link Connection} used to access the persistence system.
	 */
	protected final Connection CON;
	/**
	 * The list of other DAOs this DAO can interact with.
	 */
	protected final HashMap<Class, DAO> FRIEND_DAOS;

	/**
	 * The base constructor for all the JDBC DAOs.
	 *
	 * @param con the internal {@code Connection}.
	 *
	 * @author Stefano Chirico
	 * @since 1.0.170417
	 */
	protected JDBCDAO(Connection con) {
		super();
		this.CON = con;
		FRIEND_DAOS = new HashMap<>();
	}

	/**
	 * If this DAO can interact with it, then returns the DAO of class passed as
	 * parameter.
	 *
	 * @param <DAO_CLASS> the class name of the DAO that can interact with this
	 * DAO.
	 * @param daoClass the class of the DAO that can interact with this DAO.
	 * @return the instance of the DAO or null if no DAO of the type passed as
	 * parameter can interact with this DAO.
	 * @throws DAOFactoryException if an error occurred.
	 *
	 * @author Stefano Chirico
	 * @since 1.0.170417
	 */
	@Override
	public <DAO_CLASS extends DAO> DAO_CLASS getDAO(Class<DAO_CLASS> daoClass) throws DAOFactoryException {
		return (DAO_CLASS) FRIEND_DAOS.get(daoClass);
	}

	@Override
	public <DAO_CLASS extends DAO> DAO_CLASS setDAO(Class<DAO_CLASS> daoClass, DAO dao) throws DAOFactoryException {
		return (DAO_CLASS) FRIEND_DAOS.put(daoClass, dao);
	}

	protected DAO initDao(Class daoClass, DAOFactory daoFactory) throws ServletException {
		if (daoFactory == null) {
			throw new ServletException("Impossible to get dao factory");
		}
		try {
			DAO dao = getDAO(daoClass);
			if (dao == null) {
				dao = daoFactory.getDAO(daoClass);
				setDAO(daoClass, dao);
				if (dao != null) {
					dao.initFriendsDAO(daoFactory);
					if (MyUtils.debugDaoInit) {
						System.err.println("DAO ottenuto da un altro jdbcdao: " + dao.toString());
					}
				} else {
					System.err.println("ERRORE: Impossibile ottenere il dao");
				}
			}
			return dao;
		} catch (DAOFactoryException ex) {
			throw new ServletException("Impossible to get dao", ex);
		}
	}

	@Override
	public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
	}

}
