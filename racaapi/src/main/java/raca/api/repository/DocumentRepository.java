package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raca.api.domain.entity.Documento;

public interface DocumentRepository extends JpaRepository<Documento, Integer > {



}
