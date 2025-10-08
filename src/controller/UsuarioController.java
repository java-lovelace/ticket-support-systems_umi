package controller;

import domain.Categoria;
import service.CategoriaService;
import service.UsuarioService;
import domain.Usuario;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class UsuarioController {
    private UsuarioService usuarioService;
    private CategoriaService categoriaService;
    private Scanner sc = new Scanner(System.in);

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void manejarRegistro() {
        System.out.println("=== Registrar Usuario ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Rol (REPORTER, OPERADOR o COORDINATOR): ");
        String rol = sc.nextLine().toUpperCase();
        System.out.println("== REPORTERS ==\n");
        List<Usuario> usuarios =  usuarioService.listarPorRol("REPORTER");
        System.out.println(usuarios);
        System.out.println("Ingrese el id del reporter:");
        Long id = sc.nextLong();
        System.out.println("== CATEGORIAS ==");
        List<Categoria> categorias = categoriaService.listarTodas();
        System.out.println(categorias);
        System.out.println("Ingrese el id de la categoria:");
        Long categoriaId = sc.nextLong();

        Usuario user = usuarioService.registrarUsuario(nombre , email , rol);
        if (!(user == null)){System.out.println("Usuario registrado correctamente.");}
    }

    public void manejarListado() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        System.out.println("=== Lista de Usuarios ===");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }
}
