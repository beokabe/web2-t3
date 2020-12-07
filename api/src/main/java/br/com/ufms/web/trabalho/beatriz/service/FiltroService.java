package br.com.ufms.web.trabalho.beatriz.service;

import br.com.ufms.web.trabalho.beatriz.entity.Pessoa;
import br.com.ufms.web.trabalho.beatriz.entity.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Service
public class FiltroService {

    public boolean filtrarPessoaPor(Pessoa pessoa,
                                    String responsavelId,
                                    String responsavelNome,
                                    String situacao) {

        if (Objects.nonNull(situacao) && Objects.nonNull(responsavelId) && Objects.nonNull(responsavelNome) && Objects.nonNull(pessoa.getResponsavelId())) {
            return pessoa.getSituacao().toString().equals(situacao) && pessoa.getResponsavelId().getId().equals(Long.parseLong(responsavelId))
                    && pessoa.getResponsavelId().getNome().equals(responsavelNome);
        } else if (Objects.nonNull(situacao) && Objects.nonNull(responsavelId) && Objects.nonNull(pessoa.getResponsavelId())) {
            return pessoa.getSituacao().toString().equals(situacao) && pessoa.getResponsavelId().getId().equals(Long.parseLong(responsavelId));
        } else if (Objects.nonNull(situacao) && Objects.nonNull(responsavelNome) && Objects.nonNull(pessoa.getResponsavelId())) {
            return pessoa.getSituacao().toString().equals(situacao) && pessoa.getResponsavelId().getNome().equals(responsavelNome);
        } else if (Objects.nonNull(responsavelNome) && Objects.nonNull(responsavelId) && Objects.nonNull(pessoa.getResponsavelId())) {
            return pessoa.getResponsavelId().getNome().equals(responsavelNome) && pessoa.getResponsavelId().getId().equals(Long.parseLong(responsavelId));
        } else if (Objects.nonNull(situacao) && Objects.isNull(responsavelId) && Objects.isNull(responsavelNome)) {
            return pessoa.getSituacao().toString().equals(situacao);
        } else if (Objects.nonNull(responsavelId) && Objects.nonNull(pessoa.getResponsavelId()) && Objects.isNull(situacao) && Objects.isNull(responsavelNome)) {
            return pessoa.getResponsavelId().getId().equals(Long.parseLong(responsavelId));
        } else if (Objects.nonNull(responsavelNome) && Objects.nonNull(pessoa.getResponsavelId()) && Objects.isNull(situacao) && Objects.isNull(responsavelId)) {
            return pessoa.getResponsavelId().getNome().equals(responsavelNome);
        }

        return Objects.isNull(situacao) && Objects.isNull(responsavelId) && Objects.isNull(responsavelNome);
    }

    public boolean filtrarProdutoPorPessoaFisicaPor(Produto produto,
                                                    String descricao,
                                                    String precoMinimo,
                                                    String precoMaximo) {

        if (Objects.nonNull(descricao) && Objects.nonNull(precoMinimo) && Objects.nonNull(precoMaximo)) {
            return produto.getDescricao().equals(descricao) && (produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) > 0 ||
                    produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) == 0) && (produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMaximo)) < 0
                    || produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMaximo)) == 0);

        } else if (Objects.nonNull(descricao) && Objects.nonNull(precoMinimo) && Objects.isNull(precoMaximo)) {
            return produto.getDescricao().equals(descricao) && (produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) > 0 ||
                    produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) == 0);

        } else if (Objects.nonNull(descricao) && Objects.nonNull(precoMaximo) && Objects.isNull(precoMinimo)) {
            return produto.getDescricao().equals(descricao) && (produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMaximo)) < 0 ||
                    produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMaximo)) == 0);

        } else if (Objects.isNull(descricao) && Objects.nonNull(precoMaximo) && Objects.nonNull(precoMinimo)) {
            return (produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMaximo)) < 0
                    || produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMaximo)) == 0)
                    && (produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) > 0 ||
                    produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) == 0);

        } else if (Objects.nonNull(descricao) && Objects.isNull(precoMaximo) && Objects.isNull(precoMinimo)) {
            return produto.getDescricao().equals(descricao);

        } else if (Objects.nonNull(precoMinimo) && Objects.isNull(descricao) && Objects.isNull(precoMaximo)) {
            return produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) > 0 ||
                    produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) == 0;

        } else if (Objects.nonNull(precoMaximo) && Objects.isNull(descricao) && Objects.isNull(precoMinimo)) {
            return produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMaximo)) < 0
                    || produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMaximo)) == 0;
        }

        return Objects.isNull(descricao) && Objects.isNull(precoMinimo) && Objects.isNull(precoMaximo);
    }

    public boolean filtrarProdutoPorPessoaJuridicaPor(Produto produto,
                                                      String descricao,
                                                      String precoMinimo,
                                                      String precoMaximo) {
        if (Objects.nonNull(descricao) && Objects.nonNull(precoMinimo) && Objects.nonNull(precoMaximo)) {
            return (produto.getDescricao().equals(descricao) && produto.getPrecoVendaFisica().compareTo(new BigDecimal(precoMinimo)) > 0 ||
                    produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMinimo)) == 0) && (produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMaximo)) < 0
                    || produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMaximo)) == 0);

        } else if (Objects.nonNull(descricao) && Objects.nonNull(precoMinimo) && Objects.isNull(precoMaximo)) {
            return produto.getDescricao().equals(descricao) && produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMinimo)) > 0 ||
                    produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMinimo)) == 0;

        } else if (Objects.nonNull(descricao) && Objects.nonNull(precoMaximo) && Objects.isNull(precoMinimo)) {
            return produto.getDescricao().equals(descricao) && produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMaximo)) < 0 ||
                    produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMaximo)) == 0;

        } else if (Objects.isNull(descricao) && Objects.nonNull(precoMaximo) && Objects.nonNull(precoMinimo)) {
            return produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMaximo)) < 0
                    || produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMaximo)) == 0
                    && produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMinimo)) > 0 ||
                    produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMinimo)) == 0;

        } else if (Objects.nonNull(descricao) && Objects.isNull(precoMaximo) && Objects.isNull(precoMinimo)) {
            return produto.getDescricao().equals(descricao);

        } else if (Objects.nonNull(precoMinimo) && Objects.isNull(descricao) && Objects.isNull(precoMaximo)) {
            return produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMinimo)) > 0 ||
                    produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMinimo)) == 0;

        } else if (Objects.nonNull(precoMaximo) && Objects.isNull(descricao) && Objects.isNull(precoMinimo)) {
            return produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMaximo)) < 0
                    || produto.getPrecoVendaJuridica().compareTo(new BigDecimal(precoMaximo)) == 0;
        }

        return Objects.isNull(descricao) && Objects.isNull(precoMinimo) && Objects.isNull(precoMaximo);
    }

    public boolean filtrarPessoasMaioresDeIdade(Pessoa pessoa, Long id) {
        if (Objects.nonNull(id) && Objects.nonNull(pessoa)) {
            int idade = Period.between(pessoa.getDataNascimento(), LocalDate.now()).getYears();
            return idade >= 18 && !pessoa.getId().equals(id);
        }
        return false;
    }
}
