package br.com.ufms.web.trabalho.beatriz.controller;

import br.com.ufms.web.trabalho.beatriz.entity.Usuario;
import br.com.ufms.web.trabalho.beatriz.service.LoginService;
import br.com.ufms.web.trabalho.beatriz.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(
            @RequestHeader("login") String login,
            @RequestHeader("senha") String senha) {
        loginService.loginComoUsuarioComum(login, senha);

        return new ResponseEntity(usuarioService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        loginService.loginComoUsuarioComum(login, senha);

        return new ResponseEntity(usuarioService.buscarId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> inserir(@RequestBody Usuario login) {
        return new ResponseEntity(usuarioService.inserir(login), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);
        usuarioService.deletar(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id,
                                       @RequestBody Usuario usuario,
                                       @RequestHeader("login") String login,
                                       @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);

        return new ResponseEntity(usuarioService.atualizar(id, usuario), HttpStatus.NO_CONTENT);
    }
}
