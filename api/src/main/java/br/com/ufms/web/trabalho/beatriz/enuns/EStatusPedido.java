package br.com.ufms.web.trabalho.beatriz.enuns;

import lombok.Getter;

@Getter
public enum EStatusPedido {
    REALIZADO("Realizado"),
    CANCELADO("Cancelado");

    private String statusPedido;

    EStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }
}
