package util;

import domain.Usuario;

public class AuthUtil {

    // Simula un usuario autenticado (en apps grandes usarías sesión o token)
    private static Usuario usuarioActual;

    public static void login(Usuario usuario) {
        usuarioActual = usuario;
        System.out.println("Sesión iniciada como: " + usuario.getNombre() + " (" + usuario.getRol() + ")");
    }

    public static void logout() {
        usuarioActual = null;
        System.out.println("Sesión cerrada con un candado traido directamente desde lo más alto de los cielos.");
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static boolean tieneRol(String rol) {
        if (usuarioActual == null) return false;
        return usuarioActual.getRol().equalsIgnoreCase(rol);
    }

    public static boolean estaLogueado() {
        return usuarioActual != null;
    }
}
