package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raca.api.domain.entity.Documento;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Documento, Integer > {

}
