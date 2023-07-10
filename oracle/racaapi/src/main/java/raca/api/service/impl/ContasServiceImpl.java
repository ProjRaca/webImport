package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raca.api.domain.entity.postgres.Contas;
import raca.api.repository.postgres.ContasRepository;
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
        Contas byCpfFuncionarioAndHistorico1 = contasRepository.findCpfFuncionarioAndDescricao(cpffuncionario, historico);
       // Optional<Contas> byCpfFuncionarioAndHistorico = Optional.ofNullable(byCpfFuncionarioAndHistorico1);
        return null;
    }

    @Override
    public List<Contas> findByCpfFuncionario(String id) {
        return contasRepository.findByCpfFuncionario(id);
    }
}
