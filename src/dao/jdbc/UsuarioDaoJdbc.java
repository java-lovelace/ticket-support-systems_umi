package dao.jdbc;

import ConfigDB.ConfigDB;
import dao.UsuarioDao;
import domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDaoJdbc implements UsuarioDao {

    @Override
    public void crear(Usuario u) {
        final String sql = "INSERT INTO public.usuarios(nombre,email,password_hash,rol) VALUES (?,?,?,?)";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, "hash_demo");   // el Service luego pondrá el hash real
            ps.setString(4, u.getRol());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) u.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creando usuario", e);
        }
    }

    @Override
    public List<Usuario> listar() {
        final String sql = "SELECT id,nombre,email,rol FROM public.usuarios ORDER BY id DESC";
        List<Usuario> out = new ArrayList<>();
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Usuario(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("rol")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando usuarios", e);
        }
        return out;
    }

    @Override
    public List<Usuario> listarPorRol(String rol) {
        final String sql = "SELECT id,nombre,email,rol FROM public.usuarios WHERE rol=? ORDER BY nombre";
        List<Usuario> out = new ArrayList<>();
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Usuario(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("rol")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando usuarios por rol", e);
        }
        return out;
    }

    @Override
    public Optional<Usuario> buscarPorId(long id) {
        final String sql = "SELECT id,nombre,email,rol FROM public.usuarios WHERE id=?";
        try (Connection con = ConfigDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Usuario(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("rol")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando usuario por id", e);
        }
        return Optional.empty();
    }
}
