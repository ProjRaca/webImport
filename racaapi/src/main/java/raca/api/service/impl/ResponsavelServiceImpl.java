package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raca.api.domain.entity.Responsavel;
import raca.api.repository.ResponsavelRepository;
import raca.api.rest.dto.ResponsavelDTO;
import raca.api.service.ResponsavelService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResponsavelServiceImpl implements ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    @Override
    public List<Responsavel> getAllResponsavel() {
        return responsavelRepository.findAll();
    }

    @Override
    public Optional<Responsavel> getRsponsavelId(Integer id) {
        return responsavelRepository.findById(id);
    }

    @Override
    @Transactional
    public void salvarResponsavel(ResponsavelDTO responsavel) {
        Responsavel resp = new Responsavel();
        resp.setCpfcnpj(responsavel.getCpfcnpj());
        resp.setDescricao(responsavel.getDescricao());
        responsavelRepository.save(resp);
    }

    @Override
    public void excluirResponsavel(Responsavel responsavel) {
        responsavelRepository.delete(responsavel);
    }

    @Override
    public Responsavel editarResponsavel(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }
}
