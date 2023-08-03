package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import raca.api.domain.entity.Documento;

public interface DocumentRepository extends JpaRepository<Documento, Integer > , JpaSpecificationExecutor<Documento> {}
