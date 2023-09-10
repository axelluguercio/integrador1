package tp1.factory;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tp1.entidades.Producto;
import tp1.interfaces.DAOI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO implements DAOI {
    private Connection connection;

    public ProductoDAO(Connection con) throws SQLException {
        this.setConnection(con);
        this.createTable();
    }

    @Override
    public void insert(CSVParser parser) throws SQLException {
        String insertQuery = "INSERT INTO producto (id_producto, nombre, valor) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery)) {
            for (CSVRecord row : parser) {

                int id_producto = Integer.parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                int valor = Integer.parseInt(row.get("valor"));

                preparedStatement.setInt(1, id_producto);
                preparedStatement.setString(2, nombre);
                preparedStatement.setInt(3, valor);

                preparedStatement.executeUpdate();

                this.connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    @Override
    public void createTable() throws SQLException {
        String createTableProducto = "CREATE TABLE IF NOT EXISTS producto (" +
                "id_producto INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "valor DECIMAL(10, 2)" +
                ")";
        try {
            this.connection.prepareStatement(createTableProducto).execute();
            this.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public Producto getProductoMasRecaudado() throws SQLException {
        Producto productoMasRecaudado = null;

        String getMAsVendidoSQL = "SELECT prod.id_producto, prod.nombre, SUM(ventas.cantidad * prod.valor) AS recaudacion_total " +
        "FROM factura_producto as ventas JOIN producto as prod ON ventas.id_producto = prod.id_producto " +
        "GROUP BY ventas.id_producto ORDER BY recaudacion_total DESC LIMIT 1;";

        try (PreparedStatement statement = this.connection.prepareStatement(getMAsVendidoSQL);
             ResultSet resultado = statement.executeQuery()) {

            if (resultado.next()) {
                int idProducto = resultado.getInt("id_producto");
                String nombre = resultado.getString("nombre");
                double recaudacionTotal = resultado.getDouble("recaudacion_total");

                productoMasRecaudado = new Producto(idProducto, nombre, recaudacionTotal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }

        return productoMasRecaudado;
    }

    @Override
    public void setConnection(Connection con) throws SQLException {
        if (con != null) {
            this.connection = con;
        } else {
            throw new SQLException("La conexion dada no es valida");
        }

    }
}
