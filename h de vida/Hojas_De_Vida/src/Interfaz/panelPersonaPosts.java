package Interfaz;

import Logica.personas;
import Logica.Profesion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import org.jdesktop.swingx.JXDatePicker;

/**
 * @author Jorge Duran
 * @author Albert Charry
 */
public class panelPersonaPosts extends JPanel implements ActionListener, ItemListener{
    
    //variables para el panel
    JLabel labelNombre, labelApellido, labelCedula, labelImagen, labelCorreo, labelProfesion, LMImagen, labelGenero, labelfechaDeNacimiento;
    JTextField textNombre, textApellido, textCedula, JTImagen, textCorreo;
    JComboBox cajaProfesion;
    JButton botonGuardar, botonBuscar, botonValidar, botonVer;
    JCheckBox cajaSexoHombre, cajaSexoMujer;
    ButtonGroup cajaGeneroVarios; 
    
    private personas persona;
    private List<personas> listaPersona;
    private Profesion profesion;
    private ventanaPrincipal ventana;
    private String correo;
    private ImageIcon imagenPorDefecto;
    private verPersona dialogo;
    JXDatePicker calendario;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private verPersona dialogo1;
    
    
    //constructor
    public panelPersonaPosts(ventanaPrincipal ventana){
        this.ventana = ventana;
        setBorder(BorderFactory.createTitledBorder("Registrar Persona"));
        listaPersona = new ArrayList<personas>();
        setLayout(null);
          
        //label con sus nombres
        labelImagen = new JLabel ("Foto: ");
        labelNombre = new JLabel ("Nombre: ");
        labelApellido = new JLabel ("Apellido: ");
        labelCedula = new JLabel ("Cedula: ");
        labelCorreo =  new JLabel ("Correo: ");
        labelProfesion = new JLabel ("Profesion: ");
        labelGenero = new JLabel ("Genero: ");
        labelfechaDeNacimiento = new JLabel("Fecha Nacimiento: ");
        
        //boton
        cajaGeneroVarios =  new ButtonGroup();
        //check box genero
        cajaSexoHombre = new JCheckBox("Hombre");
        cajaSexoHombre.setBounds(450, 174, 100, 20);
        cajaSexoHombre.addItemListener(this);
        cajaSexoMujer = new JCheckBox("Mujer");
        cajaSexoMujer.setBounds(550, 174, 100, 20);
        cajaSexoMujer.addItemListener(this);
        
        cajaGeneroVarios.add(cajaSexoHombre);
        cajaGeneroVarios.add(cajaSexoMujer);
        
        add(cajaSexoHombre);
        add(cajaSexoMujer);
        
        labelImagen.setBounds(50, 226, 100, 20);
        add(labelImagen);  
        labelNombre.setBounds(400, 56, 100, 20);
        add(labelNombre);
        labelApellido.setBounds(400, 78, 100, 20);
        add(labelApellido);
        labelCedula.setBounds(400, 102, 100, 20);
        add(labelCedula);
        labelCorreo.setBounds(400, 126, 100, 20);
        add(labelCorreo);
        labelProfesion.setBounds(400, 150, 100, 20);
        add(labelProfesion);
        labelGenero.setBounds(400, 174, 100, 20);
        add(labelGenero);
        labelfechaDeNacimiento.setBounds(400, 200, 150, 20);
        add(labelfechaDeNacimiento);
        
        JTImagen = new JTextField();
        JTImagen.setBounds(90, 226, 150, 20);
        JTImagen.setFont( labelImagen.getFont( ).deriveFont( Font.PLAIN ) );
        add(JTImagen);
        textNombre = new JTextField();
        textNombre.setBounds(450, 54, 150, 20);
        add(textNombre);
        textApellido = new JTextField();
        textApellido.setBounds(450, 78, 150, 20);
        add(textApellido);
        textCedula = new JTextField();
        textCedula.setBounds(450, 102, 150, 20);
        add(textCedula);
        textCorreo = new JTextField();
        textCorreo.setBounds(450, 126, 150, 20);
        add(textCorreo);
        
        cajaProfesion = new JComboBox(profesion.values());
        cajaProfesion.setBounds(460, 150, 150, 20);
        add(cajaProfesion);
        
        //botones con sus ubicaciones
        botonBuscar = new JButton("Buscar Imagen");
        botonBuscar.setBounds(50, 200, 250, 20);
        botonBuscar.addActionListener(this);
        add(botonBuscar);
        
        botonGuardar = new JButton("Registar");
        botonGuardar.setBounds(450, 230, 130, 20);
        botonGuardar.addActionListener(this);
        add(botonGuardar);
        
        botonVer = new JButton ("Ver Personas");
        botonVer.setBounds(90, 260, 150, 30);
        botonVer.addActionListener(this);
        add(botonVer);
        //foto por defecto
        ImageIcon icono = new ImageIcon("./data/imagenes/fotodefecto.JPG");
        
        LMImagen = new JLabel( icono);
        LMImagen.setBorder( new LineBorder( Color.BLACK, 1 ) );
        LMImagen.setBounds(50, 30, 250, 150);
        add(LMImagen);
        //calendario
        calendario = new JXDatePicker();
        calendario.setDate(new Date());
        calendario.setFormats(formatter);
        calendario.setBounds(510, 200 , 150, 20);
        add(calendario);
        dialogo1 = new verPersona(this.ventana);
        
    }
 
