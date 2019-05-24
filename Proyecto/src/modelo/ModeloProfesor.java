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
    
    
    //Obtenemos todos los registros de la tabla Profesores y los pintamos en una tabla
    public DefaultTableModel getTablaProfesoresGeneral()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Nombre", "Departamento", "Tipo", "Tutorias", "Horas de Investigación", "Sueldo",
          "ID", "Usuario", "Contraseña"};
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
    Object[][] data = new String[registros][9];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Profesores");
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
    public boolean EliminarProfesor( String id )
    {
         boolean res=false;
        //se arma la consulta
        String q = "CALL EliminarProfesor ('"+id+"')" ;
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
    
    /** Registra un nuevo profesor */
    public boolean nuevoProfesor(String nombreProfesor, String nombreDepartamento , String tipoProfesor,
            String tutorias, String horasInvestigacion,String sueldo, String idProfesor,
            String usuario, String contrasena)     
    {
        if( valida_datos( nombreProfesor,  nombreDepartamento ,  tipoProfesor,
             tutorias,  horasInvestigacion, sueldo,  idProfesor,
             usuario,  contrasena)  )
        {
            if(tipoProfesor=="Asociado"){
            horasInvestigacion = "0";
        }
            //se reemplaza "," por "."
            sueldo = sueldo.replace(",", ".");
            sueldo = sueldo.replace("'", ".");
            //Se arma la consulta
            String q=" INSERT INTO Profesores ( nombreProfesor,  nombreDepartamento ,  tipoProfesor, "
                    + "tutorias,  horasInvestigacion, sueldo,  idProfesor, usuario,  contrasena  ) "
                    + "VALUES ( '" + nombreProfesor + "','" + nombreDepartamento + "', '" +
                    tipoProfesor + "','" + tutorias + "'," + horasInvestigacion + "," +
                    sueldo + ", '" + idProfesor + "','" + usuario + "','" + contrasena +"' ) ";
            
            
            
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
    
    //Metodo auxiliar para validara los datos de entrada
    private boolean valida_datos(String nombreProfesor, String nombreDepartamento , String tipoProfesor,
            String tutorias, String horasInvestigacion,String sueldo, String idProfesor,
            String usuario, String contrasena){
        
        if( nombreDepartamento.equals("") || nombreProfesor.equals("") || tipoProfesor.equals("") || tutorias.equals("")
                 || sueldo.equals("") || idProfesor.equals("") || usuario.equals("") || contrasena.equals("")){
            return false;
        }else return true;
    }
    
    //Obtenemos las id de Profesores y las pintamos en un combobox 
    public DefaultComboBoxModel getListaProfesor(){
      String nombre;
      DefaultComboBoxModel ahh = new DefaultComboBoxModel();
			// plantilla=new DefaultTableModel(null,headers);
			// tabla.setModel(plantilla);

			ArrayList<String> elementos=new ArrayList<String>();
			try {
				this.getConexion();
				PreparedStatement pstm = this.getConexion().prepareStatement("SELECT idProfesor FROM Profesores");
				ResultSet res = pstm.executeQuery();
				while (res.next()) {
					nombre = res.getString("idProfesor");

					elementos.add(nombre);
				}
				//plantilla=new DefaultComboBoxModel(items);
				pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new DefaultComboBoxModel(elementos.toArray());    
    }
    
    /** Modifica el regustro de un profesor */
    public boolean modificaProfesor(String nombreProfesor, String nombreDepartamento , String tipoProfesor,
            String tutorias, String horasInvestigacion,String sueldo, String idProfesor, String usuario, String contrasena)     
    {
            if(tipoProfesor=="Asociado"){
            horasInvestigacion = "0";
        }
            //se reemplaza "," por "."
            sueldo = sueldo.replace(",", ".");
            sueldo = sueldo.replace("'", ".");
            //Se arma la consulta
            String q=" UPDATE Profesores SET nombreProfesor='" + nombreProfesor + "', nombreDepartamento='"
                    + nombreDepartamento + "', tipoProfesor='" +tipoProfesor + "', tutorias='" + tutorias +
                    "', horasInvestigacion=" + horasInvestigacion + ", sueldo=" + sueldo + ", usuario='" + usuario + 
                    "', contrasena='" + contrasena +"' WHERE idProfesor='"+idProfesor+"'";
            
            
            
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
    

