package service;

import dao.CategoriaDao;
import dao.jdbc.CategoriaDaoJdbc;
import domain.Categoria;
import java.util.List;

public class CategoriaService {
    private CategoriaDao categoriaDao = new CategoriaDaoJdbc();

    public List<Categoria> listarTodas() {

        List<Categoria> categorias = categoriaDao.listarTodas();
        if(categorias.isEmpty()) {
            return null;
        }
        return categorias;
    }
}
