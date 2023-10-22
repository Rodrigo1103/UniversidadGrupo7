package universidadgrupo7;

import java.sql.*;
import java.time.LocalDate;
import universidadgrupo7.AccesoADatos.*;
import universidadgrupo7.Entidades.*;


public class UniversidadGrupo7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conexion=Conexion.getConexion();
        //Alumno juan=new Alumno(1,12312312,"Luna","Juan Pedro",LocalDate.of(1980,4,25),true);
        //AlumnoData al=new AlumnoData();
        
        //Materia mat=new Materia(1,"Base de Datos",2023,true);
        //MateriaData m1=new MateriaData();
        
//        m1.guardarMateria(mat);
//        System.out.println(mat.getIdMateria());

        //al.guardarAlumno(juan);
        //al.modificarAlumno(juan);
        //al.eliminarAlumno(1);
        /*Alumno a1=al.buscarAlumnoPorDni(33123123);
        if(a1!=null){
            System.out.println(a1.toString());
        }
        */
        
//        for(Alumno alumno:al.listarAlumno()){
//            System.out.println(alumno.toString());
//        }
        
//        mat=m1.buscarMateria(1);
//        if(m1!=null){
//            System.out.println(mat.toString());
//        }
        
        
        //m1.modificarMateria(mat);
        
        //m1.eliminarMateria(2);
        
//        for(Materia materia:m1.listarMaterias()){
//            System.out.println(materia.toString());
//        }
        
        AlumnoData ad=new AlumnoData();
        MateriaData md=new MateriaData();
        InscripcionData id=new InscripcionData();
        
        //Alumno a1=ad.buscarAlumno(3);
        //Materia m1=md.buscarMateria(1);
        //Inscripcion i1=new Inscripcion(a1,m1,9);
        //id.guardarInscripcion(i1);
        //id.actualizarNota(3, 1, 9.5);
        //id.borrarInscripcion(3, 1);
        
//        for(Inscripcion inscripcion:id.obtenerInscripciones()){
//            System.out.println("ID: "+inscripcion.getIdInscripcion());
//            System.out.println("Apellido: "+inscripcion.getAlumno().getApellido());
//            System.out.println("Materia: "+inscripcion.getMateria().getNombre());
//            System.out.println("-------------------------------------------------");
//        }
        
//       for(Inscripcion ins:id.obtenerInscripcionesPorAlumno(1)){
//           System.out.println("ID: "+ins.getIdInscripcion());
//            System.out.println("Apellido: "+ins.getAlumno().getApellido());
//            System.out.println("Materia: "+ins.getMateria().getNombre());
//            System.out.println("-------------------------------------------------");
//       }
       
        for(Materia materia:id.obtenerMateriasNOCursadas(3)){
            System.out.println("Nombre "+materia.getNombre());
        }
       
       
       
       
    }
    
}
