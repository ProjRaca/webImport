package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raca.api.domain.entity.Contas;
import raca.api.domain.entity.Movimentacao;
import raca.api.repository.ContasRepository;
import raca.api.repository.MovimentacaoRepository;
import raca.api.rest.dto.MovimentacaoDTO;
import raca.api.service.ContasService;
import raca.api.service.MovimentacaoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ContasService contasRepository;
    private ContasService contasService;
    @Override
    public List<Movimentacao> getAllMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    @Override
    public List<Movimentacao> processMovement(MovimentacaoDTO movimentacao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return movimentacao.getListMovimentacao().stream().map(movimentacaoList -> {
            Contas byCpfFuncionarioAndHistorico = contasService.findByCpfFuncionarioAndHistorico(
                    movimentacaoList.getCpffuncionario(), movimentacao.getHistorico());

            movimentacaoList.setCodigofilial(movimentacao.getCodigofilial());
            movimentacaoList.setHistorico(movimentacao.getHistorico());
            movimentacaoList.setVencimento(movimentacao.getVencimento());

            return movimentacaoList; // Retornar o objeto movimentacaoList após as modificações
        }).collect(Collectors.toList());
    }
}
