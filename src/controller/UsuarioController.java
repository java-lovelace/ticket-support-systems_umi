package controller;


import service.CategoriaService;
import service.UsuarioService;
import domain.Usuario;
import java.util.List;
import java.util.Scanner;

public class UsuarioController {
    private UsuarioService usuarioService = new UsuarioService();
    private Scanner sc = new Scanner(System.in);

    public void manejarRegistro() {
        System.out.println("=== Registrar Usuario ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Rol (REPORTER, ASSIGNEE, OPERADOR o COORDINATOR): ");
        String rol = sc.nextLine().toUpperCase();

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setRol(rol.toUpperCase());

        Usuario user = usuarioService.registrarUsuario(usuario);
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
