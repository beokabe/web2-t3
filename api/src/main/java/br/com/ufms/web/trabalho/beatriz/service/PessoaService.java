package br.com.ufms.web.trabalho.beatriz.service;

import br.com.ufms.web.trabalho.beatriz.entity.Pessoa;
import br.com.ufms.web.trabalho.beatriz.enuns.ETipoPessoa;
import br.com.ufms.web.trabalho.beatriz.pojo.PessoaPojo;
import br.com.ufms.web.trabalho.beatriz.repository.PessoaFisicaRepository;
import br.com.ufms.web.trabalho.beatriz.repository.PessoaJuridicaRepository;
import br.com.ufms.web.trabalho.beatriz.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private FiltroService filtroService;

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Object inserir(PessoaPojo pessoa) {

        int idade = Period.between(pessoa.getDataNascimento(), LocalDate.now()).getYears();

        if (pessoa.getTipo().equals(ETipoPessoa.FISICA)) {

            if (idade < 18) {

                if (Objects.isNull(pessoa.getResponsavelId())) {
                    throw new RuntimeException("Menores de idade devem possuir um responsável!", null);
                }

                Optional<Pessoa> pessoaResponsavel = pessoaRepository.findById(pessoa.getResponsavelId().getId());
                int idadeResponsavel = Period.between(pessoaResponsavel.get().getDataNascimento(), LocalDate.now()).getYears();

                if (idadeResponsavel < 18) {
                    throw new RuntimeException("O responsável precisa ter acima de 18 anos.", null);
                }
            }

            return pessoaFisicaRepository.save(pessoa.gerarFisica(pessoa));
        }

        return pessoaJuridicaRepository.save(pessoa.gerarJuridica(pessoa));
    }


    public List<?> buscaStream(String responsavelId,
                               String responsavelNome,
                               String tipoPessoa,
                               String situacao) {
        Stream<?> busca;

        String tipoPessoaFisica = ETipoPessoa.FISICA.toString();

        if (Objects.isNull(tipoPessoa)) {
            busca = pessoaRepository.findAll().stream()
                    .filter(pessoa -> filtroService.filtrarPessoaPor(pessoa, responsavelId, responsavelNome, situacao))
                    .sorted(Comparator.comparing(Pessoa::getId));
            return busca.collect(Collectors.toList());

        }

        if (tipoPessoa.equals(tipoPessoaFisica)) {
            busca = pessoaFisicaRepository.findAll().stream()
                    .filter(pessoa -> pessoa.getTipo().equals(ETipoPessoa.FISICA))
                    .filter(pessoa -> filtroService.filtrarPessoaPor(pessoa, responsavelId, responsavelNome, situacao))
                    .sorted(Comparator.comparing(Pessoa::getId));
        } else {
            busca = pessoaJuridicaRepository.findAll().stream()
                    .filter(pessoa -> pessoa.getTipo().equals(ETipoPessoa.JURIDICA))
                    .filter(pessoa -> filtroService.filtrarPessoaPor(pessoa, responsavelId, responsavelNome, situacao))
                    .sorted(Comparator.comparing(Pessoa::getId));
        }

        return busca.collect(Collectors.toList());
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<?> buscarResponsaveis(Long id) {

        Stream<?> busca;

        busca = pessoaRepository.findAll().stream()
                .filter(pessoa -> filtroService.filtrarPessoasMaioresDeIdade(pessoa, id))
                .sorted(Comparator.comparing(Pessoa::getId));


        return busca.collect(Collectors.toList());
    }

    public void deletar(Long id) {
        Pessoa buscaPessoa = pessoaRepository.findById(id).get();

        if (Objects.isNull(buscaPessoa)) {
            throw new RuntimeException("Não é possível deletar um registro inexistente.", null);
        }

        pessoaRepository.deleteById(id);
    }

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        Pessoa buscaPessoa = pessoaRepository.findById(id).get();

        if (Objects.isNull(buscaPessoa)) {
            throw new RuntimeException("Registro não encontrado.", null);
        }

        if (Objects.nonNull(pessoa.getNome())) {
            buscaPessoa.setNome(pessoa.getNome());
        }

        if (Objects.nonNull(pessoa.getResponsavelId())) {
            buscaPessoa.setResponsavelId(pessoa.getResponsavelId());
        }

        if (Objects.nonNull(pessoa.getApelido())) {
            buscaPessoa.setApelido(pessoa.getApelido());
        }

        if (Objects.nonNull(pessoa.getSituacao())) {
            buscaPessoa.setSituacao(pessoa.getSituacao());
        }

        if (Objects.nonNull(pessoa.getDataNascimento())) {
            buscaPessoa.setDataNascimento(pessoa.getDataNascimento());
        }

        return pessoaRepository.save(buscaPessoa);
    }
}
