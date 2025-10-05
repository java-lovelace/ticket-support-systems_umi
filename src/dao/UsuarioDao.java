package dao;

import domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioDao {
    void crear(Usuario u);
    List<Usuario> listar();               // todos
    List<Usuario> listarPorRol(String rol);
    Optional<Usuario> buscarPorId(long id);
}
