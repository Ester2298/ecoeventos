package servicio;

import java.util.Scanner;
import dominio.Organizador;
import persistencia.OrganizadorDao;

public class OrganizadorServicio implements IOrganizadorServicio {
    private final Scanner sc;
    private OrganizadorDao organizadorDao;

    public OrganizadorServicio(Scanner sc) {
        this.sc = sc;
        this.organizadorDao = new OrganizadorDao();
    }

    @Override
    public Organizador hacerLogin() {
        System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contrasenia = sc.nextLine();
        Organizador organizador = organizadorDao.login(nombre, contrasenia);
        if (organizador == null) {
            System.out.println("Nombre o contraseña incorrectos.");
        }
        return organizador;
    }

    @Override
    public void registrarOrganizador() {
        System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce tu correo: ");
        String correo = sc.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contrasenia = sc.nextLine();
        System.out.print("Introduce tu teléfono: ");
        String telefono = sc.nextLine();
        Organizador organizador = new Organizador(nombre, correo, contrasenia, telefono);
        if (organizadorDao.registrar(organizador)) {
            System.out.println("Organizador registrado correctamente.");
        } else {
            System.out.println("Ya existe un organizador con ese nombre.");
        }
    }
}
