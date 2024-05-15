import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FRMostrarLogs extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static final int tile=16;
    static int height=tile*2, vWidth=16*64, vHeight=16*44;
    static JButton btnRegresar;
    static ImageIcon imgRegresar;
    static JTable tabla, tablaActividad;
    static DefaultTableModel model, modelActividad;
    static JScrollPane scrollpane, scpaneActividad;
    //todo############-Action listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
        }
    }
    //todo############-Constructor-############
    public FRMostrarLogs(){
        //Creación de elementos
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(tile,tile,tile*3,height);
        btnRegresar.addActionListener(this);

        //Creación de tabla
        model = new DefaultTableModel();
        tabla = new JTable(model);
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Usuario");
        scrollpane = new JScrollPane(tabla);
        scrollpane.setLocation(tile,tile*4);
        scrollpane.setSize(tile*30, tile*37);

        modelActividad=new DefaultTableModel();
        tablaActividad=new JTable(modelActividad);
        modelActividad.addColumn("Usuario");
        modelActividad.addColumn("Actividad en el mes");
        scpaneActividad=new JScrollPane(tablaActividad);
        scpaneActividad.setLocation(vWidth/2,tile*4);
        scpaneActividad.setSize(tile*30,tile*16);

        //Tabla
        LeerTXT.leer_escribir("login.log.txt");

        //Añadir elementos
        add(btnRegresar);
        add(scrollpane, BorderLayout.CENTER);
        add(scpaneActividad, BorderLayout.CENTER);

        //creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Logs");
        setSize(vWidth,vHeight);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
}
