/**
 * @author pedrom
 * Data: 27/07/2022
 */
package raca.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raca.api.domain.entity.postgres.Movimentacao;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDTO extends ErroCode{


    @NotNull(message = "{campo.codigo-codigofilial.obrigatorio}")
    private String codigofilial;
    @NotNull(message = "{campo.codigo-historico.obrigatorio}")
    private String historico;

    private String historicoDescricao;
    @NotNull(message = "{campo.codigo-vencimento.obrigatorio}")
    private LocalDate vencimento;
    @NotNull(message = "{campo.codigo-competencia.obrigatorio}")
    private LocalDate competencia;

    private String nota;
    private List<Movimentacao> listMovimentacao;

}
