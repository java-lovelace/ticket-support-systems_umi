package dao.jdbc;

import ConfigDB.ConfigDB;
import dao.TicketDao;
import domain.Ticket;
import domain.dto.CategoriaEstadisticaDto;
import domain.dto.TicketDetalleDto;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoJdbc implements TicketDao {

    @Override
    public Long crear(Ticket t) {
        final String sql = """
            INSERT INTO public.tickets (titulo, descripcion, reporter_id, categoria_id, estado_id)
            VALUES (?, ?, ?, ?, ?)
            """;
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getTitulo());
            ps.setString(2, t.getDescripcion());
            ps.setLong(3, t.getReporterId());
            ps.setLong(4, t.getCategoriaId());
            ps.setLong(5, t.getEstadoId()); // normalmente el Service pondrá 'ABIERTO'

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    t.setId(id);
                    return id;
                }
            }
            throw new RuntimeException("No se obtuvo ID generado para ticket");
        } catch (SQLException e) {
            throw new RuntimeException("Error creando ticket", e);
        }
    }

    @Override
    public void asignar(long ticketId, long assigneeId) {
        final String sql = """
            UPDATE public.tickets
               SET assignee_id = ?, fecha_asignacion = NOW(), fecha_actualizacion = NOW()
             WHERE id = ?
            """;
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, assigneeId);
            ps.setLong(2, ticketId);
            if (ps.executeUpdate() == 0) throw new RuntimeException("Ticket no encontrado");
        } catch (SQLException e) {
            throw new RuntimeException("Error asignando ticket", e);
        }
    }

    @Override
    public void cambiarEstado(long ticketId, long estadoId) {
        final String sql = """
            UPDATE public.tickets
               SET estado_id = ?, fecha_actualizacion = NOW()
             WHERE id = ?
            """;
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, estadoId);
            ps.setLong(2, ticketId);
            if (ps.executeUpdate() == 0) throw new RuntimeException("Ticket no encontrado");
        } catch (SQLException e) {
            throw new RuntimeException("Error cambiando estado", e);
        }
    }

    @Override
    public void agregarComentario(long ticketId, String texto) {
        final String sql = "INSERT INTO public.comentarios (ticket_id, texto) VALUES (?, ?)";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, ticketId);
            ps.setString(2, texto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error agregando comentario", e);
        }
    }

    @Override
    public List<TicketDetalleDto> listarPorAssignee(long assigneeId) {
        final String sql = """
            SELECT t.id, t.titulo, r.nombre AS reporter, c.nombre AS categoria, e.nombre AS estado, t.fecha_creacion
              FROM public.tickets t
              JOIN public.usuarios r ON r.id = t.reporter_id
              JOIN public.categorias c ON c.id = t.categoria_id
              JOIN public.estados e    ON e.id = t.estado_id
             WHERE t.assignee_id = ?
             ORDER BY t.fecha_creacion DESC
            """;
        List<TicketDetalleDto> out = new ArrayList<>();
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, assigneeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new TicketDetalleDto(
                            rs.getLong("id"),
                            rs.getString("titulo"),
                            rs.getString("reporter"),
                            rs.getString("categoria"),
                            rs.getString("estado"),
                            rs.getObject("fecha_creacion", OffsetDateTime.class)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando por assignee", e);
        }
        return out;
    }

    @Override
    public List<TicketDetalleDto> listarPorEstadoYCategoria(Long estadoId, Long categoriaId) {
        // Filtros opcionales: si vienen null -> no filtra por ese campo
        StringBuilder sb = new StringBuilder("""
            SELECT t.id, t.titulo, r.nombre AS reporter, c.nombre AS categoria, e.nombre AS estado, t.fecha_creacion
              FROM public.tickets t
              JOIN public.usuarios r ON r.id = t.reporter_id
              JOIN public.categorias c ON c.id = t.categoria_id
              JOIN public.estados e    ON e.id = t.estado_id
             WHERE 1=1
            """);
        List<Object> params = new ArrayList<>();
        if (estadoId != null) {
            sb.append(" AND t.estado_id = ?");
            params.add(estadoId);
        }
        if (categoriaId != null) {
            sb.append(" AND t.categoria_id = ?");
            params.add(categoriaId);
        }
        sb.append(" ORDER BY t.fecha_creacion DESC");

        List<TicketDetalleDto> out = new ArrayList<>();
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new TicketDetalleDto(
                            rs.getLong("id"),
                            rs.getString("titulo"),
                            rs.getString("reporter"),
                            rs.getString("categoria"),
                            rs.getString("estado"),
                            rs.getObject("fecha_creacion", OffsetDateTime.class)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando por estado/categoría", e);
        }
        return out;
    }

    @Override
    public List<CategoriaEstadisticaDto> topCategorias(int limit) {
        final String sql = """
            SELECT c.nombre AS categoria, COUNT(*) AS cantidad
              FROM public.tickets t
              JOIN public.categorias c ON c.id = t.categoria_id
             GROUP BY c.nombre
             ORDER BY cantidad DESC, categoria ASC
             LIMIT ?
            """;
        List<CategoriaEstadisticaDto> out = new ArrayList<>();
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new CategoriaEstadisticaDto(
                            rs.getString("categoria"),
                            rs.getLong("cantidad")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo top categorías", e);
        }
        return out;
    }
}
