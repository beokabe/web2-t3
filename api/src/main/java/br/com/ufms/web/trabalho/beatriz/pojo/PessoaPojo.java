package br.com.ufms.web.trabalho.beatriz.pojo;

import br.com.ufms.web.trabalho.beatriz.enuns.ESituacaoPessoa;
import lombok.*;
import br.com.ufms.web.trabalho.beatriz.entity.PessoaFisica;
import br.com.ufms.web.trabalho.beatriz.entity.PessoaJuridica;
import br.com.ufms.web.trabalho.beatriz.entity.Pessoa;
import br.com.ufms.web.trabalho.beatriz.enuns.ETipoPessoa;

import java.time.LocalDate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PessoaPojo {
    private Long id;
    private Pessoa responsavelId;
    private ETipoPessoa tipo;
    private ESituacaoPessoa situacao = ESituacaoPessoa.ATIVO;
    private String nome;
    private String apelido;
    private LocalDate dataNascimento;
    private String cpf;
    private String rg;
    private String cnpj;

    public PessoaFisica gerarFisica(PessoaPojo pessoa){
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setId(pessoa.getId());
        pessoaFisica.setResponsavelId(pessoa.getResponsavelId());
        pessoaFisica.setTipo(pessoa.getTipo());
        pessoaFisica.setSituacao(pessoa.getSituacao());
        pessoaFisica.setNome(pessoa.getNome());
        pessoaFisica.setApelido(pessoa.getApelido());
        pessoaFisica.setDataNascimento(pessoa.getDataNascimento());
        pessoaFisica.setCpf(pessoa.getCpf());
        pessoaFisica.setRg(pessoa.getRg());

        return pessoaFisica;
    }

    public PessoaJuridica gerarJuridica(PessoaPojo pessoa){
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setId(pessoa.getId());
        pessoaJuridica.setResponsavelId(pessoa.getResponsavelId());
        pessoaJuridica.setTipo(pessoa.getTipo());
        pessoaJuridica.setSituacao(pessoa.getSituacao());
        pessoaJuridica.setNome(pessoa.getNome());
        pessoaJuridica.setApelido(pessoa.getApelido());
        pessoaJuridica.setDataNascimento(pessoa.getDataNascimento());
        pessoaJuridica.setCnpj(pessoa.getCnpj());

        return pessoaJuridica;
    }
}
