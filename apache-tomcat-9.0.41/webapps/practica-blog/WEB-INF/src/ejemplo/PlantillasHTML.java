package ejemplo;

public class PlantillasHTML {
    public static final String mHead = 
    "<!DOCTYPE html>"+
    "<html lang=\"en\">"+
    "<head>"+
        "<meta charset=\"UTF-8\">"+
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
        "<title>Mi Blog</title>"+
        "<link rel=\"stylesheet\" href=\"./css/bootstrap.css\">"+
    "</head>" +
    "<body>";
    public static final String mCabecera = "<h1 id=\"cabecera\" class=\"bg-success text-white m-3 \">"
       +"Blog "
       +"</h1>";

    public static final String mButtonLogout = "<form action=\"cerrar\">"+
    "<button type=\"submit\">Cerrar Sesion</button>"+
    "</form>";

      public static final String mButtonLogin = "<form action=\"iniciosesion\" method=\"get\">"+
    "<button type=\"submit\">Login</button>"+
    "</form>";

    public static final String mCarta = "<div class=\"card align-self-center\" style=\"width: 30rem;\">"
        +"<div class=\"card-body\">"
            +"<h5 class=\"card-title bg-primary text-white\"> $titulo$ </h5>"
            +"<p> $fecha$ </p>"
            +"<p class=\"card-text\"> $texto$ </p>"
        +"</div>"
    +"</div>";


    

    public static final String mContainerStart = "<div class=\"container\"><div class=\"col\">";
    public static final String mContainerEnd = "</div></div>";
    public static final String mFooter = "</body></html>";

    public static final String mAlertExito = "<div class=\"alert alert-success\" role=\"alert\"> $mensaje$ </div>";
    public static final String mAlertFallo = "<div class=\"alert alert-danger\" role=\"alert\"> $mensaje$ </div>";


    public static final String mLoginHead = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Login</title>    <link rel=\"stylesheet\" href=\"./css/bootstrap.css\"></head> <body>";
    public static final String mLoginForm = "<form action=\"iniciosesion\" method=\"post\">" +
        "<label for=\"user\">User: <input type=\"text\" name=\"user\" id=\"input-user\"></label>"+
        "<label for=\"password\">Password: <input type=\"password\" name=\"password\" id=\"input-password\"></label> "+
        "<label for=\"submit\"><input type=\"submit\" value=\"Login\"></label>" +
        "</form>";
    public static final String mLoginFooter = "</body> </html>";

}
