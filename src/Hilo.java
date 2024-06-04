import javax.swing.*;
import java.awt.*;

public class Hilo extends Thread{
    //Atributos
    //Cosntructor
    public Hilo(){}
    //Métodos
    public void run(){
        try {
            animar();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void animar() throws InterruptedException{
        int milisLargo=200, milisCortos=80;
        for (int i = 0; i < 3; i++) {
            FRMInicio.llepe.setBorder(BorderFactory.createLineBorder(Color.green,3,true));
            FRMInicio.llEstrato.setBorder(BorderFactory.createLineBorder(Color.green,3,true));
            FRMInicio.llCantElecs.setBorder(BorderFactory.createLineBorder(Color.green,3,true));
            FRMInicio.llCostoKWH.setBorder(BorderFactory.createLineBorder(Color.green,3,true));
            FRMInicio.llFactura.setBorder(BorderFactory.createLineBorder(Color.green,3,true));
            FRMInicio.scpnResult.setBorder(BorderFactory.createLineBorder(Color.green,3,true));
            FRMInicio.tablaResult.setGridColor(Color.green);
            FRMInicio.scpnElecs.setBorder(BorderFactory.createLineBorder(Color.green,3,true));
            FRMInicio.tablaElecs.setGridColor(Color.green);
            FRMInicio.tablaPanel.setGridColor(Color.green);
            sleep(milisCortos);
            FRMInicio.llepe.setBorder(BorderFactory.createLineBorder(Color.gray,3,true));
            FRMInicio.llEstrato.setBorder(BorderFactory.createLineBorder(Color.gray,3,true));
            FRMInicio.llCantElecs.setBorder(BorderFactory.createLineBorder(Color.gray,3,true));
            FRMInicio.llCostoKWH.setBorder(BorderFactory.createLineBorder(Color.gray,3,true));
            FRMInicio.llFactura.setBorder(BorderFactory.createLineBorder(Color.gray,3,true));
            FRMInicio.scpnResult.setBorder(BorderFactory.createLineBorder(Color.gray,3,true));
            FRMInicio.tablaResult.setGridColor(Color.gray);
            FRMInicio.scpnElecs.setBorder(BorderFactory.createLineBorder(Color.gray,3,true));
            FRMInicio.tablaElecs.setGridColor(Color.gray);
            FRMInicio.tablaPanel.setGridColor(Color.gray);
            sleep(milisCortos);
            sleep(milisLargo);
        }
    }
}
