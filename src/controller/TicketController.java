package controller;

import service.TicketService;
import domain.Ticket;

import java.util.Scanner;

public class TicketController {
    private final TicketService ticketService;
    private final Scanner sc = new Scanner(System.in);

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Crear un nuevo ticket
    public void manejarCreacion() {
        System.out.println("\nCrear Ticket");
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        Ticket ticket = new Ticket();
        ticket.setTitulo(titulo);
        ticket.setDescripcion(descripcion);

        ticketService.crearTicket(ticket);
        System.out.println("Ticket creado correctamente.\n");
    }

    // Asignar ticket
    public void manejarAsignacion() {
        System.out.println("\nAsignar Ticket");
        System.out.print("ID del Ticket: ");
        long idTicket = sc.nextLong();
        System.out.print("ID del Usuario a asignar: ");
        long idUsuario = sc.nextLong();
        sc.nextLine(); // limpiar buffer

        ticketService.asignarTicket(idTicket, idUsuario);
        System.out.println("Ticket asignado correctamente.\n");
    }

    // Cambiar estado
    public void manejarCambioEstado() {
        System.out.println("\nCambiar Estado del Ticket");
        System.out.print("ID del Ticket: ");
        long idTicket = sc.nextLong();
        System.out.print("ID del nuevo Estado: ");
        long idEstado = sc.nextLong();
        sc.nextLine();

        ticketService.cambiarEstado(idTicket, idEstado);
        System.out.println("Estado actualizado correctamente.\n");
    }

    // Mostrar los tickets de un usuario (por assignee)
    public void manejarListadoPorAssignee() {
        System.out.println("\nListar Tickets por Usuario");
        System.out.print("ID del Usuario: ");
        long idUsuario = sc.nextLong();
        sc.nextLine();

        var tickets = ticketService.listarPorAssignee(idUsuario);
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets asignados a ese usuario.\n");
        } else {
            System.out.println("\n--- Tickets del Usuario ---");
            for (var t : tickets) {
                System.out.println("[" + t.id + "] " + t.titulo + " - " + t.estadoNombre);
            }
            System.out.println();
        }
    }
}
