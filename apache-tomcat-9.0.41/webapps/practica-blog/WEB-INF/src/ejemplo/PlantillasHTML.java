package ejemplo;

public class PlantillasHTML {
    private static final String mHead = """ 
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mi Blog</title>
        <link rel=\"stylesheet" href="./css/bootstrap.css">
    </head>
    
    """;
    private static final String mCabecera = """
       <h1 id=\"cabecera\">
       Blog 
       </h1>
    """;

    private static final String mCarta = """
    <div class=\"card align-self-center\" style=\"width: 30rem;\">
        <div class=\"card-body\">
            <h5 class=\"card-title bg-primary text-white\">$titulo$</h5>
            <p>$fecha$</p>
            <p class=\"card-text\">$texto$</p>
        </div>
    </div>    
    """;

    private static final String mContainerStart = """
        <div class=\"container\">
        <div class=\"col\">

    """;
    private static final String mContainerEnd = """
        </div>
        </div>
    """;
    private static final String mFooter = """
    </body>
    </html>
    """;




}
