package tp1.interfaces;

import tp1.entidades.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface DAOCliente extends DAOI {

    List<Cliente> getClientesOrdenadosPorFacturacion() throws SQLException;
}
