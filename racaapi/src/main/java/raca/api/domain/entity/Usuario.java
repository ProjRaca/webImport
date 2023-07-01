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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;
    @Column
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;
    @Column
    private boolean admin;
    @Column
    private String descricao;
    @Column
    private String endereco;
    @Column
    private String bairro;
    @Column
    private String municipio;
    @Column
    private String tipo;
    @Column
    private String status;
    @Column
    private String nome;
    @Column
    private String email;

}
