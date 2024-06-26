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
public class AccesoDatosMemoriaSuperpoderes extends AccesoDatosAbstractSuperpoderes {
    
    /*
    Ocurre lo mismo que con acceso en memoria de Personajes, utilizamos 
    la colección ya creada en la clase abstracta
    */

    @Override
    public List<Superpoder> getAll() {
        return super.superpoderes;
    }

    @Override
    public Superpoder getById(int id) {
        for (Superpoder superpoder : super.superpoderes){
            if (superpoder.getId()==id){
                return superpoder;
            }
        }
        return null; 
    }

    @Override
    public void insert(Superpoder superpoder) {
        super.superpoderes.add(superpoder);
    }

    @Override
    public void update(Superpoder superpoder) {
        Superpoder oldSuperpoder = getById(superpoder.getId()); 
        oldSuperpoder.setDescripcion(superpoder.getDescripcion());
        oldSuperpoder.setNivel(superpoder.getNivel());
        oldSuperpoder.setNombre(superpoder.getNombre());
    }

    @Override
    public void delete(Superpoder superpoder) {
        super.superpoderes.remove(superpoder); 
    }

    @Override
    public void deleteAll() {
        super.superpoderes.removeAll(superpoderes);
    }

}
