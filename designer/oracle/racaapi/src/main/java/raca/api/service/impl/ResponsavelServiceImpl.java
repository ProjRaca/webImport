package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raca.api.domain.entity.postgres.Responsavel;
import raca.api.repository.postgres.ResponsavelRepository;
import raca.api.repository.specifications.ResponsavelSpecifications;
import raca.api.rest.dto.ResponsavelDTO;
import raca.api.service.ResponsavelService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResponsavelServiceImpl implements ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    private final ResponsavelSpecifications responsavelSpecifications;
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
        resp.setFilial(responsavel.isFilial());
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
        resp.setFilial(responsavel.isFilial());
        return responsavelRepository.save(resp);
    }

    @Override
    public List<Responsavel> encontrarPorNome(String nome) {
        return responsavelRepository.encontrarPorNome(nome);
    }

    @Override
    public List<Responsavel> getFiliais() {
        return responsavelRepository.getFiliais();
    }


    @Override
    public void delete(Integer id) {
        responsavelRepository.deleteById(id);
    }


    @Override
    public List<ResponsavelDTO> getFilterResponsavel(Integer id, String cpfcnpj, String nome, String email, String telefone, boolean filial) {
        Specification<Responsavel> spec = responsavelSpecifications.withFilters(id, cpfcnpj, nome, email, telefone, filial);
        List<Responsavel> responsaveis = responsavelRepository.findAll(spec);
        return responsaveis.stream().map(documento -> {
            return getAllResponsavelDTO(documento);
        }).collect(Collectors.toList());

    }

    private ResponsavelDTO getAllResponsavelDTO(Responsavel responsavel) {
        ResponsavelDTO responsavelDTO = new ResponsavelDTO();
        responsavelDTO.setId(responsavel.getId());
        responsavelDTO.setCpfcnpj(responsavel.getCpfcnpj());
        responsavelDTO.setTelefone(responsavel.getTelefone());
        responsavelDTO.setNome(responsavel.getNome());
        responsavelDTO.setFilial(responsavel.isFilial());
        return responsavelDTO;
    }

}
