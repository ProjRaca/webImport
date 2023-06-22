package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raca.api.domain.entity.Movimentacao;

public interface MovimentacaoRepository  extends JpaRepository<Movimentacao, Integer > {
}
