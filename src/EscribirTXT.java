import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class EscribirTXT { //todo ya no escribe al archivo sino a la base de datos (fecha, hora, usuario, rol)
    public static void escribir(String ingreso) {
        String nombreArchivo = "login.log.txt";
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(ingreso);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //public static void borrar(){}
}
