package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raca.api.domain.entity.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
}
