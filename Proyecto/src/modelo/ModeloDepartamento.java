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
public class ModeloDepartamento extends Database {
    
    /** Constructor de clase */
    public ModeloDepartamento (){}
    
    /** Obtiene registros de la tabla Departamento y los devuelve en un DefaultComboBoxModel*/
    public DefaultComboBoxModel getListaDepartamento(){
      String nombre;
      DefaultComboBoxModel ahh = new DefaultComboBoxModel();
			// plantilla=new DefaultTableModel(null,headers);
			// tabla.setModel(plantilla);

			ArrayList<String> elementos=new ArrayList<String>();
			try {
				this.getConexion();
				PreparedStatement pstm = this.getConexion().prepareStatement("SELECT nombreDepartamento FROM Departamentos");
				ResultSet res = pstm.executeQuery();
				while (res.next()) {
					nombre = res.getString("nombreDepartamento");

					elementos.add(nombre);
				}
				//plantilla=new DefaultComboBoxModel(items);
				pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new DefaultComboBoxModel(elementos.toArray());    
    }
    
    public boolean NuevoDepartamento(String nombreDepartamento)
    {
        
        if( valida_datos(nombreDepartamento)  )
        {
            //Se arma la consulta
            String q="CALL nuevoDepartamento ( '" + nombreDepartamento + "' )";
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
    
    
    public DefaultTableModel getTablaDepartamentoProfesores(String departamento)
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Profesores"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "CALL getTablaDepartamentoProfesores1 ('"+departamento+"')");
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
         PreparedStatement pstm = this.getConexion().prepareStatement("CALL getTablaDepartamentoProfesores2 ('"+departamento+"')");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nombreProfesor" );
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
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT C.nombreCurso FROM Cursos C JOIN Profesores P ON P.idProfesor=C.idProfesor WHERE P.nombreDepartamento='"+departamento+"'");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nombreCurso" );
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
        String q = "CALL eliminarDepartamento ( '" + id + "' )" ;
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
    

