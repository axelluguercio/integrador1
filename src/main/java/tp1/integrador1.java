package tp1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import tp1.dao.DAOFactory;
import tp1.entidades.Cliente;
import tp1.factory.*;
import tp1.interfaces.DAOCliente;
import tp1.interfaces.DAOI;
import tp1.interfaces.DAOProducto;

import java.io.FileReader;
import java.sql.Connection;
import java.util.List;

public class integrador1 {

    public static void main(String[] args) throws Exception {

        // instancia nuevo dao debendiendo del mysql factory
        DAOFactory db_dao = DAOFactory.getDAOFactory("MYSQL_DAO");
        // retorna conexion
        Connection connection = db_dao.connect();

        // DAO de objetos
        DAOCliente clientedao = db_dao.getClienteDAO(connection);
        DAOProducto productodao = db_dao.getProductoDAO(connection);
        DAOI facturadao = db_dao.getFacturaDAO(connection);
        DAOI factura_producto_dao = db_dao.getFacturaProductoDAO(connection);

        // CSV parsers
        CSVParser CSV_clientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/tp1/CSV/clientes.csv"));
        CSVParser CSV_productos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/tp1/CSV/productos.csv"));
        CSVParser CSV_factura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/tp1/CSV/facturas.csv"));
        CSVParser CSV_factura_producto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/tp1/CSV/facturas-productos.csv"));

        // carga de datos por csv (1 sola vez)
        //clientedao.insert(CSV_clientes);
        //productodao.insert(CSV_productos );
        //facturadao.insert(CSV_factura);
        //factura_producto_dao.insert(CSV_factura_producto);

        // sigue...

        // Producto mas vendido
        System.out.println(productodao.getProductoMasRecaudado());

        // Lista ordenada de clientes por facturacion (Imprime pantalla)
        List<Cliente> lista_clientes = clientedao.getClientesOrdenadosPorFacturacion();

        // cierra conexion
        db_dao.close();

    }
}
