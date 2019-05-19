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
public class ModeloCurso extends Database {
    
    /** Constructor de clase */
    public ModeloCurso (){}
    
    public DefaultTableModel getTablaCursos()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Materia","Tipo","Profesor","Creditos", "Precio", "Horas", "ID"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Cursos");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][7];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Cursos");
         ResultSet res = pstm.executeQuery();
        
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "materia" );
                data[i][1] = res.getString( "tipoCurso" );
                data[i][3] = res.getString( "creditos" );
                data[i][4] = res.getString( "precio" );
                data[i][5] = res.getString( "horas" );
                data[i][6] = res.getString( "idCurso" );
            i++;
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement
        ("SELECT P.nombreProfesor FROM Profesores P JOIN Cursos C ON P.idProfesor=C.idProfesor");
         ResultSet res = pstm.executeQuery();
        
         int i=0;
         while(res.next()){
                data[i][2] = res.getString( "nombreProfesor" );
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
    
    private boolean valida_datos(String nombreProfesor, String nombreDepartamento , String tipoProfesor,
            String tutorias, String horasInvestigacion,String sueldo, String idProfesor,
            String usuario, String contrasena){
        
        if( nombreDepartamento.equals("") || nombreProfesor.equals("") || tipoProfesor.equals("") || tutorias.equals("")
                 || sueldo.equals("") || idProfesor.equals("") || usuario.equals("") || contrasena.equals("")){
            return false;
        }else return true;
    }
    
    
    
    

}
    

