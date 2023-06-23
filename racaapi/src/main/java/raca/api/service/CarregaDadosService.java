package raca.api.service;

import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.Movimentacao;
import raca.api.rest.dto.MovimentacaoDTO;

import java.util.List;

public interface CarregaDadosService {
    List<Movimentacao> getAllMovimentacao();

    List<Movimentacao> processMovement(MovimentacaoDTO movimentacao) ;

    List<Movimentacao> criar(MultipartFile local);

}
