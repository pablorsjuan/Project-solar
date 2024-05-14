import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class LeerTXT {
    public static String leer_escribir(String nombreArchivo){
        String linea="";
        try {
            FileReader fileReader=new FileReader(nombreArchivo);
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            linea= bufferedReader.readLine();

            while (linea!=null){
                String[] cadena=linea.split(";");
                FRMostrarLogs.model.addRow(new Object[] {cadena[0],cadena[1],cadena[2]});
                linea= bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e){
            System.out.println("Error de lectura:\n"+e);
        }
        return linea;
    }
}