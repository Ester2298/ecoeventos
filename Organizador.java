package dominio;

public class Organizador extends Usuario {
	private String telefono;

    public Organizador(String nombre, String correo, String constrasenia, String telefono) {
        super(nombre, correo, constrasenia);
        this.telefono = telefono;
    }

    public Evento organizarEvento(String nombre, String descripcion, String ubicacion, String telefono) {
        return null;
    }

    public String getTelefono() { return telefono; }
	
}
