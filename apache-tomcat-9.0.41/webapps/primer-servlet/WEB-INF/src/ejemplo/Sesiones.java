package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Sesiones extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
        int n = 0;
        HttpSession sesion = req.getSession(true);
        String contador = (String) sesion.getAttribute("contador");
        if(contador != null)
            n = Integer.parseInt(contador) + 1;
        sesion.setAttribute("contador", "" + n);
        sesion.setAttribute("dato_muy_importante", "Datos importantísimos");
        
        if(n > 10)
            sesion.invalidate(); // Se cierra la sesión

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");

        out.println("Visitas " + n);

        out.println("</body>");
        out.println("</html>");
    }
}
