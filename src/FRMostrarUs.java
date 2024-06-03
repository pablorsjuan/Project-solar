import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FRMostrarUs extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static int numPers=1 ,numUs=0, numAds=0;
    static final int tile=16;
    static int height=tile*2, vWidth=tile*94, vHeight=tile*50;
    static JButton btnRegresar, btnBscrNum, btnBscrStr;
    static ImageIcon imgRegresar;
    static JTable tabla;
    static DefaultTableModel model;
    static JScrollPane scrollpane;
    static JLabel llCantUs, llCantAds, llTotal, llBuscarUs, llNoti;
    static JTextField txBuscarUs;
    static JPanel jPanel, jPanel2;
    static JTextArea txaUsuario;
    //todo############-Action listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource().equals(btnBscrNum)){
            buscarUs(txBuscarUs.getText(),true);
        }
        if (e.getSource().equals(btnBscrStr)){
            buscarUs(txBuscarUs.getText(),false);
        }
    }
    //todo############-Constructor-############
    public FRMostrarUs() throws RuntimeException {
        //Creación de elementos
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(tile,tile,tile*3,height);
        btnRegresar.setBorder(BorderFactory.createBevelBorder(1,Color.DARK_GRAY,Color.WHITE));
        btnRegresar.addActionListener(this);
        llCantUs=new JLabel("",SwingConstants.CENTER);
        llCantUs.setBounds(tile*33,tile*4,tile*28,height);
        llCantUs.setBorder(BorderFactory.createLineBorder(Color.gray,3));
        llCantAds=new JLabel("",SwingConstants.CENTER);
        llCantAds.setBounds(tile*33,tile*7,tile*28,height);
        llCantAds.setBorder(BorderFactory.createLineBorder(Color.gray,3));
        llTotal=new JLabel("",SwingConstants.CENTER);
        llTotal.setBounds(tile*33,tile*10,tile*28,height);
        llTotal.setBorder(BorderFactory.createLineBorder(Color.cyan,3));
        llBuscarUs=new JLabel("Escriba el nombre o número de usuario", SwingConstants.CENTER);
        llBuscarUs.setBounds(tile*33,tile*16,tile*28,height);
        txBuscarUs=new JTextField();
        txBuscarUs.setBounds(tile*33,tile*19,tile*28,height);
        txBuscarUs.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));
        btnBscrNum =new JButton("Buscar por número");
        btnBscrNum.setBounds(tile*35,tile*22,tile*10,height);
        btnBscrNum.setBackground(Color.CYAN);
        btnBscrNum.setBorder(BorderFactory.createBevelBorder(1,Color.blue,Color.white));
        btnBscrNum.addActionListener(this);
        btnBscrStr=new JButton("Buscar por nombre");
        btnBscrStr.setBounds(tile*49,tile*22,tile*10,height);
        btnBscrStr.setBackground(Color.CYAN);
        btnBscrStr.setBorder(BorderFactory.createBevelBorder(1,Color.blue,Color.white));
        btnBscrStr.addActionListener(this);
        llNoti=new JLabel("",SwingConstants.CENTER);
        llNoti.setBounds(tile*33,tile*25,tile*28,height);
        txaUsuario=new JTextArea();
        txaUsuario.setBounds(tile*33,tile*28,tile*28,tile*17);

        jPanel=new JPanel();
        jPanel.setBounds(tile*32,tile*3,tile*30,tile*10);
        jPanel.setBackground(Color.lightGray);
        jPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3,true)
                ,"Cantidades"));
        jPanel2=new JPanel();
        jPanel2.setBounds(tile*32,tile*14,tile*30,tile*32);
        jPanel2.setBackground(Color.lightGray);
        jPanel2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY,3,true)
                ,"Buscar usuario"));

        //Creación de tabla
        model = new DefaultTableModel();
        tabla = new JTable(model);
        model.addColumn("Número");
        model.addColumn("Usuario");
        model.addColumn("Rol");
        scrollpane = new JScrollPane(tabla);
        scrollpane.setLocation(tile,tile*4);
        scrollpane.setSize(tile*30,tile*42);
        scrollpane.setBorder(BorderFactory.createLineBorder(Color.GRAY,3,true));
        tabla.setRowHeight(height);
        tabla.setGridColor(Color.gray);
        tabla.setBackground(Color.darkGray);
        tabla.setForeground(Color.WHITE);
        //Tabla
        try {
            recorrerBD();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        //Añadir elementos
        add(btnRegresar);
        add(scrollpane, BorderLayout.CENTER);
        add(llCantUs);
        add(llCantAds);
        add(llTotal);
        add(llBuscarUs);
        add(txBuscarUs);
        add(llNoti);
        add(btnBscrNum);
        add(btnBscrStr);
        add(txaUsuario);
        add(jPanel);
        add(jPanel2);

        //creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mostrar usuarios");
        setSize(vWidth,vHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    static void recorrerBD() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String consulta="select * from usolar";
        Connection cnx = Conectar.Conexion();
        Statement s = cnx.createStatement();
        ResultSet resultado = s.executeQuery(consulta);
        while (resultado.next()){
            if (resultado.getInt("codadmin")==0){
                model.addRow(new Object[] {numPers,resultado.getString("usuario"),"usuario"});
                numUs++;
            }else {
                model.addRow(new Object[] {numPers,resultado.getString("usuario"),"administrador"});
                numAds++;
            }
            numPers++;
        }
        cnx.close();
        llCantUs.setText("Cantidad de usuarios: "+numUs);
        llCantAds.setText("Cantidad de administradores: "+numAds);
        llTotal.setText("Total usuarios: "+(numUs+numAds));
    }
    static void buscarUs(String dBuscado, boolean esNumero){
        boolean encontrado=false;
        int numColumna;
        if (esNumero){
            numColumna=0;
            for (int j = 0; j < tabla.getRowCount(); j++) { //for si el dato es un int
                if (tabla.getValueAt(j,numColumna).equals(Integer.parseInt(dBuscado))){
                    txaUsuario.setText("- "+tabla.getValueAt(j,numColumna)
                            +" / "+tabla.getValueAt(j,numColumna+1)
                            +" / "+tabla.getValueAt(j,numColumna+2));
                    encontrado=true;
                    break;
                }
            }
        } else {
            numColumna=1;
            for (int j = 0; j < tabla.getRowCount(); j++) { //for si el dato es un String
                if (tabla.getValueAt(j,numColumna).equals(dBuscado)){
                    txaUsuario.setText("- "+tabla.getValueAt(j,numColumna-1)
                            +" / "+tabla.getValueAt(j,numColumna)
                            +" / "+tabla.getValueAt(j,numColumna+1));
                    encontrado=true;
                    break;
                }
            }
        }
        if (encontrado){
            llNoti.setText("Usuario encontrado en la base de datos");
        }else {
            llNoti.setForeground(Color.RED);
            llNoti.setText("Usuario no encontrado");
        }
    }
}
