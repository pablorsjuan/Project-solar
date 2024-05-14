import java.awt.*;

public class Iniciador {
    static private final int vWidthLogin=600, vHeightLogin=500;
    static Color color = Color.lightGray;
    static Iniciador iniciador;
    static FRMLogin ventana;
    static FRMInicio inicioVentana;
    static FRMRegistrarP registrarVentana;
    static FRMostrarUs mostrarUsVentana;
    static FRMostrarLogs mostrarLogsVentana;
    static FRMRecuperarPass recuperarPassVentana;
    static FRMValoresElecs valoresElecsVentana;
    static FRMValoresResult valoresResultVentana;
    static FRMValoresPanel valoresPanelVentana;
    public Iniciador(){
        //Creación de ventanas extranjeras
        ventana=new FRMLogin();
        ventana.setVisible(true);
        ventana.getContentPane().setBackground(color);
        inicioVentana=new FRMInicio();
        inicioVentana.getContentPane().setBackground(color);
        registrarVentana=new FRMRegistrarP();
        registrarVentana.getContentPane().setBackground(color);
        mostrarUsVentana=new FRMostrarUs();
        mostrarUsVentana.getContentPane().setBackground(color);
        mostrarLogsVentana=new FRMostrarLogs();
        mostrarLogsVentana.getContentPane().setBackground(color);
        recuperarPassVentana=new FRMRecuperarPass();
        recuperarPassVentana.getContentPane().setBackground(color);
        valoresElecsVentana=new FRMValoresElecs();
        valoresElecsVentana.getContentPane().setBackground(color);
        valoresResultVentana=new FRMValoresResult();
        valoresResultVentana.getContentPane().setBackground(color);
        valoresPanelVentana=new FRMValoresPanel();
        valoresPanelVentana.getContentPane().setBackground(color);
    }
    public static void main(String[] args) {
        System.out.println("+-------------------------------------+");
        System.out.println("| Iniciador funcionando correctamente |");
        System.out.println("+-------------------------------------+");
        iniciador=new Iniciador();
    }
    //Métodos
    public int getvWidthLogin() {return vWidthLogin;}
    public int getvHeightLogin() {return vHeightLogin;}
}