package controller;

import service.UsuarioService;
import domain.Usuario;
import java.util.List;
import java.util.Scanner;

public class UsuarioController {
    private UsuarioService usuarioService;
    private Scanner sc = new Scanner(System.in);

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void manejarRegistro() {
        System.out.println("=== Registrar Usuario ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Rol: ");
        String rol = sc.nextLine();

        usuarioService.registrarUsuario(nombre, rol);
        System.out.println("Usuario registrado correctamente.");
    }

    public void manejarListado() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        System.out.println("=== Lista de Usuarios ===");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }
}
