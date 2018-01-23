package it.unitn.disi.dao.jdbc;

import it.unitn.disi.dao.OrderProductDAO;
import it.unitn.disi.dao.ProductDAO;
import it.unitn.disi.dao.exceptions.DAOException;
import it.unitn.disi.dao.factories.DAOFactory;
import it.unitn.disi.entities.orders.OrderProduct;
import java.sql.Connection;
import javax.servlet.ServletException;

public class JDBCOrderProductDAO extends JDBCDAO<OrderProduct, Integer> implements OrderProductDAO {

    private static final Class classe = OrderProduct.class;
    private static final String[] nomiColonne = new String[]{"id_order", "id_product", "price", "quantity"};
    private static final Class[] constructorParameterTypes = new Class[]{int.class, int.class, float.class, int.class};

    private ProductDAO productDAO;

    public JDBCOrderProductDAO(Connection con) {
        super(con);
    }

    @Override
    public void initFriendsDAO(DAOFactory daoFactory) throws ServletException {
        productDAO = (ProductDAO) initDao(ProductDAO.class, daoFactory);
    }

    private void setPointers(OrderProduct op, boolean loadProduct) throws DAOException {
        if (op != null) {
            if (loadProduct) {
                op.setProduct(productDAO.getProduct(op.getIdProduct(), false, true, false));
            }
        }
    }

    @Override
    public OrderProduct[] getOrderProductsByIdOrder(int idOrder) throws DAOException {
        String query = "SELECT * FROM orders_products WHERE id_order=?";
        Object[] parametriQuery = new Object[]{idOrder};
        OrderProduct[] orderProducts = DAOFunctions.getMany(query, parametriQuery, classe, nomiColonne, constructorParameterTypes, CON);
        for (OrderProduct op : orderProducts) {
            setPointers(op, true);
        }
        return orderProducts;
    }

}
