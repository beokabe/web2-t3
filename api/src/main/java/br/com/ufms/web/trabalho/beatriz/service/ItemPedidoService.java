package br.com.ufms.web.trabalho.beatriz.service;

import br.com.ufms.web.trabalho.beatriz.entity.ItemPedido;
import br.com.ufms.web.trabalho.beatriz.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> buscarTodos() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido inserir(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public Optional<ItemPedido> buscarPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    public void deletar(Long id) {
        ItemPedido buscaItemPedido = itemPedidoRepository.findById(id).get();

        if (Objects.isNull(buscaItemPedido)) {
            throw new RuntimeException("Não é possível deletar um registro inexistente.", null);
        }

        itemPedidoRepository.deleteById(id);
    }

    public ItemPedido atualizar(Long id, ItemPedido itemPedido) {
        ItemPedido buscaItemPedido = itemPedidoRepository.findById(id).get();

        if (Objects.isNull(buscaItemPedido)) {
            throw new RuntimeException("Registro não encontrado.", null);
        }

        if (Objects.nonNull(itemPedido.getIdPedido())) {
            buscaItemPedido.setQuantidade(itemPedido.getQuantidade());
        }

        return itemPedidoRepository.save(itemPedido);
    }
}
