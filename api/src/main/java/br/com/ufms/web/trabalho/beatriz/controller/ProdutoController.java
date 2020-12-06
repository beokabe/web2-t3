package br.com.ufms.web.trabalho.beatriz.controller;

import br.com.ufms.web.trabalho.beatriz.dto.ProdutoFisicoDTO;
import br.com.ufms.web.trabalho.beatriz.dto.ProdutoJuridicoDTO;
import br.com.ufms.web.trabalho.beatriz.entity.Produto;
import br.com.ufms.web.trabalho.beatriz.entity.Usuario;
import br.com.ufms.web.trabalho.beatriz.enuns.ETipoPessoa;
import br.com.ufms.web.trabalho.beatriz.service.LoginService;
import br.com.ufms.web.trabalho.beatriz.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarStream(@RequestHeader("login") String login,
                                          @RequestHeader("senha") String senha,
                                          @RequestParam(value = "descricao", required = false) String descricao,
                                          @RequestParam(value = "min", required = false) String precoMinimo,
                                          @RequestParam(value = "max", required = false) String precoMaximo) {

        Usuario usuario = loginService.loginComoUsuarioComumRetorna(login, senha);

        List<Produto> produtosPermitidosFiltradosPorIdade = loginService.retornaProdutosSeIdadeForPermitida(usuario);
        return new ResponseEntity(produtoService.buscarStream(descricao, precoMinimo, precoMaximo, usuario, produtosPermitidosFiltradosPorIdade), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> inserir(@RequestBody Produto produto,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);
        return new ResponseEntity(produtoService.inserir(produto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);
        produtoService.deletar(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {

        Usuario usuario = loginService.loginComoUsuarioComumRetorna(login, senha);

        Optional<Produto> produto = produtoService.buscarPorId(id);

        if (Objects.nonNull(produto.get())) {
            if (usuario.getPessoaId().getTipo().equals(ETipoPessoa.FISICA)) {
                ProdutoFisicoDTO fisico = ProdutoFisicoDTO.buildDTO(produto.get());
                return new ResponseEntity(fisico, HttpStatus.OK);
            } else {
                ProdutoJuridicoDTO juridico = ProdutoJuridicoDTO.buildDTO(produto.get());
                return new ResponseEntity(juridico, HttpStatus.OK);
            }
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id,
                                       @RequestBody Produto produto,
                                       @RequestHeader("login") String login,
                                       @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);

        return new ResponseEntity(produtoService.atualizar(id, produto), HttpStatus.NO_CONTENT);
    }
}
