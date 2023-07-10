package raca.api.service;

import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.postgres.Movimentacao;
import raca.api.rest.dto.MovimentacaoDTO;

import java.util.Date;
import java.util.List;

public interface MovimentacaoService {
    List<Movimentacao> getAllMovimentacao();

    MovimentacaoDTO processMovement(MovimentacaoDTO movimentacao) ;

    MovimentacaoDTO criar(MultipartFile local);

    MovimentacaoDTO exprtandoMovement(MovimentacaoDTO movimentacao);

    boolean buscarMovimentacoes(String cpfFuncionario, String historico, Date competencia, String cnpjEmpresa);


}
