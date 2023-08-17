package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raca.api.domain.entity.postgres.Contas;
import raca.api.repository.postgres.ContasRepository;
import raca.api.service.ContasService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContasServiceImpl implements ContasService {

    private final ContasRepository contasRepository;

    @Autowired
    private EntityManager manager;

    @Override
    public List<String> getHistorico() {
        return contasRepository.getHistorico();
    }

    @Override
    public Contas findByCpfFuncionarioAndHistorico(String cpffuncionario, String historico) {
        Contas byCpfFuncionarioAndHistorico1 = contasRepository.findCpfFuncionarioAndDescricao(cpffuncionario, historico);
       // Optional<Contas> byCpfFuncionarioAndHistorico = Optional.ofNullable(byCpfFuncionarioAndHistorico1);
        return byCpfFuncionarioAndHistorico1;
    }

    @Override
    public List<Contas> findByCpfFuncionario(String id) {
        return contasRepository.findByCpfFuncionario(id);
    }

    @SuppressWarnings("unchecked")
    public List<Contas> getContaFuncionario(String cpffuncionario, String descricao) {
        Session session = manager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Contas> criteriaQuery = builder.createQuery(Contas.class);
        Root<Contas> root = criteriaQuery.from(Contas.class);

        // Criar as condições de filtro
        List<Predicate> predicates = new ArrayList<>();
        if (cpffuncionario != null && !cpffuncionario.isEmpty()) {
            predicates.add(builder.equal(root.get("cpffuncionario"), cpffuncionario));
        }
        if (descricao != null && !descricao.isEmpty()) {
            predicates.add(builder.equal(root.get("descricao"), descricao));
        }

        // Definir as condições de filtro
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        // Executar a consulta
        Query<Contas> query = session.createQuery(criteriaQuery);
        return query.getResultList();

    }

    @Override
    public List<Contas> getAllContas() {
        return contasRepository.findAll();
    }

}
