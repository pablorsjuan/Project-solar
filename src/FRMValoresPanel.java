import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FRMValoresPanel extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static final int tile=16;
    static int height=tile*2, vWidth=tile*94, vHeight=tile*50;
    private static int cantPnls=0;
    private static boolean primeraVez=true;
    private static final int horas=12; //Horas de luz diaria
    static JButton btnRegresar, btnGuardar;
    static ImageIcon imgRegresar;
    static JPanel jPanel;
    static JLabel llNoti, llCantPnls, llVatios, llPrecioPanel;
    static JTextField txCantPnls, txVatios, txPrecioPanel;
    //todo############-Action Listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
            llNoti.setText("");
        }
        if (e.getSource().equals(btnGuardar)){
            setCantPnls(Integer.parseInt(txCantPnls.getText()));
            PanelSolar.setVatios(Integer.parseInt(txVatios.getText()));
            PanelSolar.setPrecio(Long.parseLong(txPrecioPanel.getText()));
            llNoti.setText("Dato guardado exitosamente");
            FRMInicio.llPnlsSolares.setText("Cantidad de paneles solares: "+FRMValoresPanel.getCantPnls());
        }
    }
    //todo############-Constructor-############
    public FRMValoresPanel(){
        //Creación de elementos
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(tile,tile,tile*3,height);
        btnRegresar.setBorder(BorderFactory.createBevelBorder(1, Color.DARK_GRAY,Color.WHITE));
        btnRegresar.addActionListener(this);
        btnGuardar=new JButton("Guardar");
        btnGuardar.setBounds(tile*12,tile*4,tile*8,height);
        btnGuardar.setBackground(Color.cyan);
        btnGuardar.setBorder(BorderFactory.createBevelBorder(1,Color.blue,Color.white));
        btnGuardar.addActionListener(this);
        llNoti=new JLabel("",SwingConstants.CENTER);
        llNoti.setBounds(tile*2,tile*7,tile*28,height);
        llCantPnls=new JLabel("Cantidad de paneles solares: ",SwingConstants.CENTER);
        llCantPnls.setBounds(tile*2,tile*10,tile*13,height);
        llCantPnls.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txCantPnls=new JTextField("1");
        txCantPnls.setBounds(tile*16,tile*10,tile*14,height);
        txCantPnls.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        llVatios=new JLabel("Cantidad de vatios: ");
        llVatios.setBounds(tile*2,tile*13,tile*13,height);
        llVatios.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txVatios=new JTextField("10");
        txVatios.setBounds(tile*16,tile*13,tile*14,height);
        txVatios.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        llPrecioPanel=new JLabel("Precio del panel: ");
        llPrecioPanel.setBounds(tile*2,tile*16,tile*13,height);
        llPrecioPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txPrecioPanel=new JTextField("700000");
        txPrecioPanel.setBounds(tile*16,tile*16,tile*14,height);
        txPrecioPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));

        jPanel=new JPanel();
        jPanel.setBounds(tile,tile*3,tile*30,tile*44);
        jPanel.setBackground(Color.lightGray);
        jPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3)
                ,"Modificar electrodomésticos"));

        //Añadir elementos
        add(btnRegresar);
        add(btnGuardar);
        add(llNoti);
        add(llCantPnls);
        add(txCantPnls);
        add(llVatios);
        add(txVatios);
        //----------
        add(jPanel);


        //Creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Editar valores del panel solar");
        setSize(vWidth,vHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);
    }
    public static void vaciarTPanel(){
        for (int i = 0; i < 6; i++) {
            FRMInicio.modelPanel.removeRow(0);
        }
    }
    public static void llenarTablaPanel(){
        double kWh= (double) PanelSolar.getVatios()/1000*horas;
        FRMInicio.modelPanel.addRow(new Object[] {"kWh promedio por panel solar: "+kWh});
        FRMInicio.modelPanel.addRow(new Object[] {"Costo promedio de un panel solar: "+PanelSolar.getPrecio()});
        FRMInicio.modelPanel.addRow(new Object[] {kWh*getCantPnls()+" kWh por "+getCantPnls()+" paneles solares"});
        FRMInicio.modelPanel.addRow(new Object[] {"Costo a favor por día: "+Math.round(kWh*getCantPnls()*FRMValoresResult.getCostoKWH())+" pesos"});
        FRMInicio.modelPanel.addRow(new Object[] {"Costo a favor por mes: "+Math.round(kWh*getCantPnls()*FRMValoresResult.getCostoKWH()*30)+" pesos"});
        FRMInicio.modelPanel.addRow(new Object[] {"Costo a favor por año: "+Math.round(kWh*getCantPnls()*FRMValoresResult.getCostoKWH()*365)+" pesos"});
    }
    public static int getCantPnls() {return cantPnls;}
    public static void setCantPnls(int cantPnls) {FRMValoresPanel.cantPnls = cantPnls;}
    public static boolean isPrimeraVez() {return primeraVez;}
    public static void setPrimeraVez(boolean primeraVez) {FRMValoresPanel.primeraVez = primeraVez;}
    //kWp promedio de un panel solar: 1,8 kWh
    /*Potencia nominal
    * Ep=r*Ens*Pc
    * (E_p) es la energía total producida (en kWh).
    * (r) es el rendimiento de la instalación (porcentaje).
    * (E_{ns}) es la radiación solar de la zona (en kWh/kWp)
    *       8AM: 87 W/m**2
    *       12AM: 778 W/m**2
    *       6PM: 606 W/m**2
    * (P_C) es la potencia pico del panel que deseas comprar
    *       105 kWp.
    *
    * 10 y 15 kw por hora -> Convierto esto a un promedio, luego los kWh y los multiplico por el costo
    * me da un resultado en pesos, el cual se resta al costo total
    * Un Panel solar produce en promedio 2 kWh de electricidad diaria*/
}
