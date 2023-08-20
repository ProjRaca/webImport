package raca.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentoDTO extends ErroCode{
    private Integer id;
    private String filial;
    private String emissor;
    private LocalDate datadocumentesc;
    private LocalDate datavalidade;
    private String tipodocumento;
    private Integer iddocpai;
    private boolean restrito;
    private String nome;
    private String nomepai;
    private byte[] documento;
    private String responsavel;
    private Integer empresa;

}
