package raca.api.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raca.api.domain.entity.postgres.Responsavel;

import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer>, JpaSpecificationExecutor<Responsavel> {

    @Query(value = "select * from raca.responsavel r where r.nome like '%' || :nome || '%'", nativeQuery = true)
    List<Responsavel> encontrarPorNome(@Param("nome") String nome );

    @Query(value = "select * from raca.responsavel r where r.filial = true", nativeQuery = true)
    List<Responsavel> getFiliais();

}
