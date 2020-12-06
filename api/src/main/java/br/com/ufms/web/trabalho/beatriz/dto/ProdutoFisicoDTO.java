package br.com.ufms.web.trabalho.beatriz.dto;

import lombok.*;
import br.com.ufms.web.trabalho.beatriz.entity.Produto;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class ProdutoFisicoDTO {

    private Long id;
    private String descricao;
    private Long idadePermitida;
    private Long quantidadeEstoque;
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;

    public static ProdutoFisicoDTO buildDTO(Produto produto) {
        return new ProdutoFisicoDTO(produto.getId(), produto.getDescricao(), produto.getIdadePermitida(), produto.getQuantidadeEstoque(),
                produto.getPrecoCompra(), produto.getPrecoVendaFisica());
    }
}
