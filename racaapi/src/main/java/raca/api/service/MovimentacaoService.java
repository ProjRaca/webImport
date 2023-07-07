package raca.api.service;

import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.Movimentacao;
import raca.api.rest.dto.MovimentacaoDTO;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoService {
    List<Movimentacao> getAllMovimentacao();

    MovimentacaoDTO processMovement(MovimentacaoDTO movimentacao) ;

    MovimentacaoDTO criar(MultipartFile local);

    MovimentacaoDTO exprtandoMovement(MovimentacaoDTO movimentacao);

    List<Movimentacao> buscarMovimentacoes(String cpfFuncionario, String historico, LocalDate competencia, String cnpjEmpresa);

}
