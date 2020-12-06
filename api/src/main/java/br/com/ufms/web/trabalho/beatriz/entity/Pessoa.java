package br.com.ufms.web.trabalho.beatriz.entity;

import br.com.ufms.web.trabalho.beatriz.enuns.ESituacaoPessoa;
import br.com.ufms.web.trabalho.beatriz.enuns.ETipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @Column(name = "PE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "PE_RES_ID")
    private Pessoa responsavelId;

    @Column(name = "PE_TIPO_PESSOA", nullable = false)
    @Enumerated(EnumType.STRING)
    private ETipoPessoa tipo;

    @Column(name = "PE_SITUACAO")
    @Enumerated(EnumType.STRING)
    private ESituacaoPessoa situacao;

    @Column(name = "PE_NOME", nullable = false)
    private String nome;

    @Column(name = "PE_APELIDO", nullable = false)
    private String apelido;

    @Column(name = "PE_DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Transient
    private Long idade;

    public Pessoa(Long id) {
        this.id = id;
    }
}