    //validacion correo electronico
    private boolean validarDatosCorreo(String Correo) {
        Pattern pat = null;
        Matcher mat = null;
        
        pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");// aquie es la validacion del correp con los caracteres que puede tener el correo
        mat = pat.matcher(Correo);
        
        if(mat.find()){
            return true;
        }else {
            return false;
        }
        
    }
   //convierte la fecha a un string
    public String convertirFecha(){ 
       Date Fecha = calendario.getDate();
       String fechaS = formatter.format(Fecha);
       return fechaS;
    }
    //obtinene la edad con la fecha ingresada
    public int obtenerEdad(){
        Date FechaN = calendario.getDate();
        Date FechaA = new Date();
        
        int años = FechaA.getYear() - FechaN.getYear();
        int mes = FechaA.getMonth() - FechaN.getMonth();
        int dia = FechaA.getDay() - FechaN.getDay();
        
        if(mes < 0 || mes == 0 && dia <0){
            años =  años - 1;
        }
        return años;
    }
    
    //obtiene el string de la imagen
    public String getImagen( ){
        return JTImagen.getText();
        
    }
    
    public double getCedula(){
        long Cedula =  Long.parseLong(textCedula.getText()); //parcea el string a long       
        return Cedula;
    }
    public String getNombre(){
        return textNombre.getText();//obtiene el nombre
    }
    
    public String getApellido(){
        return textApellido.getText();//obtiene el apellido
    }
    public String getCorreo(){
        return textCorreo.getText();//obtiene el correo
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {//Busca y carga la imagen junto a la direccion
        if(botonBuscar == e.getSource()){
            JFileChooser fc = new JFileChooser( "./data/imagenes" );
            fc.setDialogTitle( "Imagen del personaPost" );
            int resultado = fc.showOpenDialog( this );
            if( resultado == JFileChooser.APPROVE_OPTION ){
                File archivo = fc.getSelectedFile( );
                String strArchivo = archivo.getAbsolutePath( );
                String strCarpetaImagenes = new File( "data/imagenes" ).getAbsolutePath( );

                if( strArchivo.startsWith( strCarpetaImagenes ) ){
                    JTImagen.setText( "data/imagenes/" + archivo.getName( ) );
                    ImageIcon icono = new ImageIcon(archivo.toString());
                    Icon ico = new ImageIcon(icono.getImage().getScaledInstance(LMImagen.getWidth(),LMImagen.getHeight(), Image.SCALE_DEFAULT));
                    LMImagen.setIcon(ico);
                    
                }else{
                    JOptionPane.showMessageDialog( this, "La imagen debe estar en la carpeta " + strCarpetaImagenes );
                }
                //guarda la imagen seleccionada despues de uqe valida todos los datos 
            }
        }if(botonGuardar == e.getSource()){
            if(validarDatosPersona() &&  validarDatosCorreo(textCorreo.getText())){
            persona = new personas(textNombre.getText(),textApellido.getText(), JTImagen.getText(), (long) getCedula(),textCorreo.getText(),((Profesion) cajaProfesion.getSelectedItem()),convertirFecha(), obtenerEdad());
            listaPersona.add(persona);
            JOptionPane.showMessageDialog(null, "Persona Creada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            vaciarDatos();
            
           }else{
               JOptionPane.showMessageDialog(this, "email invalido", "intente de nuevo", JOptionPane.INFORMATION_MESSAGE);
                labelCorreo.setForeground(Color.red);
                textCorreo.requestFocus();
           }
            Date dateCalendar = calendario.getDate();
            
          
        }if(botonVer == e.getSource()){
            dialogo1.actualizarTabla();
            dialogo1.setVisible(true);
            
        }      
            
    }

    //limpia los datos para un nuevo ingreso
    private void vaciarDatos() {
        
        ImageIcon icono2 =  new ImageIcon("./data/imagenes/fotodefecto.JPG");
        LMImagen.setIcon(icono2);
        JTImagen.setText("");
        labelImagen.setForeground(Color.BLACK);
        textNombre.setText("");
        labelNombre.setForeground(Color.BLACK);
        textApellido.setText("");
        labelApellido.setForeground(Color.BLACK);
        textCedula.setText("");
        labelCedula.setForeground(Color.BLACK);
        textCorreo.setText("");
        labelCorreo.setForeground(Color.BLACK);
        cajaProfesion.setSelectedIndex(0);
        
    }
    //hace la validacion de todos los datos con un booleano si es verdadero o falso
    private boolean validarDatosPersona() {
        if(textNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre.", "Mensaje", JOptionPane.ERROR_MESSAGE);
            labelNombre.setForeground(Color.red);
            return false;
        } else if(cajaProfesion.getSelectedItem() == profesion.SELECCIONE) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un genero.", "Mensaje", JOptionPane.ERROR_MESSAGE);
            return false;        
        }  else if(textApellido.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe ingresar el Apellido.", "Mensaje", JOptionPane.ERROR_MESSAGE);
            labelApellido.setForeground(Color.red);
        } else if(textCedula.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe ingresar el numero de Cedula ", "Mensaje", JOptionPane.ERROR_MESSAGE);
            labelCedula.setForeground(Color.red);            
        }else if (textCorreo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe ingresar un correo.", "Mensaje", JOptionPane.ERROR_MESSAGE);
            labelCorreo.setForeground(Color.red);
        }
        
        return true;
    }
    
    //obtiene todos los datos de la lista
    public List<personas> getListaPersona() {
        return listaPersona;
    }
    //activa el cuadro para el genero
    @Override
    public void itemStateChanged(ItemEvent ie) {
        if(cajaSexoHombre == ie.getSource()){
            if(cajaSexoHombre == ie.getSource()){
                System.out.println("Selecciono Hombre");
                }
            else{
                System.out.println("Cambio Hombre");
                }
        
        }
        if(cajaSexoMujer == ie.getSource()){
            if(cajaSexoMujer == ie.getSource()){
                System.out.println("Seleciono Mujer");
                }else{
                System.out.println("Cambio Mujer");
                }
        }
    }

}
