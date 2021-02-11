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

public class RPCHora extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
	Properties props = new Properties();

	HttpSession sesion = req.getSession(false);
	if(sesion == null) {
		if(req.getParameter("usuario").equals("admin") 
		   && req.getParameter("password").equals("admin")) {
			sesion = req.getSession(true);
		} else
			props.setProperty("error", "Acceso denegado");
	}
	
	if(sesion != null) {
		String accion = req.getParameter("accion");
		
		if(accion.equals("getHora"))
			props.setProperty("getHora", "" + (new Date()).getTime());
	}
	
	PrintWriter out = resp.getWriter();
    props.store(out, "");
	out.close();
    }
}

