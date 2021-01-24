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

public class PanelControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            printPanelControl(out);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }


    public static void printPanelControl(PrintWriter out) {
        out.println(PlantillasHTML.mHead);
        out.println(PlantillasHTML.mPanelBotonesSuperior);
        out.println(PlantillasHTML.mPanelFormContrasena);
        out.println(PlantillasHTML.mPanelCrear);
        out.println(PlantillasHTML.mFooter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // encontrar usuario logeado
        HttpSession sesion = req.getSession(true);

        String userRecibido = (String) sesion.getAttribute("user");
        String passwordRecibida = req.getParameter("password");
        String newPasswordRecibida = req.getParameter("newpassword");

        // encontrar su contrasena
        Connection conexion = DB.conectar();

        try {
            ResultSet set = DB.selectPasswordUsers(userRecibido);

            while(set.next()) {
                // comprobar que su contrasena coincide 
                if (set.getString("password").equals(passwordRecibida)) {
                    // actualizar bbdd para cambiar tabla users con contrasena nueva
                    DB.actualizarPassword(userRecibido, newPasswordRecibida);
                    PrintWriter out = resp.getWriter();
                    out.println(PlantillasHTML.mAlertExito.replace("$mensaje$", "Contraseña reemplazada correctamente"));
                    printPanelControl(out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
        }
    }
}
