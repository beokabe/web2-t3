package br.com.ufms.web.trabalho.beatriz.entity;

import br.com.ufms.web.trabalho.beatriz.enuns.EStatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

    @Id
    @Column(name = "PED_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PE_ID", nullable = false)
    private Pessoa idPessoa;

    @Column(name = "PED_STATUS", nullable = false)
    private EStatusPedido status;

    @Column(name = "PED_DATA_COMPRA", nullable = false)
    private Date dataCompra;

    @Column(name = "PED_DATA_ENTREGA", nullable = false)
    private Date dataEntrega;

    @Column(name = "PED_DESCONTO", nullable = false)
    private Double percentualDesconto;
}
