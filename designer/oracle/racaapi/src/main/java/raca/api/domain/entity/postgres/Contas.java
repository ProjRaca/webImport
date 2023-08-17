package raca.api.domain.entity.postgres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Contas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer matriculafuncionario;

    @Column
    private String nomefuncionario;

    @Column
    private String cpffuncionario;

    @Column
    private String iddepartamento;

    @Column
    private String tipoparceiro;

    @Column
    private String idconta;

    @Column
    private String descricao;

}
