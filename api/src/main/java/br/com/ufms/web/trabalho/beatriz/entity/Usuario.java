package br.com.ufms.web.trabalho.beatriz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @Column(name = "US_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "US_IS_ADMINISTRADOR", nullable = false)
    private Boolean isAdministrador;

    @Column(name = "US_LOGIN", unique = true, nullable = false)
    private String login;

    @Column(name = "US_SENHA", nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "PE_ID", nullable = false)
    private Pessoa pessoaId;
}
