package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.UserAdminDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.UserAdmin;
import java.sql.Connection;
import javax.servlet.ServletException;

public class JDBCUserAdminDAO extends JDBCDAO<UserAdmin, Integer> implements UserAdminDAO {

	private static final Class classe = UserAdmin.class;
	private static final String[] nomiColonne = new String[]{"id"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class};

	public JDBCUserAdminDAO(Connection con) throws ServletException {
		super(con);
	}

	@Override
	public UserAdmin getUserAdmin(int id) throws DAOException {
		String query = "SELECT * FROM users_admins WHERE id = ?";
		Object[] parametriQuery = new Object[]{id};
		UserAdmin ua = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		return ua;
	}

}
