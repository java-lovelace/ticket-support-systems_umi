package dao.jdbc;

import dao.UsuarioDao;
import domain.Usuario;

public class DaoSmokeTest {
    public static void main(String[] args) {
        UsuarioDao usuarioDao = new UsuarioDaoJdbc();

        System.out.println("== Listar usuarios ==");
        usuarioDao.listar().forEach(System.out::println);

        System.out.println("== Crear uno nuevo ==");
        Usuario nuevo = new Usuario(null, "Tester DAO", "tester@example.com", "COORDINADOR");
        usuarioDao.crear(nuevo);
        System.out.println("Creado id=" + nuevo.getId());

        System.out.println("== Buscar por id ==");
        System.out.println(usuarioDao.buscarPorId(nuevo.getId()).orElse(null));

        System.out.println("== Listar por rol (COORDINADOR) ==");
        usuarioDao.listarPorRol("COORDINADOR").forEach(System.out::println);
    }
}
