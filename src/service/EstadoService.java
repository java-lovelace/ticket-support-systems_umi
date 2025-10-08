package service;

import dao.EstadoDao;
import dao.jdbc.EstadoDaoJdbc;
import domain.Estado;

import java.util.List;

public class EstadoService {
    private EstadoDao estadoDao = new EstadoDaoJdbc();

    public List<Estado> listarTodos(){
        List<Estado> estados = estadoDao.listarTodos();
        if (estados.isEmpty()) {
            return null;
        }
        return estados;
    }
}
