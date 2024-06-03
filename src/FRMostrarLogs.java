import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FRMostrarLogs extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static final int tile=16;
    static int height=tile*2, vWidth=tile*94, vHeight=tile*50;
    static JButton btnRegresar, btnBuscarLog;
    static ImageIcon imgRegresar;
    static JTable tabla, tablaActividad;
    static DefaultTableModel model, modelActividad;
    static JScrollPane scrollpane, scpaneActividad;
    static JLabel llBuscarLog, llNoti;
    static JTextField txBuscarLog;
    static JPanel jPanel;
    //todo############-Action listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource().equals(btnBuscarLog)){
            llNoti.setText("Fecha encontrada");
            llenarTablaLogs(txBuscarLog.getText());
        }
    }
    //todo############-Constructor-############
    public FRMostrarLogs(){
        //Creación de elementos
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(tile,tile,tile*3,height);
        btnRegresar.addActionListener(this);

        llBuscarLog=new JLabel("Ingrese la fecha",SwingConstants.CENTER);
        llBuscarLog.setBounds(tile*33,tile*4,tile*28,height);
        txBuscarLog=new JTextField("AÑO-MES-DIA");
        txBuscarLog.setBounds(tile*33,tile*7,tile*28,height);
        txBuscarLog.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        btnBuscarLog=new JButton("Buscar");
        btnBuscarLog.setBounds(tile*42,tile*10,tile*10,height);
        btnBuscarLog.setBackground(Color.CYAN);
        btnBuscarLog.setBorder(BorderFactory.createBevelBorder(1,Color.blue,Color.white));
        btnBuscarLog.addActionListener(this);
        llNoti=new JLabel("",SwingConstants.CENTER);
        llNoti.setBounds(tile*33,tile*13,tile*28,height);

        jPanel=new JPanel();
        jPanel.setBounds(tile*32,tile*3,tile*30,tile*30);
        jPanel.setBackground(Color.lightGray);
        jPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3,true)
                ,"Buscar logs"));

        //Creación de tabla
        model = new DefaultTableModel();
        tabla = new JTable(model);
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Usuario");
        scrollpane = new JScrollPane(tabla);
        scrollpane.setLocation(tile,tile*4);
        scrollpane.setSize(tile*30, tile*42);
        scrollpane.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        tabla.setRowHeight(height);
        tabla.setGridColor(Color.gray);
        tabla.setBackground(Color.darkGray);
        tabla.setForeground(Color.WHITE);

        modelActividad=new DefaultTableModel();
        tablaActividad=new JTable(modelActividad);
        modelActividad.addColumn("Logs del día");
        scpaneActividad=new JScrollPane(tablaActividad);
        scpaneActividad.setLocation(tile*33,tile*16);
        scpaneActividad.setSize(tile*28,tile*16);
        scpaneActividad.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        tablaActividad.setRowHeight(height);
        tablaActividad.setGridColor(Color.gray);
        tablaActividad.setBackground(Color.darkGray);
        tablaActividad.setForeground(Color.WHITE);

        //Tabla
        LeerTXT.leer_escribir("login.log.txt");

        //Añadir elementos
        add(btnRegresar);
        add(scrollpane, BorderLayout.CENTER);
        add(scpaneActividad, BorderLayout.CENTER);
        add(llBuscarLog);
        add(txBuscarLog);
        add(llNoti);
        add(btnBuscarLog);
        add(jPanel);

        //creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Logs");
        setSize(vWidth,vHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    public static void llenarTablaLogs(String fecha){
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if (tabla.getValueAt(i,0).equals(fecha)){
                modelActividad.addRow(new Object [] {tabla.getValueAt(i,0)
                        +" "+tabla.getValueAt(i,1)
                        +" "+tabla.getValueAt(i,2)});
            }
        }
    }
}
