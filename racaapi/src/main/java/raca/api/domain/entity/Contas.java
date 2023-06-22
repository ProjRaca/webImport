/**
 * @author pedrom
 * Data: 05/08/2022
 */

package raca.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "contas")
public class Contas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer matriculafuncionario;
    @Column(name = "nomefuncionario", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nomefuncionario;
    @Column(name = "cpffuncionario", length = 11)
    private String cpffuncionario;
    @Column(name = "iddepartamento", length = 3)
    private String iddepartamento;
    @Column(name = "tipoparceiro", length = 1)
    private String tipoparceiro;
    @Column(name = "idconta", length = 6)
    private String idconta;
    @Column(name = "descricao", length = 6)
    private String descricao;





}
