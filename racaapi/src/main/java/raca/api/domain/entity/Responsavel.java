package raca.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "responsavel")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotEmpty(message = "{campo.cpfcnpj.obrigatorio}")
    private String cpfcnpj;
    @Column
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

}
