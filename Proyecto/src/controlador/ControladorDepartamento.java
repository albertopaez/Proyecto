package controlador;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthSeparatorUI;

import java.awt.event.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
//se importa modelo e interfaz
import modelo.ModeloDepartamento;
import vista.Interfaz;
/**
 * @author Alberto
 */

public class ControladorDepartamento implements ActionListener,MouseListener, ItemListener{
	
	 

    /* instancia a nuestra interfaz de usuario*/
    Interfaz vista ;
    /** instancia a nuestro modelo */
    ModeloDepartamento modelo = new ModeloDepartamento();

    /** Se declaran en un ENUM las acciones que se realizan desde la
 interfaz de usuario VISTA y posterior ejecución desde el Controlador
     */
    public enum AccionMVC
    {
        __AGREGAR_DEPARTAMENTOS,
        __ELIMINAR_DEPARTAMENTOS,
        __DEPARTAMENTOS_DESPLEGABLE
    }

    /** Constrcutor de clase
     * @param vista Instancia de clase interfaz
     */
    public ControladorDepartamento( Interfaz vista )
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
        this.vista.__AGREGAR_DEPARTAMENTOS.setActionCommand( "__AGREGAR_DEPARTAMENTOS" );
        this.vista.__AGREGAR_DEPARTAMENTOS.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.__ELIMINAR_DEPARTAMENTOS.setActionCommand( "__ELIMINAR_DEPARTAMENTOS" );
        this.vista.__ELIMINAR_DEPARTAMENTOS.addActionListener(this);
        
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.__tabla_departamentosCursos.addMouseListener(this);
        this.vista.__tabla_departamentosCursos.setModel( new DefaultTableModel() );
        
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.__tabla_departamentosProfesores.addMouseListener(this);
        this.vista.__tabla_departamentosProfesores.setModel( new DefaultTableModel() );
        
        //añade e inicia el comboBox con una lista de los departamentos
        this.vista.__DEPARTAMENTOS_DESPLEGABLE.addMouseListener(this);
        this.vista.__DEPARTAMENTOS_DESPLEGABLE.addItemListener(this);
        this.vista.__DEPARTAMENTOS_DESPLEGABLE.setModel( new DefaultComboBoxModel() );
        this.vista.__DEPARTAMENTOS_DESPLEGABLE.setModel( this.modelo.getListaDepartamento() );
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        this.vista.__tabla_departamentosProfesores.setModel
        ( this.modelo.getTablaDepartamentoProfesores
        (this.vista.__DEPARTAMENTOS_DESPLEGABLE.getSelectedItem().toString()) );
        
        this.vista.__tabla_departamentosCursos.setModel
        ( this.modelo.getTablaDepartamentoCursos
        (this.vista.__DEPARTAMENTOS_DESPLEGABLE.getSelectedItem().toString()) );
    }
    
   

    //Eventos que suceden por el mouse
    
    public void mouseClicked(MouseEvent e) {
        
        if( e.getButton()== 1)//boton izquierdo
        {
           
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) { }
 
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
        
        
        case __AGREGAR_DEPARTAMENTOS:
                //añade un nuevo departamento
                if ( this.modelo.NuevoDepartamento(this.vista.__departamento.getText()) )
                {
                    this.vista.__DEPARTAMENTOS_DESPLEGABLE.setModel( this.modelo.getListaDepartamento() );
                    JOptionPane.showMessageDialog(vista,"Exito: Nuevo departamento agregado.");
                    this.vista.__departamento.setText("") ;
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
                
                
	case __ELIMINAR_DEPARTAMENTOS:
            if ( this.modelo.EliminarDepartamento( this.vista.__departamento.getText() ) )
                {
                    this.vista.__DEPARTAMENTOS_DESPLEGABLE.setModel( this.modelo.getListaDepartamento() );
                    JOptionPane.showMessageDialog(vista,"Exito: Departamento eliminado.");
                    this.vista.__departamento.setText("");
                }else{
                JOptionPane.showMessageDialog(vista,"Fallo: No puede eliminar departamentos con profesores asociados.");
            }
		break;
	default:
		break;
         
        }
    }

	

}
