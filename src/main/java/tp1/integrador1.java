package tp1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import tp1.factory.*;

import java.io.FileReader;
import java.sql.Connection;

public class integrador1 {

    public static void main(String[] args) throws Exception {

        // instancia nuevo dao debendiendo del mysql factory
        DAOFactory db_dao = DAOFactory.getDAOFactory("MYSQL_DAO");
        // retorna conexion
        Connection connection = db_dao.connect();

        // DAO de objetos
        ClienteDAO clientedao = db_dao.getClienteDAO(connection);
        ProductoDAO productodao = db_dao.getProductoDAO(connection);
        FacturaDAO facturadao = db_dao.getFacturaDAO(connection);
        FacturaProductoDAO factura_producto_dao = db_dao.getFacturaProductoDAO(connection);

        // CSV parsers
        CSVParser CSV_clientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/tp1/CSV/clientes.csv"));
        CSVParser CSV_productos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/tp1/CSV/productos.csv"));
        CSVParser CSV_factura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/tp1/CSV/facturas.csv"));
        CSVParser CSV_factura_producto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/tp1/CSV/facturas-productos.csv"));

        // carga de datos por csv (1 sola vez)
        //clientedao.insert(mysql);
        //productodao.insert(mysql);
        //facturadao.insert(mysql);
        //factura_producto_dao.insert(mysql);

        // sigue...

    }
}
