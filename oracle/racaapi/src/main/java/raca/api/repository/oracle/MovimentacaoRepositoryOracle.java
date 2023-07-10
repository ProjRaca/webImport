package raca.api.repository.oracle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import raca.api.domain.entity.oracle.MovimentacaoOracle;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MovimentacaoRepositoryOracle  extends JpaRepository<MovimentacaoOracle, Integer > {

    @Query(value = "SELECT * FROM raca.movimentacao m WHERE m.cpffuncionario = ?1 " +
            "AND m.historico = ?2 " +
            "AND m.competencia = DATE( ?3 ) " +
            "AND m.cnpjempresa = ?4 " , nativeQuery = true)
    List<MovimentacaoOracle> findByCpfFuncionarioAndHistoricoAndCompetenciaAndCnpjEmpresaAndStatus(String cpfFuncionario, String historico, String competencia, String cnpjEmpresa);

}
