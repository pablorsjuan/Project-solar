import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FRMRecuperarPass extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static final int tile=16;
    static int height=tile*2, vWidth=tile*38, vHeight=tile*32;
    static JButton btnRegresar, btnGuardar;
    static ImageIcon imgRegresar;
    static JLabel llCorreo, llNotificacion;
    static JTextField txCorreo;
    //todo############-Action listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegresar)){
            Iniciador.ventana.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource().equals(btnGuardar)){
            llNotificacion.setText("Se le ha enviado un código de verificación al correo "+txCorreo.getText());
        }
    }
    //todo############-Constructor-############
    public FRMRecuperarPass(){
        //Creación de
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(tile,tile,tile*3,height);
        btnRegresar.setBorder(BorderFactory.createBevelBorder(1,Color.DARK_GRAY,Color.WHITE));
        btnRegresar.addActionListener(this);

        llCorreo=new JLabel("Ingrese su correo electrónico", SwingConstants.CENTER);
        llCorreo.setBounds(vWidth/2-tile*6,tile,tile*12,height);

        txCorreo=new JTextField("MiCorreo@gmail.com");
        txCorreo.setForeground(Color.DARK_GRAY);
        txCorreo.setBorder(BorderFactory.createLineBorder(Color.white,5,true));
        txCorreo.setBounds(vWidth/2-tile*9,tile*3,tile*18,height);

        btnGuardar=new JButton("Guardar");
        btnGuardar.setBounds(vWidth/2-tile*3,tile*6,tile*6,height);
        btnGuardar.setBorder(BorderFactory.createBevelBorder(1,Color.DARK_GRAY,Color.WHITE));
        btnGuardar.addActionListener(this);

        llNotificacion=new JLabel("", SwingConstants.CENTER);
        llNotificacion.setBounds(vWidth/2-tile*14,tile*8,tile*28,height);
        //Añadir elementos
        add(btnRegresar);
        add(llCorreo);
        add(txCorreo);
        add(btnGuardar);
        add(llNotificacion);
        //Creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Recuperación de cuenta");
        setSize(vWidth,vHeight);
        setLocationRelativeTo(null);
    }
}
