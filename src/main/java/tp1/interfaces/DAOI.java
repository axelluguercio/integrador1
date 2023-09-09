package tp1.interfaces;

import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DAOI<T> {

    void insert(Connection con, CSVParser parser) throws IOException, SQLException;


    void createTable(Connection con) throws SQLException;
}
