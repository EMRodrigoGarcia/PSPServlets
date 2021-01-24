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
       +" $mensaje$ "
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


    public static final String mPanelBotonesSuperior = " <ul>"+
    "<li>"+
        "<a href=\"cerrar\">Cerrar Sesión</a>"+
    "</li>"+
    "<li>"+
        "<a href=\"blog\">Ir al blog</a>"+
    "</li>"+
"</ul>";
    public static final String mButtonPanel =  "<form action=\"panelcontrol\" method=\"GET\">"+
    "<button type=\"submit\">Panel de Control</button>"+
    "</form>";

    public static final String mPanelFormContrasena =  "<h2>Cambiar contraseña</h2>"+
    "<form action=\"panelcontrol\" method=\"POST\">"+
        "<label for=\"password\">Contraseña:<input type=\"password\" name=\"password\" id=\"password\"></label>"+
        "<label for=\"newpassword\">Nueva contraseña:<input type=\"password\" name=\"newpassword\" id=\"newpassword\"></label>"+
        "<input type=\"submit\" value=\"Cambiar contraseña\">"+
    "</form>";

    public static final String mPanelCrear= 
            "<a href=\"editor\"> Crear una nueva entrada </a>";

    public static final String mEditorForm = "<form action=\"editor\" method=\"post\">"+
        "<ul class=\"list-unstyled\">"+
            "<li>"+
                "<input type=\"text\" name=\"titulo\" id=\"titulo\">"+
            "</li>"+
            "<li>"+

                "<textarea name=\"texto\" id=\"texto\" cols=\"30\" rows=\"10\"></textarea>"+
            "</li>"+

            "<li>"+

                "<input type=\"date\" name=\"fecha\" id=\"fecha\">"+
            "</li>"+

            "<li>"+

                "<input type=\"submit\" value=\"Guardar\">"+
            "</li>"+
        "</ul>"+
    "</form>";


    public static final String mPanelEntradasBegin = "<h1> Entradas </h1>" + 
    "<div class=\"d-inline-flex p-3\">" +
    "<ul class=\"list-group\">";

    public static final String mPanelEntradasItem = "<li class=\"list-group-item\">"+
            " $titulo$ " +
            "<a href=\" $borrar$ \">Borrar</a>"+
        "</li>";
    
    public static final String mPanelEntradasEnd = "</ul> </div>";

    
}