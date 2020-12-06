package br.com.ufms.web.trabalho.beatriz.service;

import br.com.ufms.web.trabalho.beatriz.dto.ProdutoFisicoDTO;
import br.com.ufms.web.trabalho.beatriz.dto.ProdutoJuridicoDTO;
import br.com.ufms.web.trabalho.beatriz.entity.Pessoa;
import br.com.ufms.web.trabalho.beatriz.entity.Produto;
import br.com.ufms.web.trabalho.beatriz.entity.Usuario;
import br.com.ufms.web.trabalho.beatriz.enuns.ETipoPessoa;
import br.com.ufms.web.trabalho.beatriz.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FiltroService filtroService;

    public List<?> buscarStream(String descricao,
                                String precoMinimo,
                                String precoMaximo,
                                Usuario usuario,
                                List<Produto> produtos) {
        Stream<?> busca;

        Pessoa usuarioPessoa = usuario.getPessoaId();

        if (usuario.getIsAdministrador().equals(true)) {
            busca = produtos.stream()
                    .filter(produto -> filtroService.filtrarProdutoPorPessoaFisicaPor(produto, descricao, precoMinimo, precoMaximo))
                    .sorted(Comparator.comparing(Produto::getId));
        } else if (usuarioPessoa.getTipo().equals(ETipoPessoa.FISICA)) {
            busca = produtos.stream()
                    .filter(produto -> filtroService.filtrarProdutoPorPessoaFisicaPor(produto, descricao, precoMinimo, precoMaximo))
                    .map(ProdutoFisicoDTO::buildDTO)
                    .sorted(Comparator.comparing(ProdutoFisicoDTO::getId));
        } else {
            busca = produtos.stream()
                    .filter(produto -> filtroService.filtrarProdutoPorPessoaJuridicaPor(produto, descricao, precoMinimo, precoMaximo))
                    .map(ProdutoJuridicoDTO::buildDTO)
                    .sorted(Comparator.comparing(ProdutoJuridicoDTO::getId));
        }

        return busca.collect(Collectors.toList());
    }

    public Produto inserir(Produto Pedido) {
        return produtoRepository.save(Pedido);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public void deletar(Long id) {
        Produto buscaProduto = produtoRepository.findById(id).get();

        if (Objects.isNull(buscaProduto)) {
            throw new RuntimeException("Não é possível deletar um registro inexistente.", null);
        }

        produtoRepository.deleteById(id);
    }

    public Produto atualizar(Long id, Produto produto) {
        Produto buscaProduto = produtoRepository.findById(id).get();

        if (Objects.isNull(buscaProduto)) {
            throw new RuntimeException("Registro não encontrado.", null);
        }

        if (Objects.nonNull(produto.getDescricao())) {
            buscaProduto.setDescricao(produto.getDescricao());
        }

        if (Objects.nonNull(produto.getQuantidadeEstoque())) {
            buscaProduto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        }

        if (Objects.nonNull(produto.getIdadePermitida())) {
            buscaProduto.setIdadePermitida(produto.getIdadePermitida());
        }

        if (Objects.nonNull(produto.getPrecoCompra())) {
            buscaProduto.setPrecoCompra(produto.getPrecoCompra());
        }

        if (Objects.nonNull(produto.getPrecoVendaFisica())) {
            buscaProduto.setPrecoVendaFisica(produto.getPrecoVendaFisica());
        }

        if (Objects.nonNull(produto.getPrecoVendaJuridica())) {
            buscaProduto.setPrecoVendaFisica(produto.getPrecoVendaJuridica());
        }

        return produtoRepository.save(buscaProduto);
    }
}
