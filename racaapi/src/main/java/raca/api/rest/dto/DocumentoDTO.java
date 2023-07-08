package raca.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
    private Integer id;
    private String filial;

    private String emissor;

    private LocalDate datadocumentesc;

    private LocalDate datavalidade;

    private String tipodocumento;

    private byte[] documento;

    private Integer iddocpai;

}
