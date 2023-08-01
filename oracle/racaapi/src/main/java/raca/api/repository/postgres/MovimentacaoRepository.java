package raca.api.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import raca.api.domain.entity.postgres.Movimentacao;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepository  extends JpaRepository<Movimentacao, Integer > {

    @Query(value = "SELECT * FROM raca.movimentacao m WHERE m.cpffuncionario = ?1 " +
            "AND m.historico = ?2 " +
            "AND m.competencia = ?3 " +
            "AND m.cnpjempresa = ?4 " +
            "AND m.status = 'P'", nativeQuery = true)
    List<Movimentacao> findByCpfFuncionarioAndHistoricoAndCompetenciaAndCnpjEmpresaAndStatus(String cpfFuncionario, String historico, LocalDate competencia, String cnpjEmpresa);
    @Query(value = "SELECT * FROM raca.movimentacao m WHERE m.status = 'E'", nativeQuery = true)
    List<Movimentacao> getExMovimentacao();

}
