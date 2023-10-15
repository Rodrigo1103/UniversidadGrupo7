/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo7.AccesoADatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rodri
 */
public class Conexion {
    private static final String URL="jdbc:mariadb://localhost:3307/";
    private static final String BD="universidadulp";
    private static final String USUARIO="root";
    private static final String PASSWORD="";
    private static Connection connection;
    
    private Conexion(){}
        
    public static Connection getConexion(){
        if (connection==null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection=DriverManager.getConnection(URL+BD,USUARIO,PASSWORD);
                JOptionPane.showMessageDialog(null,"Conectado a la base de datos");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Error al cargar Driver "+ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error de Conexion "+ex.getMessage());
            }
        }
            
        return connection;
    }
}
