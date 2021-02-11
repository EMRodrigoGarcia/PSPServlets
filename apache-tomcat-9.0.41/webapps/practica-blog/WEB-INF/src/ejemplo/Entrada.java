package ejemplo;

import java.time.LocalDate;

public class Entrada {
    public String titulo;
    private String texto;
    private LocalDate fechaPublicacion;
    private int id;

    public Entrada(int id, String titulo, String texto, LocalDate fechaPublicacion) {
        this.titulo = titulo;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + " titulo='" + getTitulo() + "'" + ", texto='" + getTexto() + "'" + ", fechaPublicacion='"
                + getFechaPublicacion() + "'" + "}";
    }

    public String getTitulo() {
        return this.titulo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getFechaPublicacion() {
        return this.fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

}
