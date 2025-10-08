package service;

import dao.CategoriaDao;
import domain.Categoria;
import java.util.List;

public class CategoriaService {
    private CategoriaDao categoriaDao;

    public void setCategoriaDao(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public List<Categoria> listarTodas() {

        List<Categoria> categorias = categoriaDao.listarTodas();
        if(categorias.isEmpty()) {
            return null;
        }
        return categorias;
    }
}
