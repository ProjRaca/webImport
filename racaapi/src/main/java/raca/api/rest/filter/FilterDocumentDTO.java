package raca.api.rest.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FilterDocumentDTO {

    private Integer id;
    private String filial;

    private String emissor;
    private LocalDate datadocumentesc;

    private LocalDate datavalidade;
    private Integer iddocpai;

}
