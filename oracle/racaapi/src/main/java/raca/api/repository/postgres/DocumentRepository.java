package raca.api.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import raca.api.domain.entity.postgres.Documento;

public interface DocumentRepository extends JpaRepository<Documento, Integer > { }
