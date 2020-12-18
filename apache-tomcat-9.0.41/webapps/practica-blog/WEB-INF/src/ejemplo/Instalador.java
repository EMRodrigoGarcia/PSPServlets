package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Instalador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
       
       try {
        Connection conexion = DB.conectar();
        
        // crear bases de datos
        // insertar usuario admin con password admin
        DB.crearTablaEntradas();
        DB.crearTablaUsers();
        DB.actualizarTablaUsers("admin", "admin");
        DB.desconectar();
       } catch (Exception e) {
           //TODO: handle exception
       } 
    }

       
}