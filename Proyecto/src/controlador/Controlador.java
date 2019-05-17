package controlador;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//se importa modelo e interfaz
import modelo.ModeloDepartamento;
import vista.Interfaz;
/**
 * @author Alberto
 */

public class Controlador implements ActionListener,MouseListener{

    /* instancia a nuestra interfaz de usuario*/
    Interfaz vista ;
    /** instancia a nuestro modelo */
    ModeloDepartamento modelo = new ModeloDepartamento();

    /** Se declaran en un ENUM las acciones que se realizan desde la
 interfaz de usuario VISTA y posterior ejecución desde el Controlador
     */
    public enum AccionProyecto
    {
        __VER_DEPARTAMENTOS,
        __AGREGAR_DEPARTAMENTOS,
        __ELIMINAR_DEPARTAMENTOS
    }

    /** Constrcutor de clase
     * @param vista Instancia de clase interfaz
     */
    public Controlador( Interfaz vista )
    {
        this.vista = vista;
    }

    /** Inicia el skin y las diferentes variables que se utilizan */
    public void iniciar()
    {
        System.out.println("Iniciar funciona");
        // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}

        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.__VER_DEPARTAMENTOS.setActionCommand( "__VER_DEPARTAMENTOS" );
        this.vista.__VER_DEPARTAMENTOS.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.__AGREGAR_DEPARTAMENTOS.setActionCommand( "__AGREGAR_DEPARTAMENTOS" );
        this.vista.__AGREGAR_DEPARTAMENTOS.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.__ELIMINAR_DEPARTAMENTOS.setActionCommand( "__ELIMINAR_DEPARTAMENTOS" );
        this.vista.__ELIMINAR_DEPARTAMENTOS.addActionListener(this);

        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.__tabla_departamentos.addMouseListener(this);
        this.vista.__tabla_departamentos.setModel( new DefaultTableModel() );
    }

    //Eventos que suceden por el mouse
    
    public void mouseClicked(MouseEvent e) {
        
        System.out.println("mouseClicked funciona");
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = this.vista.__tabla_departamentos.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.__departamento.setText( String.valueOf( this.vista.__tabla_departamentos.getValueAt(fila, 0) ));
                this.vista.__idCurso.setText( String.valueOf( this.vista.__tabla_departamentos.getValueAt(fila, 1) ));
                this.vista.__idProfesor.setText( String.valueOf( this.vista.__tabla_departamentos.getValueAt(fila, 2) ));
             }
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) { }
 
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    public void actionPerformed(ActionEvent e) {

    switch ( AccionProyecto.valueOf( e.getActionCommand() ) )
        {
            case __VER_DEPARTAMENTOS:
                System.out.println("case VER DEPARTAMENTOS se ve");
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.__tabla_departamentos.setModel( this.modelo.getTablaDepartamento() );
                break;
            /*case __AGREGAR_DEPARTAMENTOS:
                //añade un nuevo registro
                if ( this.modelo.NuevoProducto(
                        this.vista.__departamento.getText() ,
                        this.vista.__idCurso.getText(),
                        this.vista.__idProfesor.getText() ) )
                {
                    this.vista.__tabla_departamentos.setModel( this.modelo.getTablaDepartamento() );
                    JOptionPane.showMessageDialog(vista,"Exito: Nuevo registro agregado.");
                    this.vista.__departamento.setText("") ;
                    this.vista.__idCurso.setText("0");
                    this.vista.__idProfesor.setText("0") ;
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
            case __ELIMINAR_DEPARTAMENTOS:
                if ( this.modelo.EliminarProducto( this.vista.__departamento.getText() ) )
                {
                    this.vista.__tabla_departamentos.setModel( this.modelo.getTablaDepartamento() );
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                    this.vista.__departamento.setText("") ;
                    this.vista.__idCurso.setText("0");
                    this.vista.__idProfesor.setText("0") ;
                }
                break;  */     
        }
    }

}
