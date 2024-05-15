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
    private static double costoKWH=47.04;
    private static String empresa="EPM";
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
    public static void llenarTablaResult(){
        double kwh=0;
        for (int j = 0; j < FRMInicio.tablaElecs.getRowCount(); j++) {
            kwh=kwh+(float)FRMInicio.tablaElecs.getValueAt(j,4);
        }
        FRMInicio.modelResult.addRow(new Object[] {"Cantidad de objetos: "+Electrodomestico.getCantElects()});
        FRMInicio.modelResult.addRow(new Object[]{"Costo total: "+kwh*costoKWH*30+" pesos"});
        FRMInicio.modelResult.addRow(new Object[]{"kWh por día: "+kwh});
        FRMInicio.modelResult.addRow(new Object[]{"kWh por semana: "+kwh*7});
        FRMInicio.modelResult.addRow(new Object[]{"kWh por mes: "+kwh*30});
        FRMInicio.modelResult.addRow(new Object[]{"Empresa prestadora de energía: "+empresa});
        FRMInicio.modelResult.addRow(new Object[]{"Costo kWh (nivel 1 - residencial): "+costoKWH*kwh*30});
        FRMInicio.modelResult.addRow(new Object[]{"Costo kWh (nivel 2): "+24.13*kwh*30+" pesos"});
        FRMInicio.modelResult.addRow(new Object[]{"Costo kWh (nivel 3): "+30.40*kwh*30+" pesos"});
    }
    public static double getCostoKWH() {return costoKWH;}
    public static void setCostoKWH(double costoKWH) {FRMValoresResult.costoKWH = costoKWH;}
    public static String getEmpresa() {return empresa;}
    public static void setEmpresa(String empresa) {FRMValoresResult.empresa = empresa;}
}
//Nivel 1 (Residencial): $47.04 COP/kWh
//Nivel 2: $24.13 COP/kWh
//Nivel 3: $30.40 COP/kWh
//Nivel 4: $0 COP/kWh (Anexo Abril - 2024)
