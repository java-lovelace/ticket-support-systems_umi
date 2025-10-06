package view;

import controller.TicketController;
import util.ValidatorUtil;
import java.util.Scanner;

public class TicketView {
    private final TicketController ticketController;
    private final Scanner sc = new Scanner(System.in);

    public TicketView(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public void mostrarMenuTickets() {
        int opcion;
        do {
            System.out.println("\n--.__ MENÚ DE TICKETS __.--");
            System.out.println("1. Crear ticket");
            System.out.println("2. Asignar ticket");
            System.out.println("3. Cambiar estado");
            System.out.println("4. Listar tickets por usuario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> ticketController.manejarCreacion();
                case 2 -> ticketController.manejarAsignacion();
                case 3 -> ticketController.manejarCambioEstado();
                case 4 -> ticketController.manejarListadoPorAssignee();
                case 0 -> System.out.println("Volviendo al menú principal");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}
