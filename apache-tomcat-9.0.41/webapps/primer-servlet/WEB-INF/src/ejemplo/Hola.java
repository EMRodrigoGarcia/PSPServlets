package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Hola extends HttpServlet {
    static int visitas = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hola mundo</h1>");
        out.println("hoy es " + new Date() + "<br/>");
        out.println("Visitas: " + incrementar() + "<br/>");
        out.println("Id del hilo: " + Thread.currentThread().getId());
        out.println("</body>");
        out.println("</html>");
    }
    private synchronized int incrementar() {
        return visitas++;
    }
}