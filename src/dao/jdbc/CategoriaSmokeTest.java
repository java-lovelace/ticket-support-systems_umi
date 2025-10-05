package dao.jdbc;

import dao.CategoriaDao;
import domain.Categoria;

public class CategoriaSmokeTest {
    public static void main(String[] args) {
        CategoriaDao dao = new CategoriaDaoJdbc();

        System.out.println("== Listar existentes ==");
        dao.listarTodas().forEach(System.out::println);

        System.out.println("== Crear una nueva ==");
        Categoria c = new Categoria(null, "Soporte N2");
        try {
            Long id = dao.guardar(c);
            System.out.println("Guardada id=" + id);
        } catch (RuntimeException ex) {
            System.out.println("Posible duplicado (nombre UNIQUE): " + ex.getMessage());
        }

        System.out.println("== Buscar por id ==");
        dao.buscarPorId(c.getId() == null ? 1L : c.getId())
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No encontrada")
                );

        System.out.println("== Listar final ==");
        dao.listarTodas().forEach(System.out::println);
    }
}
