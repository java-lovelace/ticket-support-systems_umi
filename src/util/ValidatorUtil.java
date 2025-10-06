package util;

import domain.Ticket;

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
        return rol.equals("reporter") || rol.equals("operator") || rol.equals("coordinator");
    }
}
