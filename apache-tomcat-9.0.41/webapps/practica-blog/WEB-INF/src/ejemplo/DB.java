package ejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void crearTablaEntradas() {
        String query = "CREATE TABLE IF NOT EXISTS entradas(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, texto TEXT, fecha INTEGER)";
        try {
            Statement stm = conexion.createStatement();
            stm.execute(query);
        } catch (Exception e) {
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
            e.printStackTrace();
        }

        return set;
    }

    public static ResultSet selectPasswordUsers(String user) {
        ResultSet set = null;

        String query = "SELECT password FROM usuarios WHERE usuario = ?";

        try {
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, user);
            set = stm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

    public static int borrarEntradaId(int id) {
        int cuantos = 0;

        String query = "DELETE FROM entradas WHERE id = ?";

        try {
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, Integer.toString(id));

            cuantos = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cuantos;
    }

    public static int actualizarPassword(String user, String password) {
        int cuantos = 0;
        String query = "UPDATE usuarios SET password = ? WHERE usuario = ?";

        try {
            PreparedStatement stm = conexion.prepareStatement(query);

            stm.setString(1, password);
            stm.setString(2, user);
            cuantos = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cuantos;
    }

    public static int actualizarTablaEntradas(String titulo, String texto, int fecha) {
        int cuantos = 0;
        // entradas = id (AI), titulo, texto, fecha
        String query = "INSERT OR REPLACE INTO entradas(titulo, texto, fecha) VALUES (?, ?, ?)";
        try {
            PreparedStatement stm = conexion.prepareStatement(query);

            stm.setString(1, titulo);
            stm.setString(2, texto);
            stm.setInt(3, fecha);
            cuantos = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cuantos;
    }

    public static List<Entrada> getEntradas() {
        ResultSet set = null;
        String query = "SELECT id, titulo, fecha, texto FROM entradas";
        List<Entrada> entradas = new ArrayList<>();
        try {
            PreparedStatement stm = conexion.prepareStatement(query);
            set = stm.executeQuery();
            while (set.next()) {
                entradas.add(new Entrada(set.getInt("id"), set.getString("titulo"), set.getString("texto"),
                        conversionUnixLocalDate(set.getInt("fecha"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        entradas.sort(Comparator.comparing(Entrada::getFechaPublicacion).reversed());

        return entradas;
    }

    private static LocalDate conversionUnixLocalDate(int fechaUnix) {
        return Instant.ofEpochSecond((int) fechaUnix).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static int conversionLocalDateUnix(LocalDate fechaLocalDate) {

        return (int) fechaLocalDate.toEpochSecond(LocalTime.NOON, ZoneOffset.MIN);
    }
}
