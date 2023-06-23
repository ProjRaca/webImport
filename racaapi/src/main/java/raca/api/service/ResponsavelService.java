package raca.api.service;

import raca.api.domain.entity.Responsavel;
import raca.api.rest.dto.ResponsavelDTO;

import java.util.List;
import java.util.Optional;

public interface ResponsavelService {

    List<Responsavel> getAllResponsavel();

    Optional<Responsavel> getRsponsavelId(Integer id);

    void salvarResponsavel(ResponsavelDTO responsavel);
    void excluirResponsavel(Responsavel responsavel);
    Responsavel editarResponsavel(Responsavel responsavel);

}
