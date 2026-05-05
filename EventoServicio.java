package servicio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

import dominio.Categoria;
import dominio.Evento;
import dominio.Organizador;
import dominio.Usuario;
import persistencia.CategoriaDao;
import persistencia.EventoDao;

public class EventoServicio implements IEventoServicio {
    private final Scanner sc;
    private EventoDao eventoDao;
    private CategoriaDao categoriaDao;

    public EventoServicio(Scanner sc) {
        this.sc = sc;
        this.eventoDao = new EventoDao();
        this.categoriaDao = new CategoriaDao();
    }

    @Override
    public void mostrarEventos() {
        HashMap<String, Evento> eventos = eventoDao.obtenerEventos();
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos disponibles.");
        } else {
            for (Evento e : eventos.values()) {
                System.out.println("- " + e.getNombre() + " | " + e.getFecha() + " " + e.getHora() + " | " + e.getUbicacion());
            }
        }
    }

    @Override
    public void mostrarEventosUsuario(Usuario usuario) {
        if (usuario.getEventos().isEmpty()) {
            System.out.println("No estás inscrito en ningún evento.");
        } else {
            for (Evento e : usuario.getEventos()) {
                System.out.println("- " + e.getNombre() + " | " + e.getFecha());
            }
        }
    }

    @Override
    public void inscribirUsuario(Usuario usuario) {
        mostrarEventos();
        System.out.print("Introduce el nombre del evento: ");
        String nombre = sc.nextLine();
        Evento evento = eventoDao.obtenerEventos().get(nombre);
        if (evento == null) {
            System.out.println("Evento no encontrado.");
        } else {
            usuario.inscribirEvento(evento);
            evento.agregarAsistente(usuario);
            System.out.println("Inscripción realizada correctamente.");
        }
    }

    @Override
    public void cancelarInscripcion(Usuario usuario) {
        mostrarEventosUsuario(usuario);
        System.out.print("Introduce el nombre del evento a cancelar: ");
        String nombre = sc.nextLine();
        Evento evento = eventoDao.obtenerEventos().get(nombre);
        if (evento == null) {
            System.out.println("Evento no encontrado.");
        } else {
            usuario.cancelarInscripcion(evento);
            evento.eliminarAsistente(usuario);
            System.out.println("Inscripción cancelada correctamente.");
        }
    }

    @Override
    public void mostrarEventosOrganizador(Organizador organizador) {
        HashMap<String, Evento> eventos = eventoDao.obtenerEventos();
        boolean encontrado = false;
        for (Evento e : eventos.values()) {
            if (e.getOrganizador().getNombre().equals(organizador.getNombre())) {
                System.out.println("- " + e.getNombre() + " | " + e.getFecha());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No tienes eventos creados.");
        }
    }

    @Override
    public void crearEvento(Organizador organizador) {
        System.out.print("Nombre del evento: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine());
        System.out.print("Hora (HH:MM): ");
        LocalTime hora = LocalTime.parse(sc.nextLine());
        System.out.print("Duración (minutos): ");
        int duracion = Integer.parseInt(sc.nextLine());
        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();

        System.out.println("Categorías disponibles:");
        for (String cat : categoriaDao.obtenerCategorias().keySet()) {
            System.out.println("- " + cat);
        }
        System.out.print("Introduce la categoría: ");
        String nombreCategoria = sc.nextLine();
        Categoria categoria = categoriaDao.obtenerCategoria(nombreCategoria);

        if (categoria == null) {
            System.out.println("Categoría no encontrada, evento no creado.");
            return;
        }

        Evento evento = new Evento(nombre, descripcion, fecha, hora, duracion, ubicacion, categoria, organizador);
        if (eventoDao.insertarEvento(evento)) {
            System.out.println("Evento creado correctamente.");
        } else {
            System.out.println("Ya existe un evento con ese nombre.");
        }
    }
}