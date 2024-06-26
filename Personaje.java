/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package examen_exposito_martinez_pablo;

/**
 *
 * @author david
 */

import java.util.Date;

public class Personaje {
    private int id;
    private int superpoderId;
    private String nombre;
    private String tipo;
    private String ciudad;
    private Date fechaIngreso;

    public Personaje() {
    }
    
    

    public Personaje(int id, int superpoderId, String nombre, String tipo, String ciudad, Date fechaIngreso) {
        this.id = id;
        this.superpoderId = superpoderId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.fechaIngreso = fechaIngreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuperpoderId() {
        return superpoderId;
    }

    public void setSuperpoderId(int superpoderId) {
        this.superpoderId = superpoderId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "Personaje{" + "id=" + id + ", superpoderId=" + superpoderId + ", nombre=" + nombre + ", tipo=" + tipo + ", ciudad=" + ciudad + ", fechaIngreso=" + fechaIngreso + '}';
    }
}

    

