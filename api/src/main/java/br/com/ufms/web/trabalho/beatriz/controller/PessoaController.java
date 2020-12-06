package br.com.ufms.web.trabalho.beatriz.controller;

import br.com.ufms.web.trabalho.beatriz.entity.Pessoa;
import br.com.ufms.web.trabalho.beatriz.pojo.PessoaPojo;
import br.com.ufms.web.trabalho.beatriz.service.LoginService;
import br.com.ufms.web.trabalho.beatriz.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/todos")
    @ResponseBody
    public ResponseEntity<?> buscarTodos(@RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        loginService.loginComoUsuarioComum(login, senha);

        return new ResponseEntity(pessoaService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarStream(@RequestHeader("login") String login,
                                          @RequestHeader("senha") String senha,
                                          @RequestParam(value = "res", required = false) String responsavelId,
                                          @RequestParam(value = "resnome", required = false) String responsavelNome,
                                          @RequestParam(value = "tipo", required = false) String tipo,
                                          @RequestParam(value = "situacao", required = false) String situacao) {
        loginService.loginComoUsuarioComum(login, senha);

        return new ResponseEntity(pessoaService.buscaStream(responsavelId, responsavelNome, tipo, situacao), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        loginService.loginComoUsuarioComum(login, senha);

        return new ResponseEntity(pessoaService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> inserir(@RequestBody PessoaPojo pessoa) {
        return new ResponseEntity(pessoaService.inserir(pessoa), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);
        pessoaService.deletar(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id,
                                       @RequestBody Pessoa pessoa,
                                       @RequestHeader("login") String login,
                                       @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);

        return new ResponseEntity(pessoaService.atualizar(id, pessoa), HttpStatus.NO_CONTENT);
    }
}
