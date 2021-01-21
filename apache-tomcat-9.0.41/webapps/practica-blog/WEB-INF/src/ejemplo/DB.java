package ejemplo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DB {
    public static Connection conexion;

    public static Connection conectar() {
        String url = "jdbc:sqlite:base_datos.db";

        if (conexion == null) {
            try {
                Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();
                conexion = DriverManager.getConnection(url);
                System.out.println("Conexion establecida");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return conexion;
    }

    public static void desconectar() {
        try {
            if (!conexion.isClosed()) {

                conexion.close();

            } else {
                System.out.println("Conexion ya cerrada");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void crearTablaUsers() {
        String query = "CREATE TABLE IF NOT EXISTS usuarios (usuario TEXT PRIMARY KEY, password TEXT)";
        try {
            Statement stm = conexion.createStatement();
            stm.execute(query);
        }catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void crearTablaEntradas() {
        String query = "CREATE TABLE IF NOT EXISTS entradas(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, texto TEXT, fecha INTEGER)";
        try {
            Statement stm = conexion.createStatement();
            stm.execute(query);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public static int actualizarTablaUsers(String usuario, String password) {
        int cuantos = 0;
        String query = "INSERT OR REPLACE INTO usuarios (usuario, password) VALUES (?, ?)";
        try {
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, usuario);
            stm.setString(2, password);
           cuantos = stm.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        return cuantos;
    }

    public static ResultSet selectTablaUsers(String user, String password) {
        ResultSet set = null;
        
        String query = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";

        try {
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, user);
            stm.setString(2, password);
            set = stm.executeQuery();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        return set;
    }
    public static int actualizarTablaEntradas(String titulo, String texto, int fecha) {
        int cuantos = 0;
        //entradas = id (AI), titulo, texto, fecha
        String query = "INSERT OR REPLACE INTO entradas(titulo, texto, fecha) VALUES (?, ?, ?)";
        try {
            PreparedStatement stm = conexion.prepareStatement(query);

            stm.setString(1, titulo);
            stm.setString(2, texto);
            stm.setInt(3, fecha);
            cuantos = stm.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return cuantos;
    }

    public static ResultSet getEntradas() {
        ResultSet set = null;
        String query = "SELECT titulo, fecha, texto FROM entradas";

        try {
            PreparedStatement stm = conexion.prepareStatement(query);
            set = stm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

}
