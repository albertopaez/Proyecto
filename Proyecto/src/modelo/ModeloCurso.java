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
      String[] columNames = {"Nombre","Tipo","Profesor","Creditos", "Precio", "Horas"};
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
    Object[][] data = new String[registros][6];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Cursos");
         ResultSet res = pstm.executeQuery();
        
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nombreCurso" );
                data[i][1] = res.getString( "tipoCurso" );
                data[i][3] = res.getString( "creditos" );
                data[i][4] = res.getString( "precio" );
                data[i][5] = res.getString( "horas" );
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
    
    /** Elimina un registro dado su ID -> Llave primaria */
    public boolean EliminarCurso( String id )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM Cursos WHERE  nombreCurso='" + id + "' " ;
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
    
    public boolean nuevoCurso(String nombreCurso, String tipoCurso , String idProfesor,
            String creditos,String horas, String precio)     
    {
        if( valida_datos( nombreCurso,  tipoCurso ,  idProfesor,  creditos, horas,  precio)  )
        {
            if(tipoCurso=="Completo"){
            horas = "0";
            precio = "0";
        }else{creditos="0";}
            //se reemplaza "," por "."
            precio = precio.replace(",", ".");
            precio = precio.replace("'", ".");
            //Se arma la consulta
            String q=" INSERT INTO Cursos ( nombreCurso,  tipoCurso ,  idProfesor,  creditos, horas,  precio) "
                    + "VALUES ( '" + nombreCurso + "','" + tipoCurso + "', '" + idProfesor + "'," 
                    + creditos + "," + horas + ", "+ precio +" ) ";
            
            
            
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
    
    private boolean valida_datos(String nombreCurso, String tipoCurso , String idProfesor,
            String creditos,String horas, String precio){
        
        if( nombreCurso.equals("") || tipoCurso.equals("") || creditos.equals("")
                 || horas.equals("") || idProfesor.equals("") || precio.equals("")){
            return false;
        }else return true;
    }
    
    
    
    

}
    

