/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_exposito_martinez_pablo;

import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class AccesoDatosBBDDSuperpoderes extends AccesoDatosAbstractSuperpoderes {
    
    private Superpoder parse (ResultSet rs){
        Superpoder superpoder = new Superpoder(); 
        try {
            
            superpoder.setDescripcion(rs.getString("Descripcion"));
            superpoder.setId(rs.getInt("Id"));
            superpoder.setNivel(rs.getInt("Nivel"));
            superpoder.setNombre(rs.getString("Nombre"));
            
        } catch (SQLException e){
            e.printStackTrace();
            
            
        }
        return superpoder;
    }

    @Override
    public List<Superpoder> getAll() {

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = AppConfig.cadenaConexion; 
            String pwd = AppConfig.password; 
            String user = AppConfig.usuario; 
            Connection conex = DriverManager.getConnection(url, user, pwd);
            
            String query = "SELECT * FROM SUPERHEROES"; 
            
            PreparedStatement  querySelect = conex.prepareStatement(query); 
            
            ResultSet resultado = querySelect.executeQuery(); 
            
            while (resultado.next()){
                Superpoder superpoder = parse(resultado); 
                super.superpoderes.add(superpoder); 
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.superpoderes; 
        
        
    }

    @Override
    public Superpoder getById(int id) {
        Superpoder superpoder = null; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            String url = AppConfig.cadenaConexion; 
            String pwd = AppConfig.password; 
            String user = AppConfig.usuario; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "SELECT * FROM SUPERPODERES WHERE id = ?"; 
            
            PreparedStatement querySelect = conex.prepareCall(query); 
            
            querySelect.setInt(1, id);
            
            ResultSet resultado = querySelect.executeQuery(); 
            
            while (resultado.next()){
                superpoder = parse (resultado); 
            }
            
            resultado.close();
            querySelect.close();
            conex.close();
            
            return superpoder; 
            
            
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null; 
    }

    @Override
    public void insert(Superpoder superpoder) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            String url = AppConfig.cadenaConexion; 
            String pwd = AppConfig.password; 
            String user = AppConfig.usuario; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "INSERT INTO SUPERPODERES VALUES (?,?,?,?)"; 
            
            PreparedStatement queryInsert = conex.prepareCall(query);
            
            
            queryInsert.setInt(1, superpoder.getId());
            queryInsert.setString(2, superpoder.getNombre());
            queryInsert.setString(3, superpoder.getDescripcion());
            queryInsert.setInt(4, superpoder.getNivel());
            
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
    public void update(Superpoder superpoder) {
        try {
            int identif = superpoder.getId(); //utilizado para queryUpdate 
            String objeto = superpoder.getNombre(); //utilizado para queryUpdate
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = AppConfig.cadenaConexion; 
            String user = AppConfig.usuario; 
            String pwd = AppConfig.password; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "UPDATE SUPERPODERES SET NOMBRE= ? WHERE ID = ? "; 
            
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
    public void delete(Superpoder superpoder) {
        try {
            int identif = superpoder.getId(); //utilizado para queryDelete
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = AppConfig.cadenaConexion; 
            String user = AppConfig.usuario; 
            String pwd = AppConfig.password; 
            
            Connection conex = DriverManager.getConnection(url, user, pwd); 
            
            String query = "DELETE FROM SUPERPODERES WHERE ID = ? "; 
            
            PreparedStatement queryDelete = conex.prepareCall(query); 
            
            queryDelete.setInt(1, identif);
            
            int resultado = queryDelete.executeUpdate(); 
            
            conex.close();
            queryDelete.close();
            
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
            
            String query = "DELETE FROM SUPERPODERES"; 
            
            PreparedStatement queryDelete = conex.prepareCall(query); 

            
            int resultado = queryDelete.executeUpdate(); 
            
            conex.close();
            queryDelete.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatosBBDDSuperpoderes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
