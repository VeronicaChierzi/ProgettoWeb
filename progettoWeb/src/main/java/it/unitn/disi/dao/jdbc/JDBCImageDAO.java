package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.ImageDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.entities.Image;
import java.sql.Connection;

public class JDBCImageDAO extends JDBCDAO<Image, Integer> implements ImageDAO {

	private static final Class classe = Image.class;
	private static final String[] nomiColonne = new String[]{"id", "id_product", "path", "alt"};
	private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, String.class, String.class};

	public JDBCImageDAO(Connection con) {
		super(con);
	}
	
	@Override
	public Image getProductImage(int idProduct) throws DAOException {
		String query = "SELECT * FROM images WHERE id_product = ? LIMIT 1;";
		Object[] parametriQuery = new Object[]{idProduct};
		Image image = DAOFunctions.getOne(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
		image.setPath(image.getPath().substring(0, 12) + "/img/" + image.getPath().substring(13).toLowerCase().replace(" ", "_"));
		return image;
	}

}
