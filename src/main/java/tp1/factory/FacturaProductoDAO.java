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

public class FacturaProductoDAO implements DAOI {
    public FacturaProductoDAO(Connection con) throws SQLException {
        this.createTable(con);
    }


    @Override
    public void insert(Connection con, CSVParser parser) throws IOException, SQLException {
        String insertQuery = "INSERT INTO factura_producto (id_factura, id_producto, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            for (CSVRecord row : parser) {

                int id_factura = Integer.parseInt(row.get("idFactura"));
                int id_producto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));

                preparedStatement.setInt(1, id_factura);
                preparedStatement.setInt(2, id_producto);
                preparedStatement.setInt(3, cantidad);

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
        String createTableFacturaProducto = "CREATE TABLE IF NOT EXISTS factura_producto (" +
                "id_factura INT," +
                "id_producto INT," +
                "cantidad INT," +
                "FOREIGN KEY (id_factura) REFERENCES factura(id_factura)," +
                "FOREIGN KEY (id_producto) REFERENCES producto(id_producto)" +
                ")";

        con.prepareStatement(createTableFacturaProducto).execute();
        con.commit();


    }
}

