import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
public class FRMLogin extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static final int tile=16;
    static int height=tile*2, vWidth=tile*38, vHeight=tile*32; //No se usan tilesets impares
    static JLabel llUsuario, llContrasena, llNotificacion;
    static JTextField txUsuario;
    static JPasswordField passContrasena;
    static JButton btnIngresar, btnOlvidoPass, btnRegistrar;
    //todo############-Action Listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnIngresar)){
            try{if (Usuarios.validarUsuario(txUsuario.getText().toLowerCase(),passContrasena.getText().toLowerCase())){
                if (Usuarios.validarAdmin(txUsuario.getText().toLowerCase(),passContrasena.getText().toLowerCase())){
                    FRMInicio.menuAdmin.setVisible(true);
                } else {
                    FRMInicio.menuAdmin.setVisible(false);
                }
                String ingreso = LocalDate.now()+";"+ LocalTime.now()+Usuarios.datosUsuario(txUsuario.getText().toLowerCase());
                EscribirTXT.escribir(ingreso);
                Iniciador.inicioVentana.setVisible(true);
                this.setVisible(false);
                JOptionPane.showMessageDialog(null,"Los datos mostrados son aproximados");
            } else {
                llNotificacion.setForeground(Color.RED);
                llNotificacion.setText("Usuario o contraseña inválidos");
                btnOlvidoPass.setVisible(true);
            }}catch (Exception ev){ev.printStackTrace();}
        }
        if (e.getSource().equals(btnRegistrar)){
            try {
                Insertar.ingresarBD(txUsuario.getText().toLowerCase(),passContrasena.getText().toLowerCase());
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource().equals(btnOlvidoPass)){
            Iniciador.recuperarPassVentana.setVisible(true);
            this.setVisible(false);
        }
    }
    //todo############-Constructor-############
    public FRMLogin(){
        //Creación de elementos
        llUsuario=new JLabel("Usuario", SwingConstants.CENTER);
        llUsuario.setBounds(vWidth/2-tile*3,tile*7,tile*6,height);
        llUsuario.setFont(new Font("Tahoma",Font.BOLD,12));

        txUsuario=new JTextField("Juan", SwingConstants.CENTER);
        txUsuario.setBounds(vWidth/2-tile*9,tile*9,tile*18,height);
        txUsuario.setBorder(BorderFactory.createLineBorder(Color.white,5,true));

        llContrasena=new JLabel("Contraseña", SwingConstants.CENTER);
        llContrasena.setBounds(vWidth/2-tile*3,tile*13,tile*6,height);
        llContrasena.setFont(new Font("Tahoma",Font.BOLD,12));

        passContrasena=new JPasswordField("J10", SwingConstants.CENTER);
        passContrasena.setBounds(vWidth/2-tile*9,tile*15,tile*18,height);
        passContrasena.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true));

        btnIngresar=new JButton("Ingresar");
        btnIngresar.setBounds(vWidth/2-tile*9,tile*19,tile*6,height);
        btnIngresar.addActionListener(this);
        btnIngresar.setBorder(BorderFactory.createBevelBorder(1,Color.DARK_GRAY,Color.WHITE));

        btnRegistrar=new JButton("Registrar");
        btnRegistrar.setBounds(vWidth/2+tile*3,tile*19,tile*6,height);
        btnRegistrar.addActionListener(this);
        btnRegistrar.setBorder(BorderFactory.createBevelBorder(1,Color.DARK_GRAY,Color.WHITE));

        btnOlvidoPass=new JButton("¿Olvidó su contraseña?");
        btnOlvidoPass.addActionListener(this);
        btnOlvidoPass.setBounds(vWidth/2-tile*6,tile*22,tile*12,height);
        btnOlvidoPass.setBorder(BorderFactory.createBevelBorder(1,Color.DARK_GRAY,Color.WHITE));
        btnOlvidoPass.setVisible(false);

        llNotificacion=new JLabel("",SwingConstants.CENTER);
        llNotificacion.setForeground(Color.RED);
        llNotificacion.setBounds(vWidth/2-tile*9,tile*26,tile*18,height);

        //Añadir elementos
        add(llUsuario);
        add(txUsuario);
        add(llContrasena);
        add(passContrasena);
        add(btnIngresar);
        add(btnRegistrar);
        add(btnOlvidoPass);
        add(llNotificacion);

        //Creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login usuario");
        setSize(vWidth,vHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
    }
}