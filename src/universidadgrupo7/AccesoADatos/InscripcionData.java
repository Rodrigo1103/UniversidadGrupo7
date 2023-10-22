package universidadgrupo7.AccesoADatos;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo7.Entidades.*;


public class InscripcionData {
    private Connection con=null;
    private MateriaData md=new MateriaData();
    private AlumnoData ad=new AlumnoData();
    
    public InscripcionData(){
        con=Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion ins){
        String sql="INSERT INTO inscripcion(idAlumno,idMateria,nota) VALUES(?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ins.getAlumno().getIdAlumno());
            ps.setInt(2, ins.getMateria().getIdMateria());
            ps.setDouble(3, ins.getNota());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                ins.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"Inscripcion Guardada");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        
    }
    
    public void actualizarNota(int idAlumno,int idMateria,double nota){
        String sql="UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=? ";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Nota actualizada");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        
        
    }
    
    public void borrarInscripcion(int idAlumno,int idMateria){
        String sql="DELETE FROM inscripcion WHERE idAlumno=? AND idMateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2,idMateria);
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Inscripcion borrada");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        
    }
    
    public List<Inscripcion> obtenerInscripciones(){
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        String sql="SELECT * FROM inscripcion";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion i1=new Inscripcion();
                i1.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno a1=ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia m1=md.buscarMateria(rs.getInt("idMateria"));
                i1.setAlumno(a1);
                i1.setMateria(m1);
                i1.setNota(rs.getDouble("nota"));
                cursadas.add(i1);
            }
            ps.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        
        return cursadas;
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno){
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        String sql="SELECT * FROM inscripcion WHERE idAlumno=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion i1=new Inscripcion();
                i1.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno a1=ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia m1=md.buscarMateria(rs.getInt("idMateria"));
                i1.setAlumno(a1);
                i1.setMateria(m1);
                i1.setNota(rs.getDouble("nota"));
                cursadas.add(i1);
            }
            ps.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        
        return cursadas;
    }
    
    public List<Materia> obtenerMateriasCursadas(int idAlumno){
        ArrayList<Materia> materias=new ArrayList<>();
        String sql="SELECT inscripcion.idMateria, nombre, anio FROM inscripcion,materia "
                + "WHERE inscripcion.idMateria=materia.idMateria AND inscripcion.idAlumno=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia ma=new Materia();
                ma.setIdMateria(rs.getInt("idMateria"));
                ma.setNombre(rs.getString("nombre"));
                ma.setAnioMateria(rs.getInt("anio"));
                materias.add(ma);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        
        
        return materias;
    }
    
    public List<Materia> obtenerMateriasNOCursadas(int idAlumno){
        ArrayList<Materia> materias=new ArrayList<>();
        String sql="SELECT * FROM materia WHERE estado=1 AND idMAteria NOT IN "
                + "(SELECT idMateria FROM inscripcion WHERE idAlumno=?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia ma=new Materia();
                ma.setIdMateria(rs.getInt("idMateria"));
                ma.setNombre(rs.getString("nombre"));
                ma.setAnioMateria(rs.getInt("anio"));
                materias.add(ma);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        
        
        return materias;
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria){
        ArrayList<Alumno> alumnos=new ArrayList<>();
        String sql="SELECT a.idAlumno,dni,nombre,apellido,fechaNacimiento,estado "
                + "FROM inscripcion i,alumno a WHERE i.idAlumno=a.idAlumno AND idMateria=? AND a.estado=1";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Alumno a1=new Alumno();
                a1.setIdAlumno(rs.getInt("idAlumno"));
                a1.setNombre(rs.getString("nombre"));
                a1.setApellido(rs.getString("apellido"));
                a1.setDni(rs.getInt("dni"));
                a1.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                a1.setActivo(rs.getBoolean("estado"));
                alumnos.add(a1);
                
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        
        
        return alumnos;
    }
}
