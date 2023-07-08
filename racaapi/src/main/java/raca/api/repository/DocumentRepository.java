package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Responsavel;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Documento, Integer > {
/*

    @Query(value = "SELECT * FROM raca.documento d WHERE 1 = 1 " +
            "AND d.filial = ?1 " +
            "AND d.emissor = ?2 " +
            "AND d.datadocumentesc = ?3 " +
            "AND d.datavalidade = ?4 ", nativeQuery = true)
    List<Documento> findDocumentsByFilter(String filial, String emissor, LocalDate datadocumentesc, LocalDate datavalidade);
*/

   /* @Query(value = "select * from raca.documento d WHERE 1=1 " +
            "AND d.filial = :filial " +
            "AND d.emissor = :emisssor " +
            "AND d.datadocumentesc = :datadocumentesc " +
            "AND d.datavalidade = :datavalidade "
            , nativeQuery = true)
    List<Documento> findDocumentsByFilter(@Param("filial") String filial,
                                     @Param("emissor") String emissor,
                                     @Param("datadocumentesc") Date datadocumentesc,
                                     @Param("datavalidade") Date datavalidade );*/

}
