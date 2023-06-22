/**
 * @author pedrom
 * Data: 27/07/2022
 */
package raca.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "movimentacao")
public class Movimentacao {

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
    private String ultimousuario;
    private LocalDate ultimaalteracao;
    private String agencia;
    private String agenciadv;
    private String contacorrente;
    private String contacorrentedv;


    
}
