package app;

import controller.TicketController;
import controller.UsuarioController;
import dao.TicketDao;
import dao.UsuarioDao;
import dao.jdbc.TicketDaoJdbc;
import dao.jdbc.UsuarioDaoJdbc;
import service.TicketService;
import service.UsuarioService;
import view.MenuView;

public class Main {
    public static void main(String[] args) {
        // Crear DAOs
        TicketDao ticketDao = new TicketDaoJdbc();
        UsuarioDao usuarioDao = new UsuarioDaoJdbc();

        // Crear Services
        TicketService ticketService = new TicketService(ticketDao);
        UsuarioService usuarioService = new UsuarioService(usuarioDao);

        // Crear Controllers
        TicketController ticketController = new TicketController(ticketService);
        UsuarioController usuarioController = new UsuarioController(usuarioService);

        // Crear View
        MenuView menu = new MenuView(ticketController, usuarioController);
        menu.mostrarMenuPrincipal();
    }
}