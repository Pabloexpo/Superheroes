/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_exposito_martinez_pablo;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class Main {

    private static AccesoDatosAbstractPersonajes accesoDatosPersonajes = AccesoDatosAbstractPersonajes.getInstance();
    private static AccesoDatosAbstractSuperpoderes accesoDatosSuperpoderes = AccesoDatosAbstractSuperpoderes.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (AppConfig.cargarConfiguraciónFicheroJSON())
        {
            System.out.println("Configuración cargada exitosamente");
        }
        int opcion;

        do {
            System.out.println("\nGESTIÓN DE DATOS DE SUPERHEROES");
            System.out.println("1.Nuevo Personaje.");
            System.out.println("2.Listar Personajes.");
            System.out.println("3.Buscar Personaje.");
            System.out.println("4.Cambiar tipo.");
            System.out.println("5.Eliminar personaje.");
            System.out.println("6.Guardar en BBDD.");
            System.out.println("7.Salir.");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarPersonaje(scanner);
                    break;
                case 2:
                    listarPersonajes();
                    break;
                case 3:
                    buscarPersonaje(scanner);
                    break;
                case 4:
                    cambiarTipo(scanner);
                    break;
                case 5:
                    eliminarPersonaje(scanner);
                    break;
                case 6:
                    guardarEnBBDD();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }

    private static void agregarPersonaje(Scanner scanner) {

        //SuperPoder
        int idSuperpoder = 1;
        String nombreSuperPoder = "Superpoder 1";
        String descripcion = "Descripcion 1";
        int nivel = 1;

        //Personaje
        int id = 1;
        int superpoderId = 1;
        String nombre = "Superheroe 1";
        String tipo = "heroe";
        String ciudad = "ciudad 1";

        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, 2001);
//        calendar.set(Calendar.MONTH, 1 - 1); // Meses en Calendar comienzan desde 0
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
        Date fechaIngreso = calendar.getTime();

        Superpoder superpoder = new Superpoder(idSuperpoder, nombreSuperPoder, descripcion, nivel);
        Personaje personaje = new Personaje(id, superpoderId, nombre, tipo, ciudad, fechaIngreso);

        accesoDatosSuperpoderes.insert(superpoder);
        accesoDatosPersonajes.insert(personaje);

        System.out.println("Personaje creado exitosamente");

    }

    private static void listarPersonajes() {
        List<Personaje> personajes = accesoDatosPersonajes.getAll();
        for (Personaje personaje : personajes) {
            System.out.println(personaje.toString());
            Superpoder superpoder = accesoDatosSuperpoderes.getById(personaje.getSuperpoderId());
            System.out.println(superpoder.toString());
        }
    }

    private static void buscarPersonaje(Scanner scanner) {
        System.out.print("Introduce el Id: ");
        int id = scanner.nextInt();
        Personaje personaje = accesoDatosPersonajes.getById(id);
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        System.out.println(personaje.toString());
        Superpoder superpoder = accesoDatosSuperpoderes.getById(personaje.getSuperpoderId());
        System.out.println(superpoder.toString());
        
    }

    private static void cambiarTipo(Scanner scanner) {
        System.out.print("Introduce el id del personaje: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        Personaje personaje = accesoDatosPersonajes.getById(id);
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        System.out.print("Introduce el tipo (heroe/villano): ");
        String equipo = scanner.nextLine();
        personaje.setTipo(equipo);
        accesoDatosPersonajes.update(personaje);
        System.out.println(personaje.toString());
    }

    private static void eliminarPersonaje(Scanner scanner) {
        System.out.print("Introduce el id: ");
        int idPersonaje = scanner.nextInt();
        Personaje personaje = accesoDatosPersonajes.getById(idPersonaje);
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        accesoDatosPersonajes.delete(personaje);
        System.out.println("Personaje eliminado correctamente");
    }

    private static void cargarDeBBDD() {
        AppConfig.modoAccesoDatos = "BBDD";
        accesoDatosPersonajes = AccesoDatosAbstractPersonajes.getInstance();
        accesoDatosSuperpoderes = AccesoDatosAbstractSuperpoderes.getInstance();
        
        List<Personaje> personajes = accesoDatosPersonajes.getAll();
        List<Superpoder> superpoderes = accesoDatosSuperpoderes.getAll();
        
        AppConfig.modoAccesoDatos = "Memoria";
        accesoDatosPersonajes = AccesoDatosAbstractPersonajes.getInstance();
        accesoDatosSuperpoderes = AccesoDatosAbstractSuperpoderes.getInstance();

        for (Personaje personaje : personajes) {
            accesoDatosPersonajes.insert(personaje);
        }
        for (Superpoder superpoder : superpoderes) {
            accesoDatosSuperpoderes.insert(superpoder);
        }
        System.out.println("Datos cargados correctamente.");
    }

    private static void guardarEnBBDD() {
        List<Personaje> personajes = accesoDatosPersonajes.getAll();
        List<Superpoder> superpoderes = accesoDatosSuperpoderes.getAll();

        AppConfig.modoAccesoDatos = "BBDD";
        accesoDatosPersonajes = AccesoDatosAbstractPersonajes.getInstance();
        accesoDatosSuperpoderes = AccesoDatosAbstractSuperpoderes.getInstance();
        
        accesoDatosPersonajes.deleteAll();
        for (Personaje personaje : personajes) {
            accesoDatosPersonajes.insert(personaje);
        }

        accesoDatosSuperpoderes.deleteAll();
        for (Superpoder superpoder : superpoderes) {
            accesoDatosSuperpoderes.insert(superpoder);
        }
        AppConfig.modoAccesoDatos = "Memoria";
        accesoDatosPersonajes = AccesoDatosAbstractPersonajes.getInstance();
        accesoDatosSuperpoderes = AccesoDatosAbstractSuperpoderes.getInstance();
        
        System.out.println("Datos guardados correctamente.");
    }

}
