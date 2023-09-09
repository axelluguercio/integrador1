package tp1.factory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tp1.interfaces.DAOI;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaDAO implements DAOI {
    public FacturaDAO(Connection con) throws SQLException {
        this.createTable(con);
    }

    @Override
    public void insert(Connection con, CSVParser parser) throws IOException {
        String insertQuery = "INSERT INTO factura (id_factura, id_cliente) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            for (CSVRecord row : parser) {

                int id_factura = Integer.parseInt(row.get("idFactura"));
                int id_cliente = Integer.parseInt(row.get("idCliente"));

                preparedStatement.setInt(1, id_factura);
                preparedStatement.setInt(2, id_cliente);

                preparedStatement.executeUpdate();

                con.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void createTable(Connection con) throws SQLException {
        String createTableFactura = "CREATE TABLE IF NOT EXISTS factura (" +
                "id_factura INT AUTO_INCREMENT PRIMARY KEY," +
                "id_cliente INT," +
                "FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)" +
                ")";

        con.prepareStatement(createTableFactura).execute();
        con.commit();
    }

}

