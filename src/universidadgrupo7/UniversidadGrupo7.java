/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo7;

import java.sql.*;
import java.time.LocalDate;
import universidadgrupo7.AccesoADatos.AlumnoData;
import universidadgrupo7.AccesoADatos.Conexion;
import universidadgrupo7.Entidades.Alumno;

/**
 *
 * @author rodri
 */
public class UniversidadGrupo7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conexion=Conexion.getConexion();
        //Alumno juan=new Alumno(1,12312312,"Luna","Juan Pedro",LocalDate.of(1980,4,25),true);
        AlumnoData al=new AlumnoData();
        //al.guardarAlumno(juan);
        //al.modificarAlumno(juan);
        //al.eliminarAlumno(1);
        /*Alumno a1=al.buscarAlumnoPorDni(33123123);
        if(a1!=null){
            System.out.println(a1.toString());
        }
        */
        
        for(Alumno alumno:al.listarAlumno()){
            System.out.println(alumno.toString());
        }
        
    }
    
}
