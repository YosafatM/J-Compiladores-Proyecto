package Vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import Configuracion.Configuracion;
import Configuracion.Linea;
import Compilador.Parser;

public class VentanaPrincipal extends JFrame {
    private final JTextArea areaDeCodigo;
    private final PanelDeDibujo panelDeDibujo;
    private final JButton ejecutar, siguiente;
    Parser parser;
    boolean modoDebug;
    
    public VentanaPrincipal() {
        super("Logos");

        modoDebug = false;
        parser = new Parser();
        parser.insertarInstrucciones();
        
        areaDeCodigo = new JTextArea(20,20);
        JScrollPane scrollCodigo = new JScrollPane(areaDeCodigo);
        scrollCodigo.setBounds(10,10,250,550);
        add(scrollCodigo);
        
        panelDeDibujo = new PanelDeDibujo();
        panelDeDibujo.setBounds(270,10,Propiedades.PANEL_DE_DIBUJO_ANCHO,Propiedades.PANEL_DE_DIBUJO_LARGO);
        panelDeDibujo.setBackground(Color.GRAY);
        add(panelDeDibujo);
        
        ImageIcon icon1 = new ImageIcon(System.getProperty("user.dir") + "/Play.png");
        Image img1 = icon1.getImage();
        Image imgaux = img1.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        ejecutar = new JButton(new ImageIcon(imgaux));
        ejecutar.setBounds(10,570,50,40);
        ejecutar.addActionListener(event -> {
            parser.limpiar();
            if(parser.compilar(areaDeCodigo.getText()))
                panelDeDibujo.setConfiguracion(parser.ejecutar());
            else {
                parser = new Parser();
                parser.insertarInstrucciones();
                panelDeDibujo.setConfiguracion(parser.getConfiguracion());
            }
            panelDeDibujo.repaint();
        });
        add(ejecutar);
        
        ImageIcon icon2 = new ImageIcon(System.getProperty("user.dir") + "/Debug.png");
        Image img2 = icon2.getImage();
        Image imgaux2 = img2.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        JButton debug = new JButton(new ImageIcon(imgaux2));
        debug.setBounds(150,570,50,40); 
        debug.addActionListener(event -> {
            parser.limpiar();
            if(!modoDebug){
                if(parser.compilar(areaDeCodigo.getText())){
                    panelDeDibujo.setConfiguracion(parser.getConfiguracion());
                    cambiarDebug();
                }
                else{
                    parser = new Parser();
                    parser.insertarInstrucciones();
                    panelDeDibujo.setConfiguracion(parser.getConfiguracion());
                }
            }
            else{
                cambiarDebug();
            }
            panelDeDibujo.repaint();
        });
        add(debug);
        
        ImageIcon icon3 = new ImageIcon(System.getProperty("user.dir") + "/Next.png");
        Image img3 = icon3.getImage();
        Image imgaux3 = img3.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        siguiente = new JButton(new ImageIcon(imgaux3));
        siguiente.setBounds(210,570,50,40);
        siguiente.addActionListener(event -> {
            parser.ejecutarSiguiente();
            panelDeDibujo.setConfiguracion(parser.getConfiguracion());
            panelDeDibujo.repaint();
        });
        siguiente.setEnabled(false);
        add(siguiente); 
        setLayout(null);
        setBounds(50,50,1000,700);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
    }
    
    private void cambiarDebug(){
        if(!modoDebug){
            areaDeCodigo.setEnabled(false);
            ejecutar.setEnabled(false);
            siguiente.setEnabled(true);
        }
        else{
           areaDeCodigo.setEnabled(true);
            ejecutar.setEnabled(true);
            siguiente.setEnabled(false); 
        }
        modoDebug = !modoDebug;
    }
}
