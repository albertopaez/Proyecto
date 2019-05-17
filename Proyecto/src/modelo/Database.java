package modelo;

import java.sql.*;
/**

 * @author Mouse
 */

public class Database {

	/* DATOS PARA LA CONEXION */
	  /** base de datos por defecto es test*/
	  private String db = "apaez_ProyectoGrupo";
	  /** usuario */
	  private String user = "apaez_alberto";
	  /** contrase�a de MySql*/
	  private String password = "Salesianas1**";
	  /** Cadena de conexion */
	  private String url = "jdbc:mysql://apaez.salesianas.es/"+db;
	  /** variable para trabajar con la conexion a la base de datos */
	  private Connection conn = null;

	   /** Constructor de clase */
	   public Database(){
	        this.url = "jdbc:mysql://apaez.salesianas.es/"+this.db;
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