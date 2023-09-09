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

public class ProductoDAO implements DAOI {
    public ProductoDAO(Connection con) throws SQLException {
        this.createTable(con);
    }


    @Override
    public void insert(Connection con, CSVParser parser) throws IOException, SQLException {
        String insertQuery = "INSERT INTO producto (id_producto, nombre, valor) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            for (CSVRecord row : parser) {

                int id_producto = Integer.parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                int valor = Integer.parseInt(row.get("valor"));

                preparedStatement.setInt(1, id_producto);
                preparedStatement.setString(2, nombre);
                preparedStatement.setInt(3, valor);

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
        String createTableProducto = "CREATE TABLE IF NOT EXISTS producto (" +
                "id_producto INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "valor DECIMAL(10, 2)" +
                ")";

        con.prepareStatement(createTableProducto).execute();
        con.commit();

    }
}
