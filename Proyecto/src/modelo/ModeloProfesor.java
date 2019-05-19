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
public class ModeloProfesor extends Database {
    
    /** Constructor de clase */
    public ModeloProfesor (){}
    
    
    
    public boolean NuevoDepartamento(String nombreDepartamento)
    {
        
        if( valida_datos(nombreDepartamento)  )
        {
            //Se arma la consulta
            String q=" INSERT INTO Departamentos ( nombreDepartamento  ) "
                    + "VALUES ( '" + nombreDepartamento + "' )";
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
        else
         return false;
    }
    
    
    private boolean valida_datos(String nombreDepartamento){
        if( nombreDepartamento.equals("") ){
            return false;
        }else return true;
    }
    
    
    public DefaultTableModel getTablaProfesoresGeneral()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Nombre", "ID"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Profesores");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         System.out.println(registros);
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][5];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT nombreProfesor, idProfesor FROM Profesores");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nombreProfesor" );
                data[i][1] = res.getString( "idProfesor" );
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
    
    public DefaultTableModel getTablaDepartamentoCursos(String departamento)
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Cursos"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Cursos C JOIN Profesores P ON P.idProfesor=C.idProfesor WHERE P.nombreDepartamento='"+departamento+"'");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         System.out.println(registros);
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][5];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT C.materia FROM Cursos C JOIN Profesores P ON P.idProfesor=C.idProfesor WHERE P.nombreDepartamento='"+departamento+"'");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "materia" );
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
    public boolean EliminarDepartamento( String id )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM Departamentos WHERE  nombreDepartamento='" + id + "' " ;
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
    

