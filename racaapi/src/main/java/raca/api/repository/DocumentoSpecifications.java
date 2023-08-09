package raca.api.repository;

import org.springframework.data.jpa.domain.Specification;
import raca.api.domain.entity.Documento;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
public class DocumentoSpecifications {

    public static Specification<Documento> withFilters(Integer id,
                                                       String filial,
                                                       String emissor,
                                                       LocalDate datadocumentesc,
                                                       LocalDate datavalidade,
                                                       String tipodocumento,
                                                       Integer iddocpai,
                                                       boolean restrito,
                                                       String nome,
                                                       LocalDate datafim,
                                                       LocalDate datafimvalidade) {
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
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("datadocumentesc"), datadocumentesc, datafim));
            }

            if (datavalidade != null && datafimvalidade != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("datavalidade"), datavalidade, datafimvalidade));
            }

            if (tipodocumento != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("tipodocumento"), tipodocumento));
            }

            if (iddocpai != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("iddocpai"), iddocpai));
            }


            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("restrito"), restrito));


            if (nome != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("nome"), nome));
            }

            return predicate;
        };
    }
}
