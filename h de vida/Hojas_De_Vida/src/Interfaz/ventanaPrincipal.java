package Interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Jorge Duran
 * @author Albert Charry
 */

//Clase que contiene toda la interfaz
public class ventanaPrincipal extends JFrame  implements ActionListener{
    private verPersona dialogoPersonas;//LLama el dialogo
    private panelPersonaPosts panelDePersonas;//LLama para el registro
    private JMenuBar menuBarra;//Menu de la ventana
    private JMenu archivoHistorial;//Guarda botones del historial en el menu de la ventana
    private JMenu archivoInfor;//Guarda botones de informacion en el menu de la ventana
    private JMenuItem nuevoProyecto;//Menu de las personas
    private JMenuItem nuevoArchi;
    private JMenuItem menuSalida;//Salir del programa
    private JMenuItem botonInfo;
    private static final String personasRegistradas = "Lista Aspirantes";//muestra las personas ingresadas
    private static final String AUTORES = "Autores";//etiqueta para autores
    private static final String SALIR = "Salir";//etiqueta para salir
    
    //constructor de la ventana principal
    public ventanaPrincipal() {//vantana principal
        setSize(720, 365);//tamaño
        setLocationRelativeTo(null);//Centrar
        setTitle("Registro Hojas de Vida");//titulo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//cerrar el programa
        setResizable(false);//bloquea la ampliada de la pantalla
        setLayout(null);//actualiza el panel
        
        panelDePersonas = new panelPersonaPosts(this);//panel de las personas
        panelDePersonas.setBounds(0, 0, 700, 300);//ubicacion
        add(panelDePersonas);//se añade
       
        menuBarra = new JMenuBar();//crea el menu de la barra  
        setJMenuBar(menuBarra);//añade
        
        archivoHistorial = new JMenu("Historial");//etiqueta del historial
        nuevoProyecto = new JMenuItem("Personas Registradas");//etiqueta de las personas
        nuevoProyecto.setActionCommand(personasRegistradas);//etiqueta ver personas
        nuevoProyecto.addActionListener(this);//activa con el click
        dialogoPersonas = new verPersona(this);//llama el dialogo

        menuSalida = new JMenuItem("Salir");//nombre de la etiqueta
        menuSalida.setActionCommand(SALIR);//activa el boton
        menuSalida.addActionListener(this);//activa
        
        archivoHistorial.add(nuevoProyecto);//se juntan todos los botones y se añaden a uno solo
        archivoHistorial.add(menuSalida);
        menuBarra.add(archivoHistorial);
        
        archivoInfor = new JMenu("Información");//etiqueta
        botonInfo = new JMenuItem("Informacion");
        botonInfo.setActionCommand(AUTORES);
        botonInfo.addActionListener(this);//activacion
        archivoInfor.add(botonInfo);
        menuBarra.add(archivoInfor);//se añade a la barra
        
        setVisible(true);//se hace visible la ventana
        
    }
    
    
    //metodo que activa boton desde la ventana principal
    public panelPersonaPosts getAspirantes() {
        return panelDePersonas;
    }
    //escucha el evento del click
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals(personasRegistradas)) {//muestra las personas registradas con una ventana 
            
            dialogoPersonas.actualizarTabla();//llama la ventana con datos actualizados 
            dialogoPersonas.setVisible(true);
            
        } else if(e.getActionCommand().equals(AUTORES)) {//muestra los datos de ls autores
            
               JOptionPane.showMessageDialog(this, "Programacion II\nAutores: \nJorge Andres Duran    Cod:461215120\nAlbert Charry    Cod:461215116\nUniversidad De Cundinamarca");//imprime informacion
               
            }else if(e.getActionCommand().equals(SALIR)) {//boton para salir
                
            System.exit(0);//sale del programa
            
            }
        }

}
