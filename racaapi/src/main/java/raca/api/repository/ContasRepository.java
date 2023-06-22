package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raca.api.domain.entity.Contas;

import java.util.List;

public interface ContasRepository extends JpaRepository<Contas, Integer> {
    @Query(value = " select * from raca.contas c where c.cpffuncionario = ':cpffuncionario' ", nativeQuery = true)
    List<Contas> findByCpfFuncionario(@Param("cpffuncionario") String cpffuncionario);

    @Query(value = " select DISTINCT(c.descricao) from raca.contas c ", nativeQuery = true)
    List<String> getHistorico();

    @Query(value = " select * from raca.contas c where c.cpffuncionario = ':cpffuncionario' and c.descricao = ':descricao' ", nativeQuery = true)
    Contas findByCpfFuncionarioAndHistorico(@Param("cpffuncionario") String cpffuncionario, @Param("descricao") String descricao);


}
