package tp1.interfaces;

import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DAOI<T> {

    void setConnection(Connection con) throws SQLException;

    void insert(CSVParser parser) throws SQLException;


    void createTable() throws SQLException;
}
