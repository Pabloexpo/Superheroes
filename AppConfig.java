/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_exposito_martinez_pablo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author david
 */
public class AppConfig {

    private static class TempConfig {

        private String cadenaConexion;
        private String usuario;
        private String password;
        private String modoAccesoDatos = "MEMORIA";
    }

    public static String cadenaConexion;
    public static String usuario;
    public static String password;
    public static String modoAccesoDatos = "MEMORIA";

    public static boolean cargarConfiguraciónFicheroJSON() {
        
        File archivo = new File("C:\\\\JAVA\\\\Examen\\\\appconfig.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        /*
        Bien podríamos haber usado la clase Gson() o GsonBuilder(), el 
        resultado acabará siendo el mismo
        */
        try (FileReader lector = new FileReader(archivo)) {
            /*
            Utilizamos un bloque try-with-resources para evitar tener
            que cerrar el lector al final del método, por ese motivo he 
            creado antes del bloque try el objeto File
            */
            
            TempConfig temp = gson.fromJson(lector, TempConfig.class); 
            //Objeto deserializado
            
            if (temp != null){
                
            cadenaConexion = temp.cadenaConexion; 
            usuario = temp.usuario; 
            password = temp.password;                           
            }
 
        } catch (IOException e){
            e.printStackTrace();
        } catch (JsonIOException e){
            e.printStackTrace();
        } catch (JsonSyntaxException e){
            e.printStackTrace();
        }      
        return false;
    }

}
