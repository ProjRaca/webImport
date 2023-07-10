package raca.api.repository;

import org.springframework.stereotype.Repository;
import raca.api.domain.entity.Documento;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class DocumentoDAO {

    private EntityManager em;

    public DocumentoDAO(EntityManager em) {
        this.em = em;
    }

    public List<Documento> buscarPorFiltros(String filial, String emissor, Date datadocumentesc, Date datavalidade) {
        String jpql = "SELECT d FROM Documento d WHERE 1=1 ";
        if (filial != null && !filial.trim().isEmpty()) {
            jpql += " AND d.filial = :filial ";
        }
        if (emissor != null && !emissor.trim().isEmpty()) {
            jpql += " AND d.emissor like '%' || :emissor || '%'";
        }
        if (datadocumentesc != null) {
            jpql += " AND p.datadocumentesc = :datadocumentesc ";
        }

        if (datavalidade != null) {
            jpql += " AND p.datavalidade = :datavalidade ";
        }

        TypedQuery<Documento> query = em.createQuery(jpql, Documento.class);
        if (filial != null && !filial.trim().isEmpty()) {
            query.setParameter("filial", filial);
        }
        if (emissor != null && !emissor.trim().isEmpty()) {
            query.setParameter("emissor", emissor);
        }
        if (datadocumentesc != null) {
            query.setParameter("datadocumentesc", datadocumentesc);
        }
        if (datavalidade != null) {
            query.setParameter("datavalidade", datavalidade);
        }
        return query.getResultList();
    }

}
