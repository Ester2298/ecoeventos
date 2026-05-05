package dominio;

import java.util.HashSet;

public class Usuario {
	private String nombre;
    private String correo;
    private String constrasenia;
    private HashSet<Evento> eventos = new HashSet<>();

    public Usuario(String nombre, String correo, String constrasenia) {
        this.nombre = nombre;
        this.correo = correo;
        this.constrasenia = constrasenia;
    }

    public void inscribirEvento(Evento evento) {
        eventos.add(evento);
    }

    public void cancelarInscripcion(Evento evento) {
        eventos.remove(evento);
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getConstrasenia() { return constrasenia; }
    public HashSet<Evento> getEventos() { return eventos; }
}
