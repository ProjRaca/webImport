package raca.api.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import raca.api.domain.entity.postgres.Documento;
import raca.api.domain.entity.postgres.Movimentacao;

import java.time.LocalDate;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Documento, Integer > , JpaSpecificationExecutor<Documento>{

    @Query(value = "SELECT * FROM raca.documento m WHERE m.id = ?1 " +
            "AND m.historico = ?2 " +
            "AND m.competencia = ?3 " +
            "AND m.cnpjempresa = ?4 " +
            "AND m.status = 'P'", nativeQuery = true)
    List<Documento> filter(String cpfFuncionario, String historico, LocalDate competencia, String cnpjEmpresa);

    @Query(value = "SELECT * FROM raca.documento m WHERE m.restrito = 'false' order by m.id", nativeQuery = true)
    List<Documento> listDocNotRestrito();

    @Query(value = "SELECT DISTINCT(iddocpai),id, filial, emissor, datadocumentesc, datavalidade, documento, tipodocumento, restrito, nome, numerodocumento, responsavel, empresa FROM raca.documento WHERE iddocpai is not null", nativeQuery = true)
    List<Documento> getAllMDocumentosPai();

}
