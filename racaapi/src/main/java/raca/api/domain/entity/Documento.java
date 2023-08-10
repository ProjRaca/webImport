package raca.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String filial;

    @Column
    private String emissor;
    @Column
    private Date datadocumentesc;
    @Column
    private Date datavalidade;

    @Column
    private String tipodocumento;

    @Column
    private byte[] documento;
    @Column
    private Integer iddocpai;

    @Column
    private boolean restrito;

    @Column
    private String nome;

    @Column
    private Integer numerodocumento;


}
