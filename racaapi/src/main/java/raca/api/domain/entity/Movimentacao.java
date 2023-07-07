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
    @Column
    private String codigofilial;
    @Column
    private String cnpjempresa;
    @Column
    private int idfuncionario;
    @Column
    private String nomefuncionario;
    @Column
    private String cpffuncionario;
    @Column
    private String tipoparceiro;
    @Column
    private String historico;
    @Column
    private BigDecimal valor;
    @Column
    private LocalDate vencimento;
    @Column
    private LocalDate competencia;
    @Column
    private String status;
    @Column
    private String agencia;
    @Column
    private String agenciadv;
    @Column
    private String contacorrente;
    @Column
    private String contacorrentedv;
    @Column
    private String ultimousuario;
    @Column
    private LocalDate ultimaalteracao;
    @Column
    private String historicodescricao;

    @Column
    private String nota;
}

