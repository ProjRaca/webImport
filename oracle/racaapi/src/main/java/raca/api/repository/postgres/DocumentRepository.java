package raca.api.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raca.api.domain.entity.postgres.Documento;
import raca.api.domain.entity.postgres.Movimentacao;
import raca.api.domain.entity.postgres.Responsavel;

import java.time.LocalDate;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Documento, Integer > , JpaSpecificationExecutor<Documento>{

    @Query(value = "SELECT * FROM raca.documento m WHERE m.id = ?1 " +
            "AND m.historico = ?2 " +
            "AND m.competencia = ?3 " +
            "AND m.cnpjempresa = ?4 " +
            "AND m.status = 'P'", nativeQuery = true)
    List<Documento> filter(String cpfFuncionario, String historico, LocalDate competencia, String cnpjEmpresa);

    @Query(value = "SELECT * FROM raca.documento m WHERE m.restrito = 'false' order by m.id LIMIT 10", nativeQuery = true)
    List<Documento> listDocNotRestrito();

    @Query(value = "select * from raca.documento r where r.id in (select iddocpai from raca.documento WHERE iddocpai is not null)", nativeQuery = true)
    List<Documento> getAllMDocumentosPai();


    @Query(value = "select * from raca.documento r where UPPER(r.nome) like '%' || :nome || '%'" +
            " AND r.id in (select iddocpai from raca.documento WHERE iddocpai is not null)", nativeQuery = true)
    List<Documento> encontrarDocPaiPorNome(@Param("nome") String nome );

    @Query(value = "select * from raca.documento r where UPPER(r.nome) like '%' || :nome || '%'", nativeQuery = true)
    List<Documento> encontrarDocPorNome(@Param("nome") String nome );

    @Query(value = "SELECT * FROM raca.documento m WHERE m.restrito = :restrito order by m.id desc LIMIT 10 ", nativeQuery = true)
    List<Documento> listDocTelaInical(String restrito);

    @Query(value = "SELECT * FROM raca.documento m order by m.id desc LIMIT 10 ", nativeQuery = true)
    List<Documento> listDocTelaInicalAdm();

}
