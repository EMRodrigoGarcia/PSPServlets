package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InicioSesion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
       
       try {
           Connection conexion = DB.conectar();

           DB.desconectar();
       } catch (Exception e) {
           //TODO: handle exception
           e.printStackTrace();
       } 
    }

       
}