package br.com.ufms.web.trabalho.beatriz.repository;

import br.com.ufms.web.trabalho.beatriz.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);
}
