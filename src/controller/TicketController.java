package controller;

import domain.Categoria;
import domain.Estado;
import domain.Usuario;
import service.CategoriaService;
import service.EstadoService;
import service.TicketService;
import domain.Ticket;
import service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class TicketController {
    private final TicketService ticketService = new TicketService();
    private UsuarioService usuarioService = new UsuarioService();
    private CategoriaService categoriaService = new CategoriaService();
    private EstadoService estadoService = new EstadoService();
    private final Scanner sc = new Scanner(System.in);

    // Crear un nuevo ticket
    public void manejarCreacion() {
        System.out.println("\nCrear Ticket");
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.println("== REPORTERS ==\n");
        List<Usuario> reportes =  usuarioService.listarPorRol("REPORTER");
        System.out.println(reportes);
        System.out.println("Ingrese el id del Reporter:");
        Long reporterId = sc.nextLong();

        System.out.println("== CATEGORIAS ==");
        List<Categoria> categorias = categoriaService.listarTodas();
        System.out.println(categorias);
        System.out.println("Ingrese el id de la Categoria:");
        Long categoriaId = sc.nextLong();

        System.out.println("== ESTADO ==");
        List<Estado> estados = estadoService.listarTodos();
        System.out.println(estados);
        System.out.println("Ingrese el id del Estado: ");
        Long estadoId = sc.nextLong();

//        System.out.println("== ASSIGNEE ==");
//        List<Usuario> usuarios = usuarioService.listarPorRol("ASSIGNEE");
//        System.out.println(usuarios);
//        System.out.println("Ingresa el id del Assignee (opcional ingresa cero parano ingresar usuario):");
//        Long assigneeId = sc.nextLong();

        Ticket ticket = new Ticket();
        ticket.setTitulo(titulo);
        ticket.setDescripcion(descripcion);
        ticket.setReporterId(reporterId);
        ticket.setCategoriaId(categoriaId);
//        if (assigneeId == 0) {
//            assigneeId = ticket.getAssigneeId();
//        }
//        System.out.println(assigneeId + "  :::holaa");
//        ticket.setAssigneeId(assigneeId);
        ticket.setEstadoId(estadoId);


        Long result = ticketService.crearTicket(ticket);
        if (!(result == null)) {
            System.out.println("Ticket creado correctamente.\n");
        }
    }

    // Asignar ticket
    public void manejarAsignacion() {
        System.out.println("\nAsignar Ticket");
        System.out.print("ID del Ticket: ");
        long idTicket = sc.nextLong();

        System.out.println("== ASSIGNEE ==");
        List<Usuario> usuarios = usuarioService.listarPorRol("ASSIGNEE");
        System.out.println(usuarios);
        System.out.println("Ingresa el id del Assignee (opcional):");
        long idUsuario = sc.nextLong();
        sc.nextLine(); // limpiar buffer

        boolean result  = ticketService.asignarTicket(idTicket, idUsuario);
        if (result) {
            System.out.println("Ticket asignado correctamente.\n");
        }
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
