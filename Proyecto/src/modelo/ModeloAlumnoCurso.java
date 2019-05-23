/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alberto
 */
public class ModeloAlumnoCurso extends Database{
    
    /** Constructor de clase */
    public ModeloAlumnoCurso (){}
    
    /** Elimina un registro dado su ID -> Llave primaria */
    public boolean EliminarAlumnoCurso( String idAlumno, String nombreCurso )
    {
         boolean res=false;
        //se arma la consulta
        String q = "DELETE FROM CursosAlumnos WHERE idAlumno='"+idAlumno+"' AND nombreCurso='"+nombreCurso+"'" ;
        //se ejecuta la consulta
         try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    
    
    /** Registra un nuevo producto */
    public boolean nuevoAlumnoCurso(String idAlumno, String nombreCurso)     
    {
        
         
            //Se arma la consulta
            String q=" INSERT INTO CursosAlumnos ( idAlumno,  nombreCurso) "
                    + "VALUES ( '" + idAlumno + "','" + nombreCurso +"' ) ";
            
            
            
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
    }
}
