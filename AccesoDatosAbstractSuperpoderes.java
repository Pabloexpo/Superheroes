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
public abstract class AccesoDatosAbstractSuperpoderes {

    private static AccesoDatosAbstractSuperpoderes conexion;

    protected List<Superpoder> superpoderes = new ArrayList<Superpoder>();

    public abstract List<Superpoder> getAll();

    public abstract Superpoder getById(int id);

    public abstract void insert(Superpoder superpoder);

    public abstract void update(Superpoder superpoder);

    public abstract void delete(Superpoder superpoder);

    public abstract void deleteAll();

    public static AccesoDatosAbstractSuperpoderes getInstance() {
        if (AppConfig.modoAccesoDatos.equalsIgnoreCase("MEMORIA")) {
            conexion = new AccesoDatosMemoriaSuperpoderes();
        }
        if (AppConfig.modoAccesoDatos.equalsIgnoreCase("BBDD")) {
            conexion = new AccesoDatosBBDDSuperpoderes();

        }
        return conexion;
    }
}
