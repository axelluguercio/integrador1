package tp1.interfaces;

import tp1.entidades.Producto;

import java.sql.SQLException;

public interface DAOProducto extends DAOI {

    Producto getProductoMasRecaudado() throws SQLException;

}
