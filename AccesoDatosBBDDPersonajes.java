/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_exposito_martinez_pablo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class AccesoDatosBBDDPersonajes extends AccesoDatosAbstractPersonajes {
    /*
    Tanto en esta clase, como en AccesoDatosBBDDSuperpoderes, he usado 
    un metodo para parsear la informacion del objeto creado, ya sea de 
    personaje o de superheroe, utilizando la clase ResultSet para poder
    diseccionar la información que nos llega almacenada del objeto en memoria,
    diferenciandola en "etiquetas", para poder asó ingresar la información 
    en la BBDD.  
    */
    
    private Personaje parse (ResultSet rs){
        Personaje personaje = new Personaje(); 
        try {           
            personaje.setId(rs.getInt("Id"));
            personaje.setSuperpoderId(rs.getInt("Superpoder"));
            personaje.setNombre(rs.getString("Nombre"));
            personaje.setTipo(rs.getString("Tipo"));
            personaje.setCiudad(rs.getString("Ciudad"));
            personaje.setFechaIngreso(rs.getDate("fechaIngreso"));
            
        } catch (SQLException e){
            e.printStackTrace();            
        }
        return personaje;
    }


    @Override
    public List<Personaje> getAll() {
        try {
            //Driver necesario para conectar con la BBDD
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*
            Tanto aquí como en la otra clase de BBDD, inserto en 3 variables 
            String la información para realizar la conexión, dichos datos
            son obtenidos de la clase AppConfig, la cual he deserializado 
            con el gson y allí he declarado 3 atributos en los cuales
            he almacenado la información del json.
            A partir de este punto cualquier método CRUD tendrá el mismo
            inicio para realizar la conexión
            */
            String url = AppConfig.cadenaConexion; 
            String pwd = AppConfig.password; 
            String user = AppConfig.usuario; 
            Connection conex = DriverManager.getConnection(url, user, pwd);
            
            String query = "SELECT * FROM PERSONAJES"; 
            
            PreparedStatement  querySelect = conex.prepareStatement(query); 
            
            ResultSet resultado = querySelect.executeQuery(); 
            
            while (resultado.next()){
                Personaje personaje = parse(resultado); 
                super.personajes.add(personaje); 
            }
            /*
            Al igual que se hará en los otros CRUD, cerramos la conexión 
            para aumentar la eficiencia en uso de los recursos
            */
            resultado.close();
            querySelect.close();
            conex.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.personajes; 
    }

    @Override
    public Personaje getById(int id) {
        Personaje personaje = null; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            String url = AppConfig.cadenaConexion; 
            String pwd = AppConfig.password; 
            String user = AppConfig.usuario; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "SELECT * FROM PERSONAJES WHERE id = ?"; 
            
            PreparedStatement querySelect = conex.prepareCall(query); 
            
            querySelect.setInt(1, id);
            
            /*
            De esta manera evitamos una posible inyección SQL
            */
            
            ResultSet resultado = querySelect.executeQuery(); 
            
            while (resultado.next()){
                personaje = parse (resultado); 
            }
            
            resultado.close();
            querySelect.close();
            conex.close();
            
            return personaje; 
            
            
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null; 
    }

    @Override
    public void insert(Personaje personaje) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            String url = AppConfig.cadenaConexion; 
            String pwd = AppConfig.password; 
            String user = AppConfig.usuario; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "INSERT INTO PERSONAJES VALUES (?,?,?,?,?,?)"; 
            
            PreparedStatement queryInsert = conex.prepareCall(query);
            
            
            queryInsert.setInt(1, personaje.getId());
            queryInsert.setInt(2, personaje.getSuperpoderId());
            queryInsert.setString(3, personaje.getNombre());
            queryInsert.setString(4, personaje.getTipo());
            queryInsert.setString(5, personaje.getCiudad());
            queryInsert.setDate(6, new java.sql.Date((personaje.getFechaIngreso()).getTime()));
            
            int resultado = queryInsert.executeUpdate();
            queryInsert.close();
            conex.close();
            
            
            
        }  catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(Personaje personaje) {
        try {
            int identif = personaje.getId(); 
            String objeto = personaje.getTipo(); 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = AppConfig.cadenaConexion; 
            String user = AppConfig.usuario; 
            String pwd = AppConfig.password; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "UPDATE PERSONAJES SET TIPO= ? WHERE ID = ? "; 
            
            PreparedStatement queryUpdate = conex.prepareCall(query); 
            
            queryUpdate.setInt(1, identif);
            queryUpdate.setString(2, objeto);
            
            int resultado = queryUpdate.executeUpdate(); 
            
            queryUpdate.close();
            conex.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Personaje personaje) {
        try {
            int identif = personaje.getId(); 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = AppConfig.cadenaConexion; 
            String user = AppConfig.usuario; 
            String pwd = AppConfig.password; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "DELETE FROM PERSONAJES WHERE ID = ? "; 
            
            PreparedStatement queryDelete = conex.prepareCall(query); 
            
            queryDelete.setInt(1, identif);
            
            int resultado = queryDelete.executeUpdate(); 
            
                        
            queryDelete.close();
            conex.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = AppConfig.cadenaConexion; 
            String user = AppConfig.usuario; 
            String pwd = AppConfig.password; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "DELETE FROM PERSONAJES"; 
            
            PreparedStatement queryDelete = conex.prepareCall(query); 

            
            int resultado = queryDelete.executeUpdate(); 
            
                        
            queryDelete.close();
            conex.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
