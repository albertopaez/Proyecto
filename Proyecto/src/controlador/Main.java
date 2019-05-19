package controlador;
import vista.Interfaz;
/**

 * @author Mouse
 */
public class Main {
    
    public static void main(String[] args) {
        //ejecuta el Controlador y este la vista
        new ControladorDepartamento( new Interfaz() ).iniciar() ;
    }

}
