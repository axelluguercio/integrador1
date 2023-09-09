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

public class ClienteDAO implements DAOI {
    public ClienteDAO(Connection con) throws SQLException {
        this.createTable(con);
    }

    @Override
    public void insert(Connection con, CSVParser parser) throws IOException, SQLException {

        String insertQuery = "INSERT INTO cliente (id_cliente, nombre, email) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            for (CSVRecord row : parser) {

                int id_cliente = Integer.parseInt(row.get("idCliente"));
                String nombre = row.get("nombre");
                String email = row.get("email");

                preparedStatement.setInt(1, id_cliente);
                preparedStatement.setString(2, nombre);
                preparedStatement.setString(3, email);

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

        String createTableCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                "id_cliente INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "email VARCHAR(255)" +
                ")";
        con.prepareStatement(createTableCliente).execute();
        con.commit();
    }

}

