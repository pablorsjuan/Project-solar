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
    static int height=tile*2, vWidth=tile*94, vHeight=tile*50; //vWidth = tile*64 , vHeight = tile*44
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
    static JButton btnElecs, btnResult, btnPanel, btnActualizar, btnActivarP;
    static JTable tablaElecs, tablaResult, tablaPanel;
    static DefaultTableModel modelElecs, modelResult, modelPanel;
    static JScrollPane scpnElecs, scpnResult, scpnPanel;
    static JLabel llActualizar, llepe, llEstrato, llCantElecs, llCostoKWH, llPnlsSolares, llFactura;
    static JPanel jpanelDatos, jPanelEyP, jpanleResumen;
    static JTextArea txaResumen;
    //todo############-Action Listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnActualizar)){
            Hilo hilo =new Hilo();
            hilo.start();
            FRMValoresResult.vaciarResult();
            FRMValoresResult.llenarTablaResult();
            llPnlsSolares.setText("Cantidad de paneles solares: "+FRMValoresPanel.getCantPnls());
            FRMValoresPanel.vaciarTPanel();
            FRMValoresPanel.llenarTablaPanel();
        }
        if (e.getSource().equals(btnActivarP)){
            if (FRMValoresPanel.isPrimeraVez()){
                FRMValoresPanel.setCantPnls(1);
                FRMValoresPanel.setPrimeraVez(false);
                llPnlsSolares.setText("Cantidad de paneles solares: "+FRMValoresPanel.getCantPnls());
                FRMValoresPanel.llenarTablaPanel();
            }
            if (scpnPanel.isVisible() && btnPanel.isVisible()){
                btnActivarP.setText("Panel solar: OFF");
                btnActivarP.setBackground(Color.red);
                scpnPanel.setVisible(false);
                btnPanel.setVisible(false);
            } else {
                btnActivarP.setText("Panel solar: ON");
                btnActivarP.setBackground(Color.green);
                scpnPanel.setVisible(true);
                btnPanel.setVisible(true);
            }
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
    public FRMInicio() {
        //Creación de elementos
        btnElecs = new JButton("Modificar");
        btnElecs.setBounds(tile*47, tile*21, tile*8, height);
        btnElecs.setBorder(BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.WHITE));
        btnElecs.addActionListener(this);
        btnResult = new JButton("Editar valores");
        btnResult.setBounds(tile*11, tile*43, tile*9, height);
        btnResult.setBorder(BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.WHITE));
        btnResult.addActionListener(this);
        btnPanel=new JButton("Modificar");
        btnPanel.setBounds(tile*47,tile*43,tile*8,height);
        btnPanel.setBorder(BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.WHITE));
        btnPanel.addActionListener(this);
        btnPanel.setVisible(false);
        btnActualizar=new JButton("Actualizar");
        btnActualizar.setBounds(tile,tile,tile*8,height);
        btnActualizar.setBorder(BorderFactory.createBevelBorder(1,Color.blue,Color.white));
        btnActualizar.addActionListener(this);
        btnActualizar.setBackground(Color.CYAN);
        btnActivarP=new JButton("Panel solar: OFF");
        btnActivarP.setBackground(Color.red);
        btnActivarP.setBounds(tile*33,tile*24,tile*8,height);
        btnActivarP.setBorder(BorderFactory.createBevelBorder(1, Color.darkGray, Color.WHITE));
        btnActivarP.addActionListener(this);
        llActualizar=new JLabel("Se actualizan los datos editados",SwingConstants.CENTER);
        llActualizar.setBounds(tile*10,tile,tile*21,height);
        llActualizar.setBorder(BorderFactory.createLineBorder(Color.CYAN,3,true));
        llepe =new JLabel("",SwingConstants.CENTER);
        llepe.setBounds(tile*2,tile*4,tile*28,height);
        llepe.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        llEstrato=new JLabel("",SwingConstants.CENTER);
        llEstrato.setBounds(tile*2,tile*7,tile*28,height);
        llEstrato.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        llCantElecs=new JLabel("",SwingConstants.CENTER);
        llCantElecs.setBounds(tile*2,tile*10,tile*28,height);
        llCantElecs.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        llCostoKWH=new JLabel("",SwingConstants.CENTER);
        llCostoKWH.setBounds(tile*2,tile*13,tile*28,height);
        llCostoKWH.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        llFactura=new JLabel("",SwingConstants.CENTER);
        llFactura.setBounds(tile*2,tile*16,tile*28,height);
        llFactura.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        llPnlsSolares=new JLabel("Cantidad de paneles solares: "+FRMValoresPanel.getCantPnls());
        llPnlsSolares.setBounds(tile*42,tile*24,tile*29,height);
        llPnlsSolares.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));

        txaResumen=new JTextArea();
        txaResumen.setText("En el mundo actual, se requiere usar energías \nlimpias y renovables" +
                " donde los paneles solares \ndemuestran ser una gran opción, no solo para \nempresas sino también para las personas." +
                "\n\nFuncionamiento de las tablas:\n•La primera tambla, cambia datos importantes " +
                "\npara poder calcular el consumo y el coste." +
                "\n\n•La tabla electrodomesticos muestra los \nelectrodomesticos que podría tener una persona \nde estrato 2 en su casa" +
                ", sientase libre de \nmodificar los datos." +
                "\n\n•La tabla de los paneles solares arroja unos datos \npara calcular como sería su ahorro usando una \ncantidad de " +
                "paneles solares.");
        txaResumen.setBounds(tile*74,tile*2,tile*17,tile*24);
        txaResumen.setBorder(BorderFactory.createLineBorder(Color.white,5,true));

        jpanelDatos =new JPanel();
        jpanelDatos.setBounds(tile,tile*3,tile*30,tile*43);
        jpanelDatos.setBackground(Color.lightGray);
        jpanelDatos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3,true)
                ,"Datos importantes"));
        jPanelEyP=new JPanel();
        jPanelEyP.setBounds(tile*32,tile,tile*40,tile*45);
        jPanelEyP.setBackground(Color.lightGray);
        jPanelEyP.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3,true)
                ,"Electrodomésticos y paneles"));

        jpanleResumen=new JPanel();
        jpanleResumen.setBounds(tile*73,tile,tile*19,tile*45);
        jpanleResumen.setBackground(Color.lightGray);
        jpanleResumen.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3,true)
                ,"Indicaciones"));

        //Creación de tablaElecs
        modelElecs=new DefaultTableModel();
        tablaElecs=new JTable(modelElecs);
        scpnElecs=new JScrollPane(tablaElecs);
        scpnElecs.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        modelElecs.addColumn("Electrodoméstico");
        modelElecs.addColumn("Vatios");
        modelElecs.addColumn("Horas");
        modelElecs.addColumn("Minutos");
        modelElecs.addColumn("Cantidad");
        modelElecs.addColumn("KWh");
        tablaElecs.setRowHeight(height);
        tablaElecs.setGridColor(Color.gray);
        tablaElecs.setBackground(Color.darkGray);
        tablaElecs.setForeground(Color.WHITE);
        try {
            FRMValoresElecs.llenarTablaElecs();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        scpnElecs.setLocation(tile*33,tile*2);
        scpnElecs.setSize(tile*38,tile*18);

        //Creación de tablaResult
        modelResult =new DefaultTableModel();
        tablaResult =new JTable(modelResult);
        tablaResult.setGridColor(Color.gray);
        tablaResult.setBackground(Color.darkGray);
        tablaResult.setForeground(Color.WHITE);
        tablaResult.setRowHeight(height);
        scpnResult =new JScrollPane(tablaResult);
        scpnResult.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        modelResult.addColumn("Otros datos relevantes");
        FRMValoresResult.llenarTablaResult();
        scpnResult.setLocation(tile*2,tile*19);
        scpnResult.setSize(tile*28,tile*23);

        //Creación de tablaPanel
        modelPanel=new DefaultTableModel();
        tablaPanel=new JTable(modelPanel);
        tablaPanel.setRowHeight(height);
        tablaPanel.setBackground(Color.darkGray);
        tablaPanel.setForeground(Color.green);
        tablaPanel.setGridColor(Color.gray);
        scpnPanel=new JScrollPane(tablaPanel);
        modelPanel.addColumn("Resultados con panel solar");
        scpnPanel.setLocation(tile*33,tile*27);
        scpnPanel.setSize(tile*38,tile*15);
        scpnPanel.setBorder(BorderFactory.createLineBorder(Color.green,3,true));
        scpnPanel.setVisible(false);

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
        add(llepe);
        add(llEstrato);
        add(llCantElecs);
        add(llCostoKWH);
        add(btnActivarP);
        add(llPnlsSolares);
        add(llFactura);
        add(txaResumen);
        //----------
        add(jpanelDatos);
        add(jPanelEyP);
        add(jpanleResumen);

        //Creación de ventana
        setLayout(null);
        setJMenuBar(JmenuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inicio");
        setSize(vWidth,vHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
}
