package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Editor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");

        HttpSession sesion = req.getSession(false);

        if (sesion != null) {

            try {
                PrintWriter out = resp.getWriter();

                printEditor(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                resp.sendRedirect(req.getContextPath() + "/blog");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //conseguir valores 
        //add a bbdd
        //redirigir a panel

        String titulo = req.getParameter("titulo");
        String fecha = req.getParameter("fecha");
        String texto = req.getParameter("texto");
        
        LocalDate fechaLocalDate = LocalDate.parse(fecha);
        int fechaUnix = DB.conversionLocalDateUnix(fechaLocalDate);

        Connection conexion = DB.conectar();
        DB.actualizarTablaEntradas(titulo, texto, fechaUnix);

        try {
            resp.sendRedirect(req.getContextPath() + "/panelcontrol");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printEditor(PrintWriter out) {
        out.println(PlantillasHTML.mHead);
        out.println(PlantillasHTML.mCabecera.replace("$mensaje$", "Editor"));
        out.println(PlantillasHTML.mEditorForm);
        out.println(PlantillasHTML.mFooter);
    }
}
