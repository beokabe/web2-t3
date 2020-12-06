package br.com.ufms.web.trabalho.beatriz.service;

import br.com.ufms.web.trabalho.beatriz.entity.Produto;
import br.com.ufms.web.trabalho.beatriz.entity.Usuario;
import br.com.ufms.web.trabalho.beatriz.repository.ProdutoRepository;
import br.com.ufms.web.trabalho.beatriz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> retornaProdutosSeIdadeForPermitida(Usuario usuario) {
        List<Produto> produtos = produtoRepository.findAll();

        //Calcular idade pela data de nascimento e a data atual
        Period period = Period.between(usuario.getPessoaId().getDataNascimento(), LocalDate.now());
        int idade = period.getYears();

        return produtos.stream().filter(produto -> produto.getIdadePermitida() <= idade).collect(Collectors.toList());
    }

    public void loginComoUsuarioComum(String login, String senha) {
        Usuario us = usuarioRepository.findByLoginAndSenha(login, senha);

        if (Objects.isNull(us)) {
            throw new RuntimeException("O login ou a senha está incorreta.", null);
        }
    }

    public Usuario loginComoUsuarioComumRetorna(String login, String senha) {
        Usuario us = usuarioRepository.findByLoginAndSenha(login, senha);

        if (Objects.isNull(us)) {
            throw new RuntimeException("O login ou a senha está incorreta.", null);
        }

        return us;
    }

    public void logarComoAdministrador(String login, String senha) {
        Usuario us = usuarioRepository.findByLoginAndSenha(login, senha);

        if (Objects.isNull(us)) {
            throw new RuntimeException("O login ou a senha está incorreta.", null);
        }

        if(!us.getIsAdministrador()) {
            throw new RuntimeException("Este usuário não é administrador.", null);
        }
    }
}
