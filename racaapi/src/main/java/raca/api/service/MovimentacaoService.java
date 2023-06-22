package raca.api.service;

import raca.api.domain.entity.Movimentacao;
import raca.api.rest.dto.MovimentacaoDTO;

import java.util.List;

public interface MovimentacaoService {
    List<Movimentacao> getAllMovimentacao();

    List<Movimentacao> processMovement(MovimentacaoDTO movimentacao) ;

}
