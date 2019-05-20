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
public class ModeloAlumno extends Database{
    
    public DefaultTableModel getAlumnosGeneral()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Nombre", "ID"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Alumnos");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         System.out.println(registros);
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][2];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT nombreAlumno, idAlumno FROM Alumnos");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nombreAlumno" );
                data[i][1] = res.getString( "idAlumno" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    public DefaultTableModel getTablaAlumnosPersonal(String idProfesor)
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Nombre", "Departamento", "Tipo", "Tutorias", "Horas de Investigación", "Sueldo",
          "ID", "Usuario", "Contraseña"};
      
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[1][9];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Profesores WHERE idProfesor='"+idProfesor+"'");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nombreProfesor" );
                data[i][1] = res.getString( "nombreDepartamento" );
                data[i][2] = res.getString( "tipoProfesor" );
                data[i][3] = res.getString( "tutorias" );
                data[i][4] = res.getString( "horasInvestigacion" );
                data[i][5] = res.getString("sueldo" );
                data[i][6] = res.getString( "idProfesor" );
                data[i][7] = res.getString( "usuario" );
                data[i][8] = res.getString( "contrasena" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    
    
    /** Elimina un registro dado su ID -> Llave primaria */
    public boolean EliminarAlumno( String id )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM Alumnos WHERE  idAlumno='" + id + "' " ;
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
    
}
