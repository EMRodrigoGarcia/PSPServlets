package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            HttpSession sesion = req.getSession(false);
            if (sesion != null) {
                printBlogLogged(out);
            } else {
                printBlog(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printBlog(PrintWriter out) {
        try {

            Connection conexion = DB.conectar();
            List<Entrada> entradas = DB.getEntradas();
            out.println(PlantillasHTML.mHead);
            out.println(PlantillasHTML.mCabecera.replace("$mensaje$", "Blog"));

            out.println(PlantillasHTML.mButtonLogin);

            for (Entrada entrada : entradas) {
                out.println(PlantillasHTML.mCarta.replace("$titulo$", entrada.getTitulo())
                        .replace("$fecha$", entrada.getFechaPublicacion().toString())
                        .replace("$texto$", entrada.getTexto()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printBlogLogged(PrintWriter out) {
        try {
            Connection conexion = DB.conectar();
            List<Entrada> entradas = DB.getEntradas();
            out.println(PlantillasHTML.mHead);
            out.println(PlantillasHTML.mCabecera.replace("$mensaje$", "Blog"));
            out.println(PlantillasHTML.mButtonPanel);
            out.println(PlantillasHTML.mButtonLogout);

            for (Entrada entrada : entradas) {
                out.println(PlantillasHTML.mCarta.replace("$titulo$", entrada.getTitulo())
                        .replace("$fecha$", entrada.getFechaPublicacion().toString())
                        .replace("$texto$", entrada.getTexto()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
