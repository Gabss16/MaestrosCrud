
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Maestros;
import vista.frMaestros;


public class ctrlMaestros implements MouseListener, KeyListener{
    //7.1-mandar a llamar a las otras capas (modelo y vista)
    //NOTA: importar librerias de las capas
    
    private Maestros modelo;
    private frMaestros vista;
    
     public ctrlMaestros( Maestros modelo, frMaestros vista){
         
         this.modelo = modelo;
    this.vista = vista;
    vista.insertat.addMouseListener(this);
    vista.jtMaestros.addMouseListener(this);
    //14-
    modelo.Mostrar(vista.jtMaestros);
     vista.btnEliminar.addMouseListener(this);
    //19
    vista.jtMaestros.addMouseListener(this);
    
    //22
    vista.btnActualizar.addMouseListener(this);
    vista.tfBuscar.addKeyListener(this);
     
     
     }

    @Override
    public void mouseClicked(MouseEvent e) {
        
         if(e.getSource()== vista.insertat){
             System.out.println("Botón Ingresar presionado");
        modelo.setNombre_Maestro(vista.txtNombreMaestro.getText());
        modelo.setEdad_Maestro(Integer.parseInt(vista.txtEdad.getText()));
        modelo.setPeso_Maestro(Double.parseDouble(vista.txtPeso.getText()));
        modelo.setCorreo_Maestro(vista.txtCorreo.getText());
        
        modelo.Guardar();}
        modelo.Mostrar(vista.jtMaestros);
        
        if(e.getSource()== vista.btnEliminar){
        modelo.Eliminar(vista.jtMaestros);
        modelo.Mostrar(vista.jtMaestros);
        
        
        }
        
        if(e.getSource()== vista.jtMaestros){
            modelo.cargarDatosTabla(vista);
        
        }
        
        //23
        
        if(e.getSource()== vista.btnActualizar){
            modelo.setNombre_Maestro(vista.txtNombreMaestro.getText());
    modelo.setEdad_Maestro(Integer.parseInt(vista.txtEdad.getText())); // Cambié a setEdad_Maestro
    modelo.setPeso_Maestro(Double.parseDouble(vista.txtPeso.getText())); // Agregado para el peso
    modelo.setCorreo_Maestro(vista.txtCorreo.getText()); // Agregado para el correo

    // Actualizamos el maestro en la base de datos
    modelo.Actualizar(vista.jtMaestros);
     modelo.Mostrar(vista.jtMaestros);
        
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        }

    @Override
    public void mouseReleased(MouseEvent e) {
         }

    @Override
    public void mouseEntered(MouseEvent e) {
         }

    @Override
    public void mouseExited(MouseEvent e) {
        }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource()== vista.tfBuscar){
            modelo.Buscar(vista.jtMaestros, vista.tfBuscar);}
           }

    @Override
    public void keyPressed(KeyEvent e) {
        }

    @Override
    public void keyReleased(KeyEvent e) {
        }
    
}
