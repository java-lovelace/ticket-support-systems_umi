package service;

import dao.UsuarioDao;
import dao.jdbc.UsuarioDaoJdbc;
import domain.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioDao usuarioDao = new UsuarioDaoJdbc();

    // Registrar un nuevo usuario
    public Usuario registrarUsuario(Usuario usuario) {


        if (!util.ValidatorUtil.validarUsuario(usuario)) {
            System.out.println("No se pudo registrar el usuario. Datos inválidos.");
            return null;
        }

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
