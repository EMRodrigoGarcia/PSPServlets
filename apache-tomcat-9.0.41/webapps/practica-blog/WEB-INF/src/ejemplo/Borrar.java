package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Borrar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id_entrada = req.getParameter("id_entrada");
        Connection conexion = DB.conectar();
        try {
            DB.borrarEntradaId(Integer.parseInt(id_entrada));
            resp.sendRedirect(req.getContextPath() + "/panelcontrol");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
