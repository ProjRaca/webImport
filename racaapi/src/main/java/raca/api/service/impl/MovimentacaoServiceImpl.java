package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raca.api.domain.entity.Contas;
import raca.api.domain.entity.Movimentacao;
import raca.api.repository.ContasRepository;
import raca.api.repository.MovimentacaoRepository;
import raca.api.rest.dto.MovimentacaoDTO;
import raca.api.service.MovimentacaoService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ContasRepository contasRepository;
    @Override
    public List<Movimentacao> getAllMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    @Override
    public List<Movimentacao> processMovement(MovimentacaoDTO movimentacao) {

        movimentacao.getListMovimentacao().stream().forEach(x->{

            List<Contas> byCpfFuncionario = contasRepository.findByCpfFuncionario(x.getCpffuncionario());

        });

        return null;
    }
}
