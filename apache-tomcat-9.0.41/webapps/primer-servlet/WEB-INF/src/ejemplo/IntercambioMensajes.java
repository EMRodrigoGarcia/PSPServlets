package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Date;

public class IntercambioMensajes extends HttpServlet{
    private static String mensaje = "";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException  {

        Properties props = new Properties();
        String accion = req.getParameter("accion");

        if (accion.equalsIgnoreCase("enviarMensaje")) {
            String mensajeRecibido = req.getParameter("mensaje");
            mensaje = mensajeRecibido;
        }else if (accion.equalsIgnoreCase("recibirMensaje")) {
            props.setProperty("getMensaje", mensaje);
        }else {
            System.err.println("No hay accion con ese nombre");
        }

        PrintWriter out = resp.getWriter();
        props.store(out, "");
        out.close();
    }
}
