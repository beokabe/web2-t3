package br.com.ufms.web.trabalho.beatriz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRO_ID", nullable = false)
    private Long id;

    @Column(name = "PRO_DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "PRO_QTD_ESTOQUE", nullable = false)
    private Long quantidadeEstoque;

    @Column(name = "PRO_IDADE_PERMITIDA", nullable = false)
    private Long idadePermitida;

    @Column(name = "PRO_PRECO_COMPRA", nullable = false)
    private BigDecimal precoCompra;

    @Column(name = "PRO_PRECO_VENDA_FISICA", nullable = false)
    private BigDecimal precoVendaFisica;

    @Column(name = "PRO_PRECO_VENDA_JURIDICA", nullable = false)
    private BigDecimal precoVendaJuridica;
}
