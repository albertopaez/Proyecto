package modelo;

import java.sql.*;
import java.util.List;
import static modelo.ManejaArchivos.manejaArchivos;
/**

 * @author Mouse
 */

public class Database {
    
    private static final String NOMBRE_ARCHIVO = "/DatabaseVariables.txt";
    
    List<String> lista = manejaArchivos(NOMBRE_ARCHIVO);
    
    

	/* DATOS PARA LA CONEXION */
	  /** base de datos por defecto es test*/
	  private String db = lista.get(0);
	  /** usuario */
	  private String user = lista.get(1);
	  /** contrase�a de MySql*/
	  private String password = lista.get(2);
	  /** Cadena de conexion */
	  private String url = lista.get(3)+db;
	  /** variable para trabajar con la conexion a la base de datos */
	  private Connection conn = null;

	   /** Constructor de clase */
	   public Database(){
               
	       try{
	         //obtenemos el driver de para mysql
	         Class.forName("com.mysql.jdbc.Driver");
	         //obtenemos la conexi�n
	         conn = DriverManager.getConnection( this.url, this.user , this.password );         
	      }catch(SQLException e){
	         System.err.println( e.getMessage() );
	      }catch(ClassNotFoundException e){
	         System.err.println( e.getMessage() );
	      }
	   }


	   public Connection getConexion()
	   {
	    return this.conn;
	   }

	}