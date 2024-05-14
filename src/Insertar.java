import java.awt.*;
import java.sql.*;
public class Insertar {
    public static void ingresarBD(String usuario, String contrasena) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection cnx = Conectar.Conexion();
        String insertar = "insert into usolar(usuario,contrasena,codadmin) values(\""+usuario+"\",\""+contrasena+"\",0)";
        Statement s = cnx.createStatement();
        s.executeUpdate(insertar);
        FRMLogin.llNotificacion.setForeground(Color.GREEN);
        FRMLogin.llNotificacion.setText("Usuario registrado");
        cnx.close();
    }
}
