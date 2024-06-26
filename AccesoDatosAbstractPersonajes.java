/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_exposito_martinez_pablo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public abstract class AccesoDatosAbstractPersonajes {

    private static AccesoDatosAbstractPersonajes conexion;

    protected List<Personaje> personajes = new ArrayList<Personaje>();

    public abstract List<Personaje> getAll();

    public abstract Personaje getById(int id);

    public abstract void insert(Personaje personaje);

    public abstract void update(Personaje personaje);

    public abstract void delete(Personaje personaje);

    public abstract void deleteAll();

    public static AccesoDatosAbstractPersonajes getInstance() {
        if (AppConfig.modoAccesoDatos.equalsIgnoreCase("MEMORIA")) {
            conexion = new AccesoDatosMemoriaPersonajes();
        }
        if (AppConfig.modoAccesoDatos.equalsIgnoreCase("BBDD")) {
            conexion = new AccesoDatosBBDDPersonajes();
        }
        return conexion;
    }
}
