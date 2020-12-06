package br.com.ufms.web.trabalho.beatriz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ItemPedidoId.class)
@Table(name = "TB_ITEM_PED")
public class ItemPedido {

    @Id
    @Column(name = "ITEM_PED_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "PED_ID", nullable = false)
    private Pedido idPedido;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto idProduto;

    @Column(name = "ITEM_PED_QTD", nullable = false)
    private Long quantidade;
}

class ItemPedidoId implements Serializable {
    Long id;
    Pedido idPedido;
    Produto idProduto;
}
