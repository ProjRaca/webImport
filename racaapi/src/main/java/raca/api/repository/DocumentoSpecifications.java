package raca.api.repository;

import org.springframework.data.jpa.domain.Specification;
import raca.api.domain.entity.Documento;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DocumentoSpecifications {

    public static Specification<Documento> withFilters(Integer id,
                                                       String filial,
                                                       String emissor,
                                                       String datadocumentesc,
                                                       String datavalidade,
                                                       String tipodocumento,
                                                       Integer iddocpai,
                                                       boolean restrito,
                                                       String nome,
                                                       String datafim,
                                                       String datafimvalidade,
                                                       Integer numerodocumento) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (id != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }

            if (filial != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("filial"), filial));
            }

            if (emissor != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("emissor"), emissor));
            }

            if (datadocumentesc != null && datafim != null) {
                LocalDate parse1 = LocalDate.parse(datafim, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate parse = LocalDate.parse(datadocumentesc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("datadocumentesc"), parse, parse1));
            }

            if (datavalidade != null && datafimvalidade != null) {
                LocalDate parse1 = LocalDate.parse(datafimvalidade, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate parse = LocalDate.parse(datavalidade, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("datavalidade"), parse, parse1));
            }

            if (tipodocumento != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("tipodocumento"), tipodocumento));
            }

            if (iddocpai != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("iddocpai"), iddocpai));
            }

            if (numerodocumento != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("numerodocumento"), numerodocumento));
            }


            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("restrito"), restrito));


            if (nome != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("nome"), nome));
            }

            return predicate;
        };
    }
}
