package raca.api.repository.oracle;

public interface MovimentacaoRepositoryOracle {
}
/*
public interface MovimentacaoRepositoryOracle extends JpaRepository<Movimentacao, Integer > {

    @Query(value = "SELECT * FROM Movimentacao m WHERE m.cpffuncionario = ?1 " +
            "AND m.historico = ?2 " +
            "AND m.competencia = ?3 " +
            "AND m.cnpjempresa = ?4 " +
            "AND m.status = 'P'", nativeQuery = true)
    List<Movimentacao> findByCpfFuncionarioAndHistoricoAndCompetenciaAndCnpjEmpresaAndStatus(String cpfFuncionario, String historico, LocalDate competencia, String cnpjEmpresa);

}
*/