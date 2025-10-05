package dao;

import domain.Estado;
import java.util.List;
import java.util.Optional;

public interface EstadoDao {
    List<Estado> listarTodos();
    Optional<Estado> buscarPorId(long id);
    Optional<Estado> buscarPorNombre(String nombre);
}
