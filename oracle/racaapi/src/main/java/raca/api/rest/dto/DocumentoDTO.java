package raca.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
    private Integer id;
    private String filial;

    private String emissor;

    private Date datadocumentesc;

    private Date datavalidade;

    private String tipodocumento;

    private byte[] documento;

    private Integer iddocpai;

}
