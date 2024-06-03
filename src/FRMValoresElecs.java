import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FRMValoresElecs extends JFrame implements ActionListener {
    //todo############-Atributos-############
    private static String nombre;
    private static int vatios, horasUso, minutosUso, cantidad;
    static final int tile=16;
    static int height=tile*2, vWidth=tile*94, vHeight=tile*50;
    static JButton btnRegresar, btnGuardar, btnEliminar;
    static ImageIcon imgRegresar;
    static JPanel jPanel;
    static JLabel llNoti,llNotiEl, llNombre, llVatios, llHorasUso, llMntsUso, llCant;
    static JTextField txNombre, txVatios, txHorasUso, txMntsUso, txCant, txEliminar;
    //todo############-Action Listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
            llNoti.setText("");
            llNotiEl.setText("");
        }
        if (e.getSource().equals(btnGuardar)){
            llNoti.setForeground(Color.black);
            llNoti.setText("Objeto añadido exitosamente");
            Electrodomestico.cantElects++;
            setNombre(txNombre.getText().toLowerCase());
            setVatios(Integer.parseInt(txVatios.getText()));
            setHorasUso(Integer.parseInt(txHorasUso.getText()));
            setMinutosUso(Integer.parseInt(txMntsUso.getText()));
            setCantidad(Integer.parseInt(txCant.getText()));
            FRMInicio.modelElecs.addRow(new Object[]{getNombre(),getVatios(),getHorasUso(),getMinutosUso(),getCantidad(),
            consultarKWH(getVatios(),getHorasUso(),getMinutosUso(),getCantidad())});
        }
        if (e.getSource().equals(btnEliminar)){
            llNotiEl.setForeground(Color.black);
            llNotiEl.setText("Objeto eliminado exitosamente");
            eliminarRow(txEliminar.getText().toLowerCase());
        }
    }
    //todo############-Constructor-############
    public FRMValoresElecs(){
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
        btnEliminar=new JButton("Eliminar");
        btnEliminar.setBounds(tile*12,tile*25,tile*8,height);
        btnEliminar.setBackground(Color.red);
        btnEliminar.setBorder(BorderFactory.createBevelBorder(1,Color.darkGray,Color.white));
        btnEliminar.addActionListener(this);
        llNoti=new JLabel("",SwingConstants.CENTER);
        llNoti.setBounds(tile*2,tile*7,tile*28,height);
        llNotiEl=new JLabel("",SwingConstants.CENTER);
        llNotiEl.setBounds(tile*2,tile*28,tile*28,height);
        llNombre=new JLabel("Nombre del objeto:",SwingConstants.CENTER);
        llNombre.setBounds(tile*2,tile*10,tile*13,height);
        llNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txNombre=new JTextField("cosa");
        txNombre.setBounds(tile*16,tile*10,tile*14,height);
        txNombre.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        llVatios=new JLabel("Vatios que consume:",SwingConstants.CENTER);
        llVatios.setBounds(tile*2,tile*13,tile*13,height);
        llVatios.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txVatios=new JTextField("100");
        txVatios.setBounds(tile*16,tile*13,tile*14,height);
        txVatios.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        llHorasUso=new JLabel("Horas de uso:",SwingConstants.CENTER);
        llHorasUso.setBounds(tile*2,tile*16,tile*13,height);
        llHorasUso.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txHorasUso=new JTextField("2");
        txHorasUso.setBounds(tile*16,tile*16,tile*14,height);
        txHorasUso.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        llMntsUso=new JLabel("Minutos de uso:",SwingConstants.CENTER);
        llMntsUso.setBounds(tile*2,tile*19,tile*13,height);
        llMntsUso.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txMntsUso=new JTextField("30");
        txMntsUso.setBounds(tile*16,tile*19,tile*14,height);
        txMntsUso.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        llCant=new JLabel("Cantidad:",SwingConstants.CENTER);
        llCant.setBounds(tile*2,tile*22,tile*13,height);
        llCant.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        txCant=new JTextField("1");
        txCant.setBounds(tile*16,tile*22,tile*14,height);
        txCant.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        txEliminar=new JTextField("Escriba el objeto a eliminar");
        txEliminar.setBounds(tile*2,tile*31,tile*28,height);
        txEliminar.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));

        jPanel=new JPanel();
        jPanel.setBounds(tile,tile*3,tile*30,tile*44);
        jPanel.setBackground(Color.lightGray);
        jPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3)
                ,"Modificar electrodomésticos"));

        //Añadir elementos
        add(btnRegresar);
        add(btnGuardar);
        add(btnEliminar);
        add(llNoti);
        add(llNotiEl);
        add(llNombre);
        add(txNombre);
        add(llVatios);
        add(txVatios);
        add(llHorasUso);
        add(txHorasUso);
        add(llMntsUso);
        add(txMntsUso);
        add(llCant);
        add(txCant);
        add(txEliminar);
        //----------
        add(jPanel);

        //Creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Editar valores de los electrodomésticos");
        setSize(vWidth,vHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);
    }
    public static void llenarTablaElecs() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String consulta="select * from electrodomesticos";
        Connection cnx = Conectar.Conexion();
        Statement s = cnx.createStatement();
        ResultSet resultado = s.executeQuery(consulta);
        while (resultado.next()){
            FRMInicio.modelElecs.addRow(new Object[] {resultado.getString("nombre")
                    ,resultado.getInt("vatios")
                    ,resultado.getInt("horasuso")
                    ,resultado.getInt("minutosuso")
                    ,resultado.getInt("cantidad")
                    ,consultarKWH(resultado.getInt("vatios"),resultado.getInt("horasuso")
                    ,resultado.getInt("minutosuso")
                    ,resultado.getInt("cantidad"))});
            Electrodomestico.cantElects++;
        }
    }
    public static void eliminarRow(String dato){
        int numRow=0;
        boolean encontrado=false;
        for (int j = 0; j < FRMInicio.tablaElecs.getRowCount(); j++) {
            if (FRMInicio.tablaElecs.getValueAt(j,0).equals(dato)){
                numRow=j;
                encontrado=true;
                break;
            }
        }
        if (encontrado){
            FRMInicio.modelElecs.removeRow(numRow);
        }else {
            llNotiEl.setForeground(Color.RED);
            llNotiEl.setText("Objeto no encontrado");
        }
    }
    public static float consultarKWH(int vatios,int horasuso, int minutosuso, int cantidad) {
        if (horasuso==0){
            return (float) vatios /1000*minutosuso*cantidad;
        }else {
            return (float) vatios /1000*horasuso+minutosuso*cantidad;
        }
    }
    public static String getNombre() {return nombre;}
    public static void setNombre(String nombre) {FRMValoresElecs.nombre = nombre;}
    public static int getVatios() {return vatios;}
    public static void setVatios(int vatios) {FRMValoresElecs.vatios = vatios;}
    public static int getHorasUso() {return horasUso;}
    public static void setHorasUso(int horasUso) {
        if (horasUso>24){
            llNoti.setForeground(Color.RED);
            llNoti.setText("Un día no tiene mas de 24 horas");
        }else {FRMValoresElecs.horasUso = horasUso;}
    }
    public static int getMinutosUso() {return minutosUso;}
    public static void setMinutosUso(int minutosUso) {
        if (minutosUso>60){
            llNoti.setForeground(Color.RED);
            llNoti.setText("Una hora no tiene mas de 60 minutos");
        }
    }
    public static int getCantidad() {return cantidad;}
    public static void setCantidad(int cantidad) {FRMValoresElecs.cantidad = cantidad;}
}
