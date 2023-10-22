/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo7.AccesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo7.Entidades.Materia;

/**
 *
 * @author rodri
 */
public class MateriaData {
    private Connection con=null;
    
    public MateriaData(){
        con=Conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia){
        String sql="INSERT INTO materia (nombre,anio,estado)"
                + " VALUES(?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isActivo());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"Materia Guardada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia "+ex.getMessage());
        }
        
    }
    
        public Materia buscarMateria(int id){
        String sql="SELECT nombre,anio FROM materia WHERE idMateria=? AND estado=1";
        Materia mat=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                mat=new Materia();
                mat.setIdMateria(id);
                mat.setNombre(rs.getString("nombre"));
                mat.setAnioMateria(rs.getInt("anio"));
                mat.setActivo(true);
                
            }else{
                JOptionPane.showMessageDialog(null,"No existe la materia");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia "+ex.getMessage());
        }
        return mat;
    }
        
        public void modificarMateria(Materia materia){
        String sql="UPDATE materia set nombre=?, anio=? "
                + " WHERE idMateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setInt(3, materia.getIdMateria());
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Materia actualizada");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia "+ex.getMessage());
        }
    }
        
        public void eliminarMateria(int id){
        String sql="UPDATE materia SET estado=0 WHERE idMateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Materia borrada");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia "+ex.getMessage());
        }
    }
        
        
        public List<Materia> listarMaterias(){
        String sql="SELECT idMateria,nombre,anio FROM materia WHERE estado=1";
        ArrayList<Materia> materias=new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("anio"));
                materia.setActivo(true);
                materias.add(materia);
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia "+ex.getMessage());
        }
        return materias;
    }
    
}
