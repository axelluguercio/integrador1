package tp1.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAO extends DAOFactory {

    private Connection connection;
    private static MySqlDAO instance;
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URI = "jdbc:mysql://localhost:3306/integrador1";

    private static String USER = "root";
    private static String PASS = "password";


    public MySqlDAO() {}

    public static MySqlDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new MySqlDAO();
        }
        return instance;
    }

    public Connection connect() throws SQLException {

        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | NoClassDefFoundError |
                 NoSuchMethodException | SecurityException | ClassNotFoundException |
                 InvocationTargetException exception ) {
            exception.printStackTrace();
            System.exit(1);
        }

        try {
            this.connection = DriverManager.getConnection(URI, USER, PASS);
            connection.setAutoCommit(false);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if (this.connection != null) {
            return connection;
        } else {
            throw new SQLException("No se creo la conexion");
        }
    }

    public void close() throws SQLException {
        this.connection.close();
    }

    @Override
    public ClienteDAO getClienteDAO(Connection con) throws SQLException {
        return new ClienteDAO(con);
    }

    @Override
    public ProductoDAO getProductoDAO(Connection con) throws SQLException {
        return new ProductoDAO(con);
    }

    @Override
    public FacturaDAO getFacturaDAO(Connection con) throws SQLException {
        return new FacturaDAO(con);
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO(Connection con) throws SQLException {
        return new FacturaProductoDAO(con);
    }
}
