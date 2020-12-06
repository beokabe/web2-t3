package br.com.ufms.web.trabalho.beatriz.controller;

import br.com.ufms.web.trabalho.beatriz.entity.ItemPedido;
import br.com.ufms.web.trabalho.beatriz.service.ItemPedidoService;
import br.com.ufms.web.trabalho.beatriz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/item-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        loginService.loginComoUsuarioComum(login, senha);
        return new ResponseEntity(itemPedidoService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscar(@RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {
        loginService.loginComoUsuarioComum(login, senha);
        return new ResponseEntity(itemPedidoService.buscarTodos(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> inserir(@RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha,
                                     @RequestBody ItemPedido itemPedido) {
        loginService.logarComoAdministrador(login, senha);

        return new ResponseEntity(itemPedidoService.inserir(itemPedido), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha,
                                     @PathVariable("id") Long id) {
        loginService.logarComoAdministrador(login, senha);
        itemPedidoService.deletar(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestHeader("login") String login,
                                       @RequestHeader("senha") String senha,
                                       @PathVariable("id") Long id,
                                       @RequestBody ItemPedido itemPedido) {
        loginService.logarComoAdministrador(login, senha);

        return new ResponseEntity(itemPedidoService.atualizar(id, itemPedido), HttpStatus.NO_CONTENT);
    }
}
