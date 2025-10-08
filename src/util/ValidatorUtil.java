package util;

import domain.Ticket;
import domain.Usuario;

public class ValidatorUtil {

    // Verifica que un ticket tenga título y descripción válidos
    public static boolean validarTicket(Ticket ticket) {
        if (ticket.getTitulo() == null || ticket.getTitulo().isBlank()) {
            System.out.println("El título no puede estar vacío.");
            return false;
        }
        if (ticket.getDescripcion() == null || ticket.getDescripcion().isBlank()) {
            System.out.println("La descripción no puede estar vacía.");
            return false;
        }
        if (ticket.getReporterId() == null || ticket.getReporterId() <= 0) {
            System.out.println("El id del reporter no es valido.");
            return false;
        }
        if (ticket.getEstadoId() == null || ticket.getEstadoId() <= 0) {
            System.out.println("El id del estado no es valido.");
            return false;
        }
        if (ticket.getAssigneeId() == null || ticket.getAssigneeId() <= 0) {
            System.out.println("El id del estado no es valido.");
            return false;
        }
        if (ticket.getCategoriaId() == null || ticket.getCategoriaId() <= 0) {
            System.out.println("El id del estado no es valido.");
            return false;
        }

        return true;
    }

    // Verifica que los IDs sean positivos
    public static boolean validarId(long id) {
        if (id <= 0) {
            System.out.println("El ID debe ser mayor que 0.");
            return false;
        }
        return true;
    }

    // Verifica el rol permitido
    public static boolean validarRol(String rol) {
        if (rol == null || rol.isBlank()) return false;
        rol = rol.toLowerCase();
        return rol.equals("reporter") || rol.equals("operador") || rol.equals("coordinator") || rol.equals("assignee");
    }

    public static boolean validarUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            System.out.println("El email no puede estar vacío.");
            return false;
        }
        if (!usuario.getEmail().contains("@")) {
            System.out.println("El email debe contener '@'.");
            return false;
        }
        if (!validarRol(usuario.getRol())) {
            System.out.println("El rol ingresado no es válido (solo REPORTER, OPERADOR o COORDINATOR).");
            return false;
        }
        return true;
    }

}
