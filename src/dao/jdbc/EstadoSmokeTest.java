package dao.jdbc;

import dao.EstadoDao;

public class EstadoSmokeTest {
    public static void main(String[] args) {
        EstadoDao dao = new EstadoDaoJdbc();

        System.out.println("== Estados ==");
        dao.listarTodos().forEach(System.out::println);

        System.out.println("== Buscar por nombre (ABIERTO) ==");
        System.out.println(dao.buscarPorNombre("ABIERTO").orElse(null));
    }
}
