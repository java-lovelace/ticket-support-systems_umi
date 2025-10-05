package dao;

import domain.Ticket;
import domain.dto.CategoriaEstadisticaDto;
import domain.dto.TicketDetalleDto;

import java.util.List;

public interface TicketDao {
    Long crear(Ticket t);                                          // inserta y retorna id
    void asignar(long ticketId, long assigneeId);                  // set assignee + fecha_asignacion
    void cambiarEstado(long ticketId, long estadoId);              // set estado + fecha_actualizacion
    void agregarComentario(long ticketId, String texto);           // inserta comentario

    List<TicketDetalleDto> listarPorAssignee(long assigneeId);     // JOIN con usuarios/categorias/estados
    List<TicketDetalleDto> listarPorEstadoYCategoria(Long estadoId, Long categoriaId); // filtros opcionales
    List<CategoriaEstadisticaDto> topCategorias(int limit);        // GROUP BY
}
