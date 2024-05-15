import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
public class FRMInicio extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static final int tile=16;
    static int height=tile*2, vWidth=16*64, vHeight=16*44;
    static String valores="Editar valores";
    static Connection cnx;
    static {
        try {
            cnx = Conectar.Conexion();
        } catch (ClassNotFoundException | IllegalAccessException | SQLException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
    static int optionRespuesta;
    static JMenuBar JmenuBar;
    static JMenu menuOpciones, menuAdmin;
    static JMenuItem miLogin, miSalir, miAdUsuarios, miAdLogs;
    static JButton btnElecs, btnResult, btnPanel, btnActualizar;
    static JTable tablaElecs, tablaResult, tablaPanel;
    static DefaultTableModel modelElecs, modelResult, modelPanel;
    static JScrollPane scpnElecs, scpnResult, scpnPanel;
    static JLabel llActualizar, llTexto;
    //todo############-Action Listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnActualizar)){
            FRMValoresResult.llenarTablaResult();
        }
        if (e.getSource().equals(btnElecs)){
            Iniciador.valoresElecsVentana.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource().equals(btnResult)){
            Iniciador.valoresResultVentana.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource().equals(btnPanel)){
            Iniciador.valoresPanelVentana.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource().equals(miLogin)){
            Iniciador.ventana.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource().equals(miSalir)){
            JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de que desea salir?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION);
            if (optionRespuesta == JOptionPane.YES_OPTION){
                System.exit(0);
                try {
                    cnx.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else {return;}
        }
        if (e.getSource().equals(miAdUsuarios)){
            Iniciador.mostrarUsVentana.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource().equals(miAdLogs)){
            Iniciador.mostrarLogsVentana.setVisible(true);
            this.setVisible(false);
        }
    }
    //todo############-Constructor-############
    public FRMInicio(){
        //Creación de elementos
        btnElecs=new JButton("Modificar");
        btnElecs.setBounds(tile*13,tile*37,tile*8,height);
        btnElecs.addActionListener(this);
        btnResult =new JButton(valores);
        btnResult.setBounds(tile*43,tile*18,tile*9,height);
        btnResult.addActionListener(this);
        btnPanel=new JButton(valores);
        btnPanel.setBounds(tile*43,tile*37,tile*9,height);
        btnPanel.addActionListener(this);
        btnActualizar=new JButton("Actualizar");
        btnActualizar.setBounds(tile,tile,tile*8,height);
        btnActualizar.addActionListener(this);
        btnActualizar.setBackground(Color.CYAN);
        llActualizar=new JLabel("Se actualizan los datos editados",SwingConstants.CENTER);
        llActualizar.setBounds(tile*10,tile,tile*21,height);
        llActualizar.setBorder(BorderFactory.createLineBorder(Color.CYAN,3,true));
        llTexto=new JLabel("Recuadro en proceso",SwingConstants.CENTER);
        llTexto.setBounds(tile,tile*4,tile*30,tile*16);
        llTexto.setBorder(BorderFactory.createLineBorder(Color.white,3,true));

        //Creación de tablaElecs
        modelElecs=new DefaultTableModel();
        tablaElecs=new JTable(modelElecs);
        scpnElecs=new JScrollPane(tablaElecs);
        modelElecs.addColumn("Electrodoméstico");
        modelElecs.addColumn("Vatios");
        modelElecs.addColumn("Horas");
        modelElecs.addColumn("Minutos");
        modelElecs.addColumn("KWh");
        tablaElecs.setRowHeight(height);
        tablaElecs.setBackground(Color.darkGray);
        tablaElecs.setForeground(Color.white);
        try {
            FRMValoresElecs.llenarTablaElecs();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        scpnElecs.setLocation(tile,tile*21);
        scpnElecs.setSize(tile*30,tile*16);

        //Creación de tablaResult
        modelResult =new DefaultTableModel();
        tablaResult =new JTable(modelResult);
        tablaResult.setBackground(Color.darkGray);
        tablaResult.setForeground(Color.white);
        tablaResult.setRowHeight(height);
        scpnResult =new JScrollPane(tablaResult);
        modelResult.addColumn("Resultados");
        FRMValoresResult.llenarTablaResult();
        scpnResult.setLocation(vWidth/2,tile);
        scpnResult.setSize(tile*30,tile*17);

        //Creación de tablaPanel
        modelPanel=new DefaultTableModel();
        tablaPanel=new JTable(modelPanel);
        tablaPanel.setRowHeight(height);
        tablaPanel.setBackground(Color.darkGray);
        tablaPanel.setForeground(Color.GREEN);
        scpnPanel=new JScrollPane(tablaPanel);
        modelPanel.addColumn("Resultados con panel solar");
        scpnPanel.setLocation(vWidth/2,tile*21);
        scpnPanel.setSize(tile*30,tile*16);
        FRMValoresPanel.llenarTablaPanel();

        //Menu bar
        JmenuBar =new JMenuBar();
        menuOpciones =new JMenu("Opciones");
        menuOpciones.setHorizontalAlignment(SwingConstants.CENTER);
        miLogin =new JMenuItem("Inicio sesión");
        miLogin.addActionListener(this);
        miSalir =new JMenuItem("Salir");
        miSalir.setForeground(Color.RED);
        miSalir.addActionListener(this);
        menuAdmin=new JMenu("Admin Power");
        menuAdmin.setHorizontalAlignment(SwingConstants.CENTER);
        miAdUsuarios=new JMenuItem("Usuarios");
        miAdUsuarios.addActionListener(this);
        miAdLogs=new JMenuItem("Logs");
        miAdLogs.addActionListener(this);
        menuOpciones.add(miLogin);
        menuOpciones.add(miSalir);
        menuAdmin.add(miAdUsuarios);
        menuAdmin.add(miAdLogs);
        JmenuBar.add(menuOpciones);
        JmenuBar.add(menuAdmin);

        //Añadir elementos
        add(scpnElecs, BorderLayout.CENTER);
        add(scpnResult, BorderLayout.CENTER);
        add(scpnPanel, BorderLayout.CENTER);
        add(btnElecs);
        add(btnResult);
        add(btnPanel);
        add(btnActualizar);
        add(llActualizar);
        add(llTexto);

        //Creación de ventana
        setLayout(null);
        setJMenuBar(JmenuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inicio");
        setSize(vWidth,vHeight);
        setLocationRelativeTo(null);
    }
    /*if (e.getSource().equals(btnGuardar)){
            float vatios= Float.parseFloat(txVatios.getText());
            float kW= vatios/1000;
            float horas= Float.parseFloat(txHoras.getText());
            int tiempo= Integer.parseInt(txTiempo.getText());
            float costoKWH= Float.parseFloat(txCostoKWH.getText());
            float total= (kW*horas)*costoKWH;
            llNotificacion.setText("Costo kWh: "+total+" pesos");
        }*/
}
