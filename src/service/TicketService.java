package service;

import dao.TicketDao;
import dao.jdbc.TicketDaoJdbc;
import domain.Ticket;
import domain.dto.CategoriaEstadisticaDto;
import domain.dto.TicketDetalleDto;
import util.ValidatorUtil;

import java.util.List;

public class TicketService {
    private final TicketDao ticketDao = new TicketDaoJdbc();
    private ValidatorUtil validatorUtil;

    public TicketService() {}

    // Crear un nuevo ticket y devolver su ID
    public Long crearTicket(Ticket ticket) {
        if (!ValidatorUtil.validarTicket(ticket)){
            return null;
        }
        return ticketDao.crear(ticket);
    }

    // Asignar un ticket a un usuario
    public boolean asignarTicket(long ticketId, long usuarioId) {
        boolean result = ticketDao.asignar(ticketId, usuarioId);
        return result;
    }

    // Cambiar el estado de un ticket
    public void cambiarEstado(long ticketId, long estadoId) {
        ticketDao.cambiarEstado(ticketId, estadoId);
    }

    // Agregar un comentario a un ticket
    public void agregarComentario(long ticketId, String texto) {
        ticketDao.agregarComentario(ticketId, texto);
    }

    // Listar tickets por usuario asignado (JOIN)
    public List<TicketDetalleDto> listarPorAssignee(long assigneeId) {
        return ticketDao.listarPorAssignee(assigneeId);
    }

    // Buscar tickets por estado y categoría (JOIN + filtros)
    public List<TicketDetalleDto> buscarPorEstadoYCategoria(Long estadoId, Long categoriaId) {
        return ticketDao.listarPorEstadoYCategoria(estadoId, categoriaId);
    }

    // Top categorías (GROUP BY)
    public List<CategoriaEstadisticaDto> obtenerTopCategorias(int limite) {
        return ticketDao.topCategorias(limite);
    }
}
