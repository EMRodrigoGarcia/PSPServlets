package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Bibliotecas para conectar con SQLite
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SQLite extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
        Connection conn = null;
        StringBuffer respuesta = new StringBuffer();
        try {
            // Ruta a la base de datos. El archivo "base_datos.db".
            // Se puede indicar una ruta completa del tipo /home/usuario/... 
            String url = "jdbc:sqlite:/data/sqlite/base_datos.db";
            // Se crea la conexión a la base de datos:
		Class.forName("org.sqlite.JDBC")
			.getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    // Se crea la tabla si no existe
                    String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n"
                        + "usuario TEXT PRIMARY KEY,\n"
                        + "email TEXT\n"
                        + ");";
                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);

                    // Se insertan los datos
                    String sqlInsert = 
				"INSERT INTO usuarios(usuario, email) VALUES(?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    String usuario = req.getParameter("usuario");
                    String email = req.getParameter("email");
                    pstmt.setString(1, usuario);
                    pstmt.setString(2, email);
                    pstmt.executeUpdate();

                    // Se hace una consulta
                    String sqlSelect = "SELECT usuario, email FROM usuarios";
                    ResultSet cursor = stmt.executeQuery(sqlSelect);
                    while(cursor.next()) {
                        // Se construye la respuesta que se insertará en el HTML
                        respuesta.append(cursor.getString("usuario"));
                        respuesta.append(" ");
                        respuesta.append(cursor.getString("email"));
                        respuesta.append("<br/>");
                    }

                    // Se cierra la conexión con la base de datos
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        // Se escribe la página con la respuesta al usuario
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Usuarios:<br/>");
        out.println(respuesta.toString());
        out.println("</body>");
        out.println("</html>");
    }
}
