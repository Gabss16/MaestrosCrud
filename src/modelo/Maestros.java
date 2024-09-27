/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.frMaestros;

/**
 *
 * @author Gudelia
 */
public class Maestros {
    
    //4- parametros
    
    private String uuid_maestro;
    private String  Nombre_Maestro;
    private int Edad_Maestro;
    private Double Peso_Maestro;

    public Double getPeso_Maestro() {
        return Peso_Maestro;
    }

    public void setPeso_Maestro(Double Peso_Maestro) {
        this.Peso_Maestro = Peso_Maestro;
    }
    private String Correo_Maestro;

    public String getUuid_maestro() {
        return uuid_maestro;
    }

    public void setUuid_maestro(String uuid_maestro) {
        this.uuid_maestro = uuid_maestro;
    }

    public String getNombre_Maestro() {
        return Nombre_Maestro;
    }

    public void setNombre_Maestro(String Nombre_Maestro) {
        this.Nombre_Maestro = Nombre_Maestro;
    }

    public int getEdad_Maestro() {
        return Edad_Maestro;
    }

    public void setEdad_Maestro(int Edad_Maestro) {
        this.Edad_Maestro = Edad_Maestro;
    }

    

    public String getCorreo_Maestro() {
        return Correo_Maestro;
    }

    public void setCorreo_Maestro(String Correo_Maestro) {
        this.Correo_Maestro = Correo_Maestro;
    }
    
    //6- Agregar las funciones(insertar, actualizar, eliminar)
    //6.1-no olvidar importar manualmente la libreria de coneccion y por alt + enter la de uuid
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO tbmaestro (UUID_Maestro, Nombre_Maestro, Edad_Maestro, Peso_Maestro, Correo_Maestro) VALUES (?, ?, ?, ?,?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre_Maestro());
            addProducto.setInt(3, getEdad_Maestro());
            addProducto.setDouble(4,getPeso_Maestro());
            addProducto.setString(5, getCorreo_Maestro());
            //Ejecutar la consulta
            addProducto.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        // Creamos una variable de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        // Definimos el modelo de la tabla con las columnas correspondientes a la tabla tbMaestros
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Maestro", "Nombre_Maestro", "Edad_Maestro", "Peso_Maestro", "Correo_Maestro"});

        try {
            // Creamos un Statement
            Statement statement = conexion.createStatement();
            
            // Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbmaestro");
            
            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getString("UUID_Maestro"), 
                    rs.getString("Nombre_Maestro"), 
                    rs.getInt("Edad_Maestro"), 
                    rs.getDouble("Peso_Maestro"), 
                    rs.getString("Correo_Maestro")
                });
            }
            
            // Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar: " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
    // Creamos una variable igual a ejecutar el método de la clase de conexión
    Connection conexion = ClaseConexion.getConexion();

    // Obtenemos la fila seleccionada por el usuario
    int filaSeleccionada = tabla.getSelectedRow();

    // Verificar si se ha seleccionado una fila
    if (filaSeleccionada != -1) {
        // Obtenemos el id (UUID_Maestro) de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();

        // Eliminamos el registro correspondiente
        try {
            PreparedStatement deleteMaestro = conexion.prepareStatement("DELETE FROM tbmaestro WHERE UUID_Maestro = ?");
            deleteMaestro.setString(1, miId);
            deleteMaestro.executeUpdate();
            System.out.println("Registro eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Este es el error en el método Eliminar: " + e.getMessage());
        }
    } else {
        System.out.println("Debe seleccionar una fila para eliminar.");
        }
    }
    
    public void cargarDatosTabla(frMaestros vista) {
    // Obtén la fila seleccionada
    int filaSeleccionada = vista.jtMaestros.getSelectedRow();

    // Debemos asegurarnos de que haya una fila seleccionada antes de acceder a sus valores
    if (filaSeleccionada != -1) {
        // Obtenemos los valores de la fila seleccionada
        String UUIDDeTb = vista.jtMaestros.getValueAt(filaSeleccionada, 0).toString();
        String NombreDeTB = vista.jtMaestros.getValueAt(filaSeleccionada, 1).toString();
        String EdadDeTb = vista.jtMaestros.getValueAt(filaSeleccionada, 2).toString();
        String PesoDeTB = vista.jtMaestros.getValueAt(filaSeleccionada, 3).toString();
        String CorreoDeTB = vista.jtMaestros.getValueAt(filaSeleccionada, 4).toString();

        // Establece los valores en los campos de texto de la vista
        vista.txtNombreMaestro.setText(NombreDeTB);
        vista.txtEdad.setText(EdadDeTb);
        vista.txtPeso.setText(PesoDeTB);
        vista.txtCorreo.setText(CorreoDeTB);
    }
}
    
    public void Actualizar(JTable tabla) {
    // Creamos una variable igual a ejecutar el método de la clase de conexión
    Connection conexion = ClaseConexion.getConexion();

    // Obtenemos qué fila seleccionó el usuario
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        // Obtenemos el id de la fila seleccionada
        String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            // Ejecutamos la Query
            PreparedStatement updateMaestro = conexion.prepareStatement(
                "UPDATE tbmaestro SET Nombre_Maestro = ?, Edad_Maestro = ?, Peso_Maestro = ?, Correo_Maestro = ? WHERE UUID_Maestro = ?"
            );

            // Asignamos los nuevos valores a la consulta
            updateMaestro.setString(1, getNombre_Maestro());
            updateMaestro.setInt(2, getEdad_Maestro());
            updateMaestro.setDouble(3, getPeso_Maestro());
            updateMaestro.setString(4, getCorreo_Maestro());
            updateMaestro.setString(5, miUUID);

            // Ejecutamos la actualización
            updateMaestro.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error en el método de actualizar: " + e);
        }
    } else {
        System.out.println("No se ha seleccionado ninguna fila para actualizar.");
    }
}
    
    public void Buscar(JTable tabla, JTextField txtBuscar) {
    // Creamos una variable igual a ejecutar el método de la clase de conexión
    Connection conexion = ClaseConexion.getConexion();
    // Definimos el modelo de la tabla
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"UUID_Maestro", "Nombre_Maestro", "Edad_Maestro", "Peso_Maestro", "Correo_Maestro"}); // Adaptado a las columnas de tbMaestro
    
    try {
        // Preparamos la consulta SQL con un LIKE para buscar por nombre
        PreparedStatement buscarMaestro = conexion.prepareStatement("SELECT * FROM tbMaestro WHERE Nombre_Maestro LIKE ? || '%'"); 
        buscarMaestro.setString(1, txtBuscar.getText());
        ResultSet rs = buscarMaestro.executeQuery();
        
        // Recorremos el ResultSet
        while (rs.next()) {
            // Llenamos el modelo por cada vez que recorremos el ResultSet
            modelo.addRow(new Object[]{
                rs.getString("UUID_Maestro"), 
                rs.getString("Nombre_Maestro"), 
                rs.getInt("Edad_Maestro"), 
                rs.getDouble("Peso_Maestro"), 
                rs.getString("Correo_Maestro") 
            });
        }
        // Asignamos el nuevo modelo lleno a la tabla
        tabla.setModel(modelo);
        
    } catch (Exception e) {
        System.out.println("Este es el error en el modelo, metodo de buscar " + e);
    }
}}
