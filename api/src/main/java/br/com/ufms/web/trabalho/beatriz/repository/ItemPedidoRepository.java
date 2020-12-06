package br.com.ufms.web.trabalho.beatriz.repository;

import br.com.ufms.web.trabalho.beatriz.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> { }
