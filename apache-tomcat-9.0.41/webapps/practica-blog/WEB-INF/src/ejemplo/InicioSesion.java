package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InicioSesion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println(PlantillasHTML.mLoginHead);
        out.println(PlantillasHTML.mLoginForm);
        out.println(PlantillasHTML.mLoginFooter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Connection conexion = DB.conectar();
            boolean encontrado = false;
            String user = req.getParameter("user");
            String password = req.getParameter("password");

            ResultSet set = DB.selectTablaUsers(user, password);
            HttpSession sesion = req.getSession(true);
            while (set.next() && !encontrado) {
                if (set.getString("usuario").equals(user) && set.getString("password").equals(password)) {
                    encontrado = true;
                    sesion.setAttribute("user", user);
                    sesion.setMaxInactiveInterval(60 * 30);
                }
            }
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();

            if (encontrado) {
                resp.sendRedirect(req.getContextPath() + "/panelcontrol");
            } else {
                loginHTMLFail(out);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loginHTMLFail(PrintWriter out) {
        out.println(PlantillasHTML.mAlertFallo.replace("$mensaje$", "Usuario / password no encontrado"));
        printLogin(out);
    }

    public static void printLogin(PrintWriter out) {
        out.println(PlantillasHTML.mLoginHead);
        out.println(PlantillasHTML.mLoginForm);
        out.println(PlantillasHTML.mLoginFooter);
    }

}