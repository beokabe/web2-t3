package br.com.ufms.web.trabalho.beatriz.repository;

import br.com.ufms.web.trabalho.beatriz.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> { }
