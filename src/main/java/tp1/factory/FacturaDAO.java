package tp1.factory;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tp1.interfaces.DAOI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaDAO implements DAOI {
    private Connection connection;

    public FacturaDAO(Connection con) throws SQLException {
        this.setConnection(con);
        this.createTable();
    }

    @Override
    public void insert(CSVParser parser) throws SQLException {
        String insertQuery = "INSERT INTO factura (id_factura, id_cliente) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery)) {
            for (CSVRecord row : parser) {

                int id_factura = Integer.parseInt(row.get("idFactura"));
                int id_cliente = Integer.parseInt(row.get("idCliente"));

                preparedStatement.setInt(1, id_factura);
                preparedStatement.setInt(2, id_cliente);

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
        String createTableFactura = "CREATE TABLE IF NOT EXISTS factura (" +
                "id_factura INT AUTO_INCREMENT PRIMARY KEY," +
                "id_cliente INT," +
                "FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)" +
                ")";

        this.connection.prepareStatement(createTableFactura).execute();
        this.connection.commit();
    }

}

