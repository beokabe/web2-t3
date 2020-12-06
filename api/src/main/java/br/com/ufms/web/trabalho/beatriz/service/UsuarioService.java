package br.com.ufms.web.trabalho.beatriz.service;

import br.com.ufms.web.trabalho.beatriz.entity.Usuario;
import br.com.ufms.web.trabalho.beatriz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario inserir(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletar(Long id) {
        Usuario buscaUsuario = usuarioRepository.findById(id).get();

        if (Objects.isNull(buscaUsuario)) {
            throw new RuntimeException("Não é possível deletar um registro inexistente", null);
        }

        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        Usuario buscaUsuario = usuarioRepository.findById(id).get();

        if (Objects.isNull(buscaUsuario)) {
            throw new RuntimeException("Registro não encontrado.", null);
        }

        if (Objects.nonNull(usuario.getIsAdministrador())) {
            buscaUsuario.setIsAdministrador(usuario.getIsAdministrador());
        }

        if (Objects.nonNull(usuario.getLogin())) {
            buscaUsuario.setLogin(usuario.getLogin());
        }

        if (Objects.nonNull(usuario.getSenha())) {
            buscaUsuario.setSenha(usuario.getSenha());
        }

        return usuarioRepository.save(buscaUsuario);
    }
}
