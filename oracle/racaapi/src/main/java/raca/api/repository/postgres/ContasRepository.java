package raca.api.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raca.api.domain.entity.oracle.MovimentacaoOracle;
import raca.api.domain.entity.postgres.Contas;

import java.util.List;
import java.util.Optional;

public interface ContasRepository extends JpaRepository<Contas, Integer> {
    //@Query(value = " select * from raca.contas c where c.cpffuncionario = ':cpffuncionario' ", nativeQuery = true)
    //List<Contas> findCpfFuncionarioAndDescricao(@Param("cpffuncionario") String cpffuncionario, @Param("descricao") String descricao);

    @Query(value = " select DISTINCT(c.descricao) from raca.contas c ", nativeQuery = true)
    List<String> getHistorico();

    //@Query(value = " select * from raca.contas c where c.cpffuncionario = ':cpffuncionario' and c.descricao = ':descricao' ", nativeQuery = true)
   // Contas findCpfFuncionarioAndDescricao(String cpffuncionario, String descricao);

    @Query("SELECT c FROM Contas c WHERE c.cpffuncionario = :cpffuncionario")
   List<Contas> findByCpfFuncionario(@Param("cpffuncionario") String cpffuncionario);

    //@Query("SELECT c FROM Contas c WHERE c.cpffuncionario = :cpffuncionario AND c.descricao = :descricao")
    //Contas findCpfFuncionarioAndDescricao(@Param("cpffuncionario") String cpffuncionario, @Param("descricao") String descricao);

    @Query(value = "SELECT * FROM raca.contas m WHERE m.cpffuncionario = ':cpffuncionario' " +
            "AND m.descricao = ':descricao' " , nativeQuery = true)
    Contas findCpfFuncionarioAndDescricao(@Param("cpffuncionario") String cpffuncionario, @Param("descricao") String descricao);

}
