/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_exposito_martinez_pablo;

import java.util.List;

/**
 *
 * @author david
 */
public class AccesoDatosMemoriaPersonajes extends AccesoDatosAbstractPersonajes{
    /*
    Al estar ya creada la lista en la clase Abstracta, podemos usarla en 
    esta clase para aligerar los pasos a la hora de realizar los métodos CRUD, 
    en la mayoría de ellos (obtener todos, borrar e insertar) simplemente 
    tendremos que realizar una de las posibles combinaciones que nos permiten 
    los métodos de las colecciones
    */

    @Override
    public List<Personaje> getAll() {
        return super.personajes;
    }

    @Override
    public Personaje getById(int id) {
        /*
        Utilizamos un bucle que esté destinado a recorrer todos los objetos de 
        la coleccion ya creada, si el getter del id coincide con el id 
        que se le pasa por parámetro al método, se nos retornará ese objeto de 
        la clase Personaje
        */
        for (Personaje personaje : super.personajes){
            if (personaje.getId()==id){
                return personaje; 
            }
        }
        return null; 
    }

    @Override
    public void insert(Personaje personaje) {
        super.personajes.add(personaje); 
    }

    @Override
    public void update(Personaje personaje) {
        
        /*
        Seteamos los valores del antiguo objeto de Personaje con los datos del
        objeto Personaje que se le pasa por parámetro a update para realizar la 
        actualización
        */

        Personaje oldPersonaje = getById(personaje.getId()); 
        oldPersonaje.setCiudad(personaje.getCiudad());
        oldPersonaje.setFechaIngreso(personaje.getFechaIngreso());
        oldPersonaje.setNombre(personaje.getNombre());
        oldPersonaje.setSuperpoderId(personaje.getSuperpoderId());
        oldPersonaje.setTipo(personaje.getTipo());
    }

    @Override
    public void delete(Personaje personaje) {
        super.personajes.remove(personaje); 
    }

    @Override
    public void deleteAll() {
        super.personajes.removeAll(personajes);
    }
    
}
