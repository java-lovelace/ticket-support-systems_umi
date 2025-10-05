package service;

import dao.UsuarioDao;
import domain.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioDao usuarioDao;

    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    // Registrar un nuevo usuario
    public Usuario registrarUsuario(String nombre, String rol) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setRol(rol);
        usuarioDao.crear(usuario);
        return usuario;
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioDao.listar();
    }

    // Listar usuarios por rol
    public List<Usuario> listarPorRol(String rol) {
        return usuarioDao.listarPorRol(rol);
    }

    // Buscar usuario por ID
    public Optional<Usuario> buscarPorId(long id) {
        return usuarioDao.buscarPorId(id);
    }
}
