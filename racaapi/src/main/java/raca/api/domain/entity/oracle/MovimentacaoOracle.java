/**
 * @author pedrom
 * Data: 27/07/2022
 */
package raca.api.domain.entity.oracle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "movimentacaoOracle")
public class MovimentacaoOracle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmovimentacao")
    private int idmovimentacao;
    private String codigofilial;
    private String cnpjempresa;
    private int idfuncionario;
    private String nomefuncionario;
    private String cpffuncionario;
    private String tipoparceiro;
    private String historico;
    private BigDecimal valor;
    private LocalDate vencimento;
    private LocalDate competencia;
    private String status;
    private String agencia;
    private String agenciadv;
    private String contacorrente;
    private String contacorrentedv;
    private String ultimousuario;
    private LocalDate ultimaalteracao;

    
}
