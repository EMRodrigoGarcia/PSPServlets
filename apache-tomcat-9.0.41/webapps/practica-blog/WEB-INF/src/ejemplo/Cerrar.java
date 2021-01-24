package ejemplo;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Cerrar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.getSession(true).invalidate();
            resp.sendRedirect(req.getContextPath() + "/blog");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
