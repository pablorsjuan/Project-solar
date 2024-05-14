import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
public class Usuarios {
    static String consulta="select * from usolar";
    static Boolean validarUsuario(String user, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection cnx = Conectar.Conexion();
        Statement s = cnx.createStatement();
        ResultSet resultado = s.executeQuery(consulta);
        while (resultado.next()){
            if (Objects.equals(user, resultado.getString("usuario")) &&
                    Objects.equals(password, resultado.getString("contrasena"))){
                cnx.close();
                return true;
            }
        }
        cnx.close();
        return false;
    }
    static Boolean validarAdmin(String user, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection cnx = Conectar.Conexion();
        Statement s = cnx.createStatement();
        ResultSet resultado = s.executeQuery(consulta);
        while (resultado.next()){ //Si no hay mas next, retorna null
            if (Objects.equals(user, resultado.getString("usuario")) &&
                    Objects.equals(password, resultado.getString("contrasena")) &&
                    resultado.getInt("codadmin")==1){
                cnx.close();
                return true;
            }
        }
        cnx.close();
        return false;
    }
    static String datosUsuario(String user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection cnx = Conectar.Conexion();
        String datos="";
        Statement s = cnx.createStatement();
        ResultSet resultado = s.executeQuery(consulta);
        while (resultado.next()){
            if (Objects.equals(user, resultado.getString("usuario")) &&
                    resultado.getInt("codadmin")==1) {
                datos=";"+user+";"+"Admin";
            } else {
                datos=";"+user+";"+"Usuario";
            }
        }
        cnx.close();
        return datos;
    }
}
