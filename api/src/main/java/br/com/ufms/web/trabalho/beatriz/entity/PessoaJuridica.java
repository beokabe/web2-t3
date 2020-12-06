package br.com.ufms.web.trabalho.beatriz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PE_JURIDICA")
@PrimaryKeyJoinColumn(name = "PE_ID")
public class PessoaJuridica extends Pessoa {

    @Column(name = "PJ_CNPJ", length = 18, nullable = false, unique = true)
    private String cnpj;

    public PessoaJuridica(Long id) {
        super(id);
    }
}
