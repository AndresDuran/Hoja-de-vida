package Interfaz;

import Logica.personas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Jorge Duran
 * @author Albert Charry
 */
public class verPersona extends JDialog {
    JPanel panel;//panel principal
    JTable tablaPersonas;//tabla de personas
    private DefaultTableModel tablaDefecto;//tabla por defecto
    private ventanaPrincipal ventana;
    JLabel labelImagen;
    private personas persona;//llama la clase perosna del paquete logico
    
    //constructor de la clase dialogo persona
    public verPersona(ventanaPrincipal ventana) {
        
        this.ventana = ventana; //saca la ventana
        setSize(1100, 400);//tamaño
        setTitle("Datos de personas");//nombre de la ventana
        
        panel = new JPanel();//panel que agrega tabla
        panel.setBorder(BorderFactory.createTitledBorder("Personas"));
        panel.setBackground(Color.WHITE);//color de la ventana
        panel.setLayout(null);//Actualiza
        add(panel);//añade
        
        tablaDefecto = new DefaultTableModel();
        tablaPersonas = new JTable(tablaDefecto);
        
        Object[] columna = {"Foto", "Nombre", "Apellido"," Cedula", " Correo", "Profesion","Fecha De Nacimiento", "Edad"};
        
        for (Object columna1 : columna) {//llena con los nombres y demas
            tablaDefecto.addColumn(columna1);
        }    
    
        JScrollPane scrollPane = new JScrollPane(tablaPersonas);//crea la tabla
        scrollPane.setBounds(40, 40, 1000, 100);//tamaño de la tabla
        panel.add(scrollPane);//se añade al panel

        
    }
    //metodo que actualiza la tabla 
    public void actualizarTabla() {
        
        DefaultTableModel modelo = (DefaultTableModel) tablaPersonas.getModel();
        
        int filas = tablaPersonas.getRowCount();
        
        for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        
        List<personas> lista = this.ventana.getAspirantes().getListaPersona();
        
        for (personas persona : lista) {//datos
             Object[] fila = {persona.getImagen(), persona.getNombre(), persona.getApellido(),persona.getcedula(),persona.getCorreo(), persona.getProfesion().toString(),persona.getFechaDeNacimiento(),persona.getEdad()};
                tablaDefecto.addRow(fila);
             
        }
    }

}
