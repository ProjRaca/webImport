package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import raca.api.domain.entity.Movimentacao;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepository  extends JpaRepository<Movimentacao, Integer > {

    @Query(value = "SELECT * FROM Movimentacao m WHERE m.cpffuncionario = ?1 " +
            "AND m.historico = ?2 " +
            "AND m.competencia = ?3 " +
            "AND m.cnpjempresa = ?4 " +
            "AND m.status = 'P'", nativeQuery = true)
    List<Movimentacao> findByCpfFuncionarioAndHistoricoAndCompetenciaAndCnpjEmpresaAndStatus(String cpfFuncionario, String historico, LocalDate competencia, String cnpjEmpresa);

}
