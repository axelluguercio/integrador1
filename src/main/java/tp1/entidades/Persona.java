package tp1.entidades;

import tp1.ignore.Direccion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Persona {

    @Id
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(name = "anios")
    private int edad;
    @ManyToOne
    private Direccion domicilio;

    public Persona(){super();}

    public Persona(int id, String nombre, int edad, Direccion domicilio ) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Direccion getDomicilio() {
        return domicilio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDomicilio(Direccion domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", domicilio='" + domicilio + '\'' +
                '}';
    }
}