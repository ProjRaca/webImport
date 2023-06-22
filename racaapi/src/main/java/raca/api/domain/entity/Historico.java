/**
 * @author pedrom
 * Data: 01/08/2022
 */

package raca.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "idhistorico")
    private int idhistorico;
    @Column(name = "descricao", length = 45)
    private String descricao;

}
