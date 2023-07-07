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
        resp.setNome(responsavel.getNome());
        if(responsavel.getTelefone() != null ){
            resp.setTelefone(responsavel.getTelefone());
        }
        if(responsavel.getEmail() != null ){
            resp.setEmail(responsavel.getEmail());
        }

        responsavelRepository.save(resp);
    }

    @Override
    public void excluirResponsavel(Responsavel responsavel) {
        responsavelRepository.delete(responsavel);
    }

    @Override
    @Transactional
    public Responsavel editarResponsavel(ResponsavelDTO responsavel) {
        Responsavel resp = new Responsavel();
        resp.setId(Integer.valueOf(responsavel.getId()));
        resp.setCpfcnpj(responsavel.getCpfcnpj());
        resp.setNome(responsavel.getNome());
        if(responsavel.getTelefone() != null ){
            resp.setTelefone(responsavel.getTelefone());
        }
        if(responsavel.getEmail() != null ){
            resp.setEmail(responsavel.getEmail());
        }
        return responsavelRepository.save(resp);
    }

    @Override
    public List<Responsavel> encontrarPorNome(String nome) {
        return responsavelRepository.encontrarPorNome(nome);
    }

    @Override
    public void delete(Integer id) {
        responsavelRepository.deleteById(id);
    }


}
