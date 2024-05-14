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
    static int height=tile*2, vWidth=16*64, vHeight=16*44;
    static JButton btnRegresar;
    static ImageIcon imgRegresar;
    static JTable tabla;
    static DefaultTableModel model;
    static JScrollPane scrollpane;
    static JLabel llCantUs, llCantAds, llTotal;
    //todo############-Action listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
        }
    }
    //todo############-Constructor-############
    public FRMostrarUs() throws RuntimeException {
        //Creación de elementos
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(tile,tile,tile*3,height);
        btnRegresar.addActionListener(this);

        llCantUs=new JLabel();
        llCantUs.setBounds(vWidth/2,tile*4,tile*30,height);
        llCantUs.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));

        llCantAds=new JLabel();
        llCantAds.setBounds(vWidth/2,tile*7,tile*30,height);
        llCantAds.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));

        llTotal=new JLabel("Total: ");
        llTotal.setBounds(vWidth/2,tile*10,tile*30,height);
        llTotal.setForeground(Color.BLUE);
        llTotal.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));

        //Creación de tabla
        model = new DefaultTableModel();
        tabla = new JTable(model);
        model.addColumn("Número");
        model.addColumn("Usuario");
        model.addColumn("Rol");
        scrollpane = new JScrollPane(tabla);
        scrollpane.setLocation(tile,tile*4);
        scrollpane.setSize(tile*30,tile*37);
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

        //creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mostrar usuarios");
        setSize(vWidth,vHeight);
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
        llTotal.setText("Total: "+(numUs+numAds));
    }
}
