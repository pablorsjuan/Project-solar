import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class FRMRegistrarP extends JFrame implements ActionListener {
    //todo############-Atributos-############
    static int X=10, Y=10, vWidth=600, vHeight=500;
    static ArrayList<String> listaP = new ArrayList<>();
    static JLabel llRegistrarP, llNotificacion;
    static JTextField txRegistrarP;
    static JButton btnRegresar, btnRegistrar;
    static ImageIcon imgRegresar;
    //todo############-Action Listener-############
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnRegistrar)){
            listaP.add(txRegistrarP.getText());
            Object[] fila = {txRegistrarP.getText()};
            //FRMInicio.model.addRow(fila);
            JOptionPane.showMessageDialog(this,"Pregunta guardada exitosamente");
            Iniciador.inicioVentana.setVisible(true);
            this.setVisible(false);
        }
    }
    //todo############-Constructor-############
    public FRMRegistrarP(){
        //Creación de elementos
        imgRegresar=new ImageIcon(".\\img\\return.png");
        btnRegresar=new JButton(imgRegresar);
        btnRegresar.setBounds(X,Y,50,30);
        llRegistrarP=new JLabel("Registar pregunta");
        llRegistrarP.setBounds(X,Y*4,200,30);
        txRegistrarP=new JTextField();
        txRegistrarP.setBounds(X,Y*7,vWidth-40,30);
        btnRegistrar =new JButton("Registrar");
        btnRegistrar.setBounds(X,Y*12,100,30);
        //Añadir elementos
        add(llRegistrarP);
        add(txRegistrarP);
        add(btnRegresar);
        btnRegresar.addActionListener(this);
        add(btnRegistrar);
        btnRegistrar.addActionListener(this);

        //Creación de ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registrar pregunta");
        setSize(vWidth,vHeight);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
