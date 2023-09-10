package tp1.factory;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tp1.entidades.Cliente;
import tp1.interfaces.DAOI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAOI {

    private Connection connection;

    public ClienteDAO(Connection con) throws SQLException {
        // setea connection a db
        this.setConnection(con);
        this.createTable();
    }

    @Override
    public void insert(CSVParser parser) throws SQLException {

        String insertQuery = "INSERT INTO cliente (id_cliente, nombre, email) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery)) {
            for (CSVRecord row : parser) {

                int id_cliente = Integer.parseInt(row.get("idCliente"));
                String nombre = row.get("nombre");
                String email = row.get("email");

                preparedStatement.setInt(1, id_cliente);
                preparedStatement.setString(2, nombre);
                preparedStatement.setString(3, email);

                preparedStatement.executeUpdate();
                this.connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    @Override
    public void setConnection(Connection con) throws SQLException {
        if (con != null) {
            this.connection = con;
        } else {
            throw new SQLException("La conexion dada no es valida");
        }

    }

    @Override
    public void createTable() throws SQLException {

        String createTableCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                "id_cliente INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "email VARCHAR(255)" +
                ")";
        try {
            this.connection.prepareStatement(createTableCliente).execute();
            this.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public List<Cliente> getClientesOrdenadosPorFacturacion() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String mas_facturadosSQL = "SELECT c.id_cliente, c.nombre, SUM(fp.cantidad * p.valor) AS total_facturado " +
                "FROM cliente c " +
                "LEFT JOIN factura f ON c.id_cliente = f.id_cliente " +
                "LEFT JOIN factura_producto fp ON f.id_factura = fp.id_factura " +
                "LEFT JOIN producto p ON fp.id_producto = p.id_producto " +
                "GROUP BY c.id_cliente, c.nombre " +
                "ORDER BY total_facturado DESC";

        try (PreparedStatement statement = this.connection.prepareStatement(mas_facturadosSQL);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                int idCliente = resultado.getInt("id_cliente");
                String nombreCliente = resultado.getString("nombre");
                double totalFacturado = resultado.getDouble("total_facturado");

                // imprime pantalla con el resultado
                System.out.print(idCliente + ", ");
                System.out.print(nombreCliente+ ", ");
                System.out.print(totalFacturado + ";");
                System.out.println();

                Cliente cliente = new Cliente(idCliente, nombreCliente, "null");
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }

        return clientes;
    }

}

