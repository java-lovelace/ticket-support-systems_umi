package dao;

import domain.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaDao {
    Long guardar(Categoria c);                 // retorna id

    List<Categoria> listarTodas();

    Optional<Categoria> buscarPorId(long id);
}