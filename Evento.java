package dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

public class Evento {
	private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;
    private int duracion;
    private String ubicacion;
    private Categoria categoria;
    private Organizador organizador;
    private HashSet<Usuario> asistentes = new HashSet<>();

    public Evento(String nombre, String descripcion, LocalDate fecha, LocalTime hora,
                  int duracion, String ubicacion, Categoria categoria, Organizador organizador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.organizador = organizador;
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public int getDuracion() { return duracion; }
    public String getUbicacion() { return ubicacion; }
    public Categoria getCategoria() { return categoria; }
    public Organizador getOrganizador() { return organizador; }
    public HashSet<Usuario> getAsistentes() { return asistentes; }

    public void agregarAsistente(Usuario usuario) { asistentes.add(usuario); }
    public void eliminarAsistente(Usuario usuario) { asistentes.remove(usuario); }
	
}
