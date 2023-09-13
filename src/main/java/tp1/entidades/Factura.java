package tp1.entidades;

import tp1.entidades.Cliente;

import javax.persistence.*;

public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private int id;

    // Relación Many-to-One con Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente") // Columna de clave foránea en la tabla factura
    private Cliente cliente;

    public Factura(){
        super();
    }

    public Factura(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", cliente=" + cliente +
                '}';
    }
}
