package view;

import controller.TicketController;
import controller.UsuarioController;
import util.AuthUtil;

import java.util.Scanner;

public class MenuView {
    private final TicketController ticketController;
    private final UsuarioController usuarioController;
    private final Scanner sc = new Scanner(System.in);

    public MenuView(TicketController ticketController, UsuarioController usuarioController) {
        this.ticketController = ticketController;
        this.usuarioController = usuarioController;
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n|°~^- SISTEMA DE TICKETS -^~°|");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Crear ticket");
            System.out.println("4. Asignar ticket");
            System.out.println("5. Cambiar estado");
            System.out.println("6. Listar tickets por usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> usuarioController.manejarRegistro();
                case 2 -> usuarioController.manejarListado();
                case 3 -> ticketController.manejarCreacion();
                case 4 -> ticketController.manejarAsignacion();
                case 5 -> ticketController.manejarCambioEstado();
                case 6 -> ticketController.manejarListadoPorAssignee();
                case 0 -> System.out.println("Saliste con éxito.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}
