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
    static JButton btnRegresar;
    static ImageIcon imgRegresar;
    //todo############-Action Listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
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

        //Añadir elementos
        add(btnRegresar);

        //Creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Editar valores del panel solar");
        setSize(vWidth,vHeight);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    public static void llenarTablaPanel(){
        FRMInicio.modelPanel.addRow(new Object[] {"Trabajo en proceso ..."});
    }
}
