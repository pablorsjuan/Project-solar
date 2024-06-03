import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FRMValoresResult extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static final int tile=16;
    static int height=tile*2, vWidth=tile*94, vHeight=tile*50;
    private static double costoKWH=47.04;
    private static String empresa="EPM";
    private static int estrato=2;
    static JButton btnRegresar, btnGuardar;
    static ImageIcon imgRegresar;
    static JPanel jPanel, jPanelInfo;
    static JTextArea txaInfo;
    static JLabel llEmpresa, llEstrato, llCostoKWH, llNoti;
    static JTextField txEmpresa, txEstrato, txCostoKWH;
    //todo############-Action Listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
            llNoti.setText("");
        }
        if (e.getSource().equals(btnGuardar)){
            llNoti.setText("Datos editados exitosamente");
            setEmpresa(txEmpresa.getText());
            setEstrato(Integer.parseInt(txEstrato.getText()));
            setCostoKWH(Double.parseDouble(txCostoKWH.getText()));
            FRMInicio.llepe.setText("Empresa prestadora de energía: "+getEmpresa());
            FRMInicio.llEstrato.setText("Estrato: "+getEstrato());
            FRMInicio.llCostoKWH.setText("Costo kWH (Para estrato "+getEstrato()+" y empresa "+getEmpresa()+"): "+getCostoKWH());
        }
    }
    //todo############-Constructor-############
    public FRMValoresResult(){
        //Creación de elementos
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(tile,tile,tile*3,height);
        btnRegresar.setBorder(BorderFactory.createBevelBorder(1, Color.DARK_GRAY,Color.WHITE));
        btnRegresar.addActionListener(this);
        llEmpresa=new JLabel("Empresa prestadora de energía:",SwingConstants.CENTER);
        llEmpresa.setBounds(tile*2,tile*10,tile*13,height);
        llEmpresa.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txEmpresa=new JTextField("EPM");
        txEmpresa.setBounds(tile*16,tile*10,tile*14,height);
        txEmpresa.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        llEstrato=new JLabel("Estrato:",SwingConstants.CENTER);
        llEstrato.setBounds(tile*2,tile*13,tile*13,height);
        llEstrato.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txEstrato=new JTextField("2");
        txEstrato.setBounds(tile*16,tile*13,tile*14,height);
        txEstrato.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        llCostoKWH=new JLabel("Costo kWh:",SwingConstants.CENTER);
        llCostoKWH.setBounds(tile*2,tile*16,tile*13,height);
        llCostoKWH.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txCostoKWH=new JTextField("47.04");
        txCostoKWH.setBounds(tile*16,tile*16,tile*14,height);
        txCostoKWH.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        btnGuardar=new JButton("Guardar");
        btnGuardar.setBounds(tile*12,tile*4,tile*8,height);
        btnGuardar.setBackground(Color.cyan);
        btnGuardar.setBorder(BorderFactory.createBevelBorder(1,Color.blue,Color.white));
        btnGuardar.addActionListener(this);
        llNoti=new JLabel("",SwingConstants.CENTER);
        llNoti.setBounds(tile*2,tile*7,tile*28,height);

        jPanel=new JPanel();
        jPanel.setBounds(tile,tile*3,tile*30,tile*44);
        jPanel.setBackground(Color.lightGray);
        jPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3)
                ,"Editar valores"));

        //Añadir elementos
        add(btnRegresar);
        add(llEmpresa);
        add(txEmpresa);
        add(llEstrato);
        add(txEstrato);
        add(llCostoKWH);
        add(txCostoKWH);
        add(btnGuardar);
        add(llNoti);
        //----------
        add(jPanel);

        //Creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Editar valores de los resultados");
        setSize(vWidth,vHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);
    }
    public static void vaciarResult(){
        for (int i = 0; i < 6; i++) {
            FRMInicio.modelResult.removeRow(0);
        }
    }
    public static void llenarTablaResult(){
        double kwh=0;
        for (int j = 0; j < FRMInicio.tablaElecs.getRowCount(); j++) {
            kwh=kwh+(float)FRMInicio.tablaElecs.getValueAt(j,5);
        }
        //Labels
        FRMInicio.llepe.setText("Empresa prestadora de energía: "+getEmpresa());
        FRMInicio.llEstrato.setText("Estrato: "+getEstrato());
        FRMInicio.llCantElecs.setText("Cantidad de objetos: "+Electrodomestico.getCantElects());
        FRMInicio.llCostoKWH.setText("Costo kWH (Para estrato "+getEstrato()+" y empresa "+getEmpresa()+"): "+getCostoKWH());
        FRMInicio.llFactura.setText("Costo total mensual: "+Math.round(kwh*getCostoKWH()*30)+" pesos");
        //Rows
        FRMInicio.modelResult.addRow(new Object[]{"kWh por día: "+Math.round(kwh)});
        FRMInicio.modelResult.addRow(new Object[]{"kWh por semana: "+Math.round(kwh*7)});
        FRMInicio.modelResult.addRow(new Object[]{"kWh por mes: "+Math.round(kwh*30)});
        FRMInicio.modelResult.addRow(new Object[]{"Costo kWh (nivel 1 - residencial): "+Math.round(getCostoKWH()*kwh*30)});
        FRMInicio.modelResult.addRow(new Object[]{"Costo kWh (nivel 2): "+Math.round(24.13*kwh*30)+" pesos"});
        FRMInicio.modelResult.addRow(new Object[]{"Costo kWh (nivel 3): "+Math.round(30.40*kwh*30)+" pesos"});
    }
    public static double getCostoKWH() {return costoKWH;}
    public static void setCostoKWH(double costoKWH) {FRMValoresResult.costoKWH = costoKWH;}
    public static String getEmpresa() {return empresa;}
    public static void setEmpresa(String empresa) {FRMValoresResult.empresa = empresa;}
    public static int getEstrato() {return estrato;}
    public static void setEstrato(int estrato) {
        if (estrato>6){
            llNoti.setForeground(Color.RED);
            llNoti.setText("Estrato no válido");
        }else {FRMValoresResult.estrato = estrato;}
    }
}
/*
* Estrato 1: 45,72 pesos
* Estrato 2: 21,94 pesos
* Estrato 3: 26,50 pesos
* Estrato 4: 0 pesos (No es subsidiado ni subsidia)
* Estrato 5: No sé
* Cada municipio impone su propia tarifa*/
