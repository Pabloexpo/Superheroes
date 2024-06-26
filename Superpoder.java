/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_exposito_martinez_pablo;

/**
 *
 * @author david
 */
public class Superpoder {

    private int id;
    private String nombre;
    private String descripcion;
    private int nivel;

    public Superpoder() {
    }

    
    // Constructor
    public Superpoder(int id, String nombre, String descripcion, int nivel) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    // Método toString para imprimir los detalles del superpoder
    @Override
    public String toString() {
        return "Superpoder{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", descripcion='" + descripcion + '\''
                + ", nivel=" + nivel
                + '}';
    }
}
