package raca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raca.api.domain.entity.Responsavel;
import raca.api.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

    @Query(value = "select * from raca.usuario r where r.nome like '%' || :nome || '%'", nativeQuery = true)
    List<Usuario> encontrarPorNome(@Param("nome") String nome );
}
