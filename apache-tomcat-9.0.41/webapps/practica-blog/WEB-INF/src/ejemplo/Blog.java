package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.stringtemplate.v4.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Blog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter out = resp.getWriter();
            HttpSession sesion = req.getSession(false);
            if (sesion != null) {
                printBlogLogged(out);
            } else {
                printBlog(out);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void printBlog(PrintWriter out) {
        try {

            Connection conexion = DB.conectar();
            ResultSet set = DB.getEntradas();
            out.println(PlantillasHTML.mHead);
            out.println(PlantillasHTML.mCabecera);

            out.println(PlantillasHTML.mButtonLogin);
            String aux = "";
            while (set.next()) {
                out.println(PlantillasHTML.mCarta.replace("$titulo$", set.getString("titulo")).replace("$fecha$",
                        Integer.toString(set.getInt("fecha")).replace("$texto$", set.getString("texto"))));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void printBlogLogged(PrintWriter out) {
        try {
            Connection conexion = DB.conectar();
            ResultSet set = DB.getEntradas();
            out.println(PlantillasHTML.mHead);
            out.println(PlantillasHTML.mCabecera);
            out.println(PlantillasHTML.mButtonPanel);
            out.println(PlantillasHTML.mButtonLogout);
            String aux = "";
            while (set.next()) {
                out.println(PlantillasHTML.mCarta.replace("$titulo$", set.getString("titulo")).replace("$fecha$",
                        Integer.toString(set.getInt("fecha")).replace("$texto$", set.getString("texto"))));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
