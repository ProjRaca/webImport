package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raca.api.domain.entity.Contas;
import raca.api.repository.ContasRepository;
import raca.api.service.ContasService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContasServiceImpl implements ContasService {

    private final ContasRepository contasRepository;

    @Override
    public List<String> getHistorico() {
        return contasRepository.getHistorico();
    }

    @Override
    public Contas findByCpfFuncionarioAndHistorico(String cpffuncionario, String historico) {
        return contasRepository.findByCpfFuncionarioAndHistorico(cpffuncionario, historico);
    }

    @Override
    public List<Contas> findByCpfFuncionario(String id) {
        return contasRepository.findByCpfFuncionario(id);
    }
}
