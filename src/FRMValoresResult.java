import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FRMValoresResult extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static final int tile=16;
    static int height=tile*2, vWidth=16*64, vHeight=16*44;
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
    public FRMValoresResult(){
        //Creación de elementos
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(tile,tile,tile*3,height);
        btnRegresar.addActionListener(this);

        //Añadir elementos
        add(btnRegresar);

        //Creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Editar valores de los resultados");
        setSize(vWidth,vHeight);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
