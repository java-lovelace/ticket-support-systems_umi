package dao.jdbc;

import dao.TicketDao;
import domain.Ticket;
import domain.dto.CategoriaEstadisticaDto;
import domain.dto.TicketDetalleDto;

import ConfigDB.ConfigDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketSmokeTest {

    // ======== Ajusta estos valores a los que tengas en tu BD ========
    private static final String reporterEmail   = "anderson@demo.com"; // REPORTER
    private static final String assigneeEmail   = "miguel@demo.com";    // OPERADOR/COORDINADOR
    private static final String categoriaNombre = "Hardware";
    private static final String estadoAbierto   = "ABIERTO";
    private static final String estadoEnProg    = "EN_PROGRESO";
    // ===============================================================

    public static void main(String[] args) {
        TicketDao dao = new TicketDaoJdbc();

        // 0) Resolver IDs reales desde la BD (sin hardcodear)
        long reporterId  = getUsuarioIdByEmail(reporterEmail);
        long assigneeId  = getUsuarioIdByEmail(assigneeEmail);
        long categoriaId = getCategoriaIdByNombre(categoriaNombre);
        long abiertoId   = getEstadoIdByNombre(estadoAbierto);
        long enProgId    = getEstadoIdByNombre(estadoEnProg);

        System.out.println("IDs resueltos => reporter=" + reporterId
                + ", assignee=" + assigneeId
                + ", categoria=" + categoriaId
                + ", ABIERTO=" + abiertoId
                + ", EN_PROGRESO=" + enProgId);

        try {
            // 1) Crear ticket
            Ticket t = new Ticket();
            t.setTitulo("Impresora no imprime");
            t.setDescripcion("Se queda en cola; el usuario ya reinició el spooler.");
            t.setReporterId(reporterId);
            t.setCategoriaId(categoriaId);
            t.setEstadoId(abiertoId);

            Long ticketId = dao.crear(t);
            System.out.println("Ticket creado id=" + ticketId);

            // 2) Asignar
            dao.asignar(ticketId, assigneeId);
            System.out.println("Asignado a usuario " + assigneeId);

            // 3) Cambiar estado
            dao.cambiarEstado(ticketId, enProgId); // EN_PROGRESO
            System.out.println("Estado => " + estadoEnProg);

            // 4) Agregar comentario
            dao.agregarComentario(ticketId, "Tomado por mesa de ayuda. Revisando driver.");
            System.out.println("Comentario agregado");

            // 5) Listar por assignee
            System.out.println("\n== Tickets del assignee " + assigneeId + " ==");
            List<TicketDetalleDto> porAssignee = dao.listarPorAssignee(assigneeId);
            porAssignee.forEach(System.out::println);

            // 6) Buscar por estado y categoría (combinaciones)
            System.out.println("\n== Buscar por estado=" + enProgId + " y categoria=" + categoriaId + " ==");
            dao.listarPorEstadoYCategoria(enProgId, categoriaId).forEach(System.out::println);

            System.out.println("\n== Buscar por estado=" + enProgId + " (solo) ==");
            dao.listarPorEstadoYCategoria(enProgId, null).forEach(System.out::println);

            System.out.println("\n== Buscar por categoría=" + categoriaId + " (solo) ==");
            dao.listarPorEstadoYCategoria(null, categoriaId).forEach(System.out::println);

            // 7) Top categorías
            System.out.println("\n== Top 3 categorías ==");
            List<CategoriaEstadisticaDto> top3 = dao.topCategorias(3);
            top3.forEach(System.out::println);

        } catch (RuntimeException ex) {
            System.err.println("Smoke test falló: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ===== Helpers: resuelven IDs reales directamente de la BD =====

    private static long getUsuarioIdByEmail(String email) {
        final String sql = "SELECT id FROM public.usuarios WHERE email = ?";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getLong(1);
                throw new RuntimeException("No existe usuario con email: " + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando usuario por email: " + email, e);
        }
    }

    private static long getCategoriaIdByNombre(String nombre) {
        final String sql = "SELECT id FROM public.categorias WHERE nombre = ?";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getLong(1);
                throw new RuntimeException("No existe categoría: " + nombre);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando categoría: " + nombre, e);
        }
    }

    private static long getEstadoIdByNombre(String nombre) {
        final String sql = "SELECT id FROM public.estados WHERE nombre = ?";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getLong(1);
                throw new RuntimeException("No existe estado: " + nombre);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando estado: " + nombre, e);
        }
    }
}
