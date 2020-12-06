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
@Table(name = "TB_PE_FISICA")
@PrimaryKeyJoinColumn(name = "PE_ID")
public class PessoaFisica extends Pessoa {

    @Column(name = "PF_CPF", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "PF_RG", nullable = false, unique = true)
    private String rg;

    public PessoaFisica(Long id) {
        super(id);
    }
}
