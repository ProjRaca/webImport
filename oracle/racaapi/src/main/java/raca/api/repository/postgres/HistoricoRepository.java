package raca.api.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import raca.api.domain.entity.postgres.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
}
