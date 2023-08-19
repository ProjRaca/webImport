package raca.api.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import raca.api.domain.entity.postgres.Responsavel;

import javax.persistence.criteria.Predicate;

@Component
public class ResponsavelSpecifications {

    public Specification<Responsavel> withFilters(  Integer id,
                                                    String cpfcnpj,
                                                    String nome,
                                                    String email,
                                                    String telefone,
                                                    boolean filial) {
        return (root, query, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();

            if (id != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }

            if (cpfcnpj != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("cpfcnpj"), cpfcnpj));
            }

            if (nome != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("nome"), nome));
            }

            if (email != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("email"), email));
            }


            if (telefone != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("telefone"), telefone));
            }

            if(filial) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("filial"), filial));
            }

            return predicate;
        };
    }

}
