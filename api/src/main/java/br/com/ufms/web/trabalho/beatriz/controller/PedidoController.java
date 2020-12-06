package br.com.ufms.web.trabalho.beatriz.controller;

import br.com.ufms.web.trabalho.beatriz.entity.Pedido;
import br.com.ufms.web.trabalho.beatriz.service.LoginService;
import br.com.ufms.web.trabalho.beatriz.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {

        loginService.loginComoUsuarioComum(login, senha);

        return new ResponseEntity(pedidoService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscar(@RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {
        loginService.loginComoUsuarioComum(login, senha);

        return new ResponseEntity(pedidoService.buscarTodos(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> inserir(@RequestBody Pedido pedido,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);

        return new ResponseEntity(pedidoService.inserir(pedido), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);
        pedidoService.deletar(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id,
                                       @RequestBody Pedido pedido,
                                       @RequestHeader("login") String login,
                                       @RequestHeader("senha") String senha) {
        loginService.logarComoAdministrador(login, senha);

        return new ResponseEntity(pedidoService.atualizar(id, pedido), HttpStatus.NO_CONTENT);
    }
}
