package dao.jdbc;

import ConfigDB.ConfigDB;
import dao.CategoriaDao;
import domain.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaDaoJdbc implements CategoriaDao {

    @Override
    public Long guardar(Categoria c) {
        final String sql = "INSERT INTO public.categorias(nombre) VALUES (?)";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNombre());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    c.setId(id);
                    return id;
                }
            }
            throw new RuntimeException("No se obtuvo ID generado para categoría");
        } catch (SQLException e) {
            // Si la categoría existe (nombre UNIQUE), el INSERT fallará
            throw new RuntimeException("Error guardando categoría", e);
        }
    }

    @Override
    public List<Categoria> listarTodas() {
        final String sql = "SELECT id, nombre FROM public.categorias ORDER BY nombre";
        List<Categoria> out = new ArrayList<>();
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Categoria(
                        rs.getLong("id"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando categorías", e);
        }
        return out;
    }

    @Override
    public Optional<Categoria> buscarPorId(long id) {
        final String sql = "SELECT id, nombre FROM public.categorias WHERE id = ?";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Categoria(
                            rs.getLong("id"),
                            rs.getString("nombre")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando categoría por id", e);
        }
        return Optional.empty();
    }
}
