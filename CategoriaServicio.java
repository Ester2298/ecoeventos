package servicio;

import java.util.Scanner;
import dominio.Categoria;
import persistencia.CategoriaDao;

public class CategoriaServicio implements ICategoriaServicio {
    private final Scanner sc;
    private CategoriaDao categoriaDao;

    public CategoriaServicio(Scanner sc) {
        this.sc = sc;
        this.categoriaDao = new CategoriaDao();
    }

    @Override
    public Categoria buscarCategoria() {
        System.out.print("Introduce el nombre de la categoría: ");
        String nombre = sc.nextLine();
        Categoria categoria = categoriaDao.obtenerCategoria(nombre);
        if (categoria == null) {
            System.out.println("Categoría no encontrada.");
        }
        return categoria;
    }
}