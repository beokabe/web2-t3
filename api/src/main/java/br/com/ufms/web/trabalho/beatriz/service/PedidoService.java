package br.com.ufms.web.trabalho.beatriz.service;

import br.com.ufms.web.trabalho.beatriz.entity.Pedido;
import br.com.ufms.web.trabalho.beatriz.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido inserir(Pedido Pedido) {
        return pedidoRepository.save(Pedido);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public void deletar(Long id) {
        Pedido buscaPedido = pedidoRepository.findById(id).get();

        if (Objects.isNull(buscaPedido)) {
            throw new RuntimeException("Não é possível deletar um registro inexistente.", null);
        }

        pedidoRepository.deleteById(id);
    }

    public Pedido atualizar(Long id, Pedido pedido) {
        Pedido buscaPedido = pedidoRepository.findById(id).get();

        if (Objects.isNull(buscaPedido)) {
            throw new RuntimeException("Registro não encontrado.", null);
        }

        if (Objects.nonNull(pedido.getStatus())) {
            buscaPedido.setStatus(pedido.getStatus());
        }

        if (Objects.nonNull(pedido.getPercentualDesconto())) {
            buscaPedido.setPercentualDesconto(pedido.getPercentualDesconto());
        }

        if (Objects.nonNull(pedido.getDataCompra())) {
            buscaPedido.setDataCompra(pedido.getDataCompra());
        }

        if (Objects.nonNull(pedido.getDataEntrega())) {
            buscaPedido.setDataEntrega(pedido.getDataEntrega());
        }

        if (Objects.nonNull(pedido.getIdPessoa())) {
            buscaPedido.setIdPessoa(pedido.getIdPessoa());
        }

        return pedidoRepository.save(buscaPedido);
    }
}
