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

public class InicioSesion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
       
       try {
           Connection conexion = DB.conectar();
           boolean encontrado = false;
            String user = req.getParameter("user");
            String password = req.getParameter("password");

            ResultSet set = DB.selectTablaUsers(user, password);
            HttpSession sesion = req.getSession(true);
            while(set.next() && !encontrado) {
                if (set.getString("usuario").equals(user) && set.getString("password").equals(password)) {
                    encontrado = true;
                    sesion.setAttribute("user", user);
                    sesion.setMaxInactiveInterval(60 * 30);
                }
            }            

            PrintWriter out = resp.getWriter();


            if(encontrado) {
                pintarHTMLExito(out);
            }else {
                pintarHTMLFallo(out);
            }
            
           DB.desconectar();
       } catch (Exception e) {
           //TODO: handle exception
           e.printStackTrace();
       } 
    }

    private void pintarHTMLExito(PrintWriter out) {
        ST st = new ST(PlantillasHTML.mAlertExito);
        out.println(PlantillasHTML.mHead);
        out.println(PlantillasHTML.mCabecera);
        out.println(st.setAttribute("Usuario autenticado correctamente"));
        Connection conexion = DB.conectar();
        
        ResultSet set = DB.getEntradas();

        DB.desconectar();
        while(set.next()) {
            st = new ST(PlantillasHTML.mCarta);
            st.setAttribute("titulo", set.getString("titulo"));
            st.setAttribute("fecha", set.getDate("fecha").toString());
            st.setAttribute("texto", set.getString("texto"));
            out.println(st.render());
        }
        out.println(PlantillasHTML.mFooter);
    }

    private void pintarHTMLFallo(PrintWriter out) {
        ST st = new ST(PlantillasHTML.mAlertFallo);
        out.println(PlantillasHTML.mLoginHead);
        out.println(st.setAttribute("Usuario / password no encontrado"));
        out.println(PlantillasHTML.mLoginFooter);
    }

       
}