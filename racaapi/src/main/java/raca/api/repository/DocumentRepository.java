package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import raca.api.domain.entity.Documento;

import java.time.LocalDate;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Documento, Integer > {

    @Query(value = "SELECT * FROM Movimentacao d WHERE 1 = 1 " +
            "AND d.filial = ?1 " +
            "AND m.emissor = ?2 " +
            "AND m.datadocumentesc = ?3 " +
            "AND m.datavalidade = ?4 ", nativeQuery = true)
    List<Documento> findDocumentsByFilter(String filial, String emissor, LocalDate datadocumentesc, LocalDate datavalidade);

}
