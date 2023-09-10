package tp1.dao;

import tp1.factory.*;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory  {

    Connection connection;

    public abstract Connection connect() throws Exception;

    public abstract void close() throws SQLException;

    public abstract ClienteDAO getClienteDAO(Connection con) throws SQLException;

    public abstract ProductoDAO getProductoDAO(Connection con) throws SQLException;

    public abstract FacturaDAO getFacturaDAO(Connection con) throws SQLException;

    public abstract FacturaProductoDAO getFacturaProductoDAO(Connection con) throws SQLException;

    public static DAOFactory getDAOFactory (String factoryType) throws Exception {
        switch (factoryType) {
            case "MYSQL_DAO":
                return MySqlDAO.getInstance();
            default:
                throw new IllegalArgumentException("Tipo de fábrica no válido: " + factoryType);
        }
    }


}
