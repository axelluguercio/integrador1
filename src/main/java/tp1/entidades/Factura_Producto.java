package tp1.entidades;

import tp1.ignore.Factura;

import javax.persistence.*;

public class Factura_Producto {
    @ManyToOne
    @JoinColumn(name = "id_factura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "cantidad")
    private int cantidad;

    public Factura_Producto(){super();}

    public Factura_Producto(Factura id_factura, Producto id_producto, int cantidad) {
        this.factura = id_factura;
        this.producto = id_producto;
        this.cantidad = cantidad;
    }

    public Factura getFactura() {
        return factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Factura_Producto{" +
                "factura=" + factura +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
