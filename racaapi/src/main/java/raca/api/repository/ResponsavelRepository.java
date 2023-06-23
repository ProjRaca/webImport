package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raca.api.domain.entity.Responsavel;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
}
