package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.print.event.PrintJobListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Suma extends HttpServlet {
    static int visitas = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        int n1 = Integer.parseInt(req.getParameter("N1"));
        int n2 = Integer.parseInt(req.getParameter("N2"));
        out.println("Result: " + (n1 + n2)  + "<br/>");
        out.println("</body>");
        out.println("</html>");
    }
    private synchronized int incrementar() {
        return visitas++;
    }
}