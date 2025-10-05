package dao.jdbc;

import ConfigDB.ConfigDB;
import dao.EstadoDao;
import domain.Estado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstadoDaoJdbc implements EstadoDao {

    @Override
    public List<Estado> listarTodos() {
        final String sql = "SELECT id, nombre FROM public.estados ORDER BY id";
        List<Estado> out = new ArrayList<>();
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Estado(rs.getLong("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando estados", e);
        }
        return out;
    }

    @Override
    public Optional<Estado> buscarPorId(long id) {
        final String sql = "SELECT id, nombre FROM public.estados WHERE id = ?";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Estado(rs.getLong("id"), rs.getString("nombre")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando estado por id", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Estado> buscarPorNombre(String nombre) {
        final String sql = "SELECT id, nombre FROM public.estados WHERE nombre = ?";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Estado(rs.getLong("id"), rs.getString("nombre")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando estado por nombre", e);
        }
        return Optional.empty();
    }
}
