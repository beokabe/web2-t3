package br.com.ufms.web.trabalho.beatriz.enuns;

import lombok.Getter;

@Getter
public enum ETipoPessoa {
    FISICA("Física"),
    JURIDICA("Jurídica");

    private String tipoPessoa;

    ETipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
}
