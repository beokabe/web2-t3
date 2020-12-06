package br.com.ufms.web.trabalho.beatriz.enuns;

import lombok.Getter;

@Getter
public enum ESituacaoPessoa {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String situacao;

    ESituacaoPessoa(String situacao) {
        this.situacao = situacao;
    }
}
