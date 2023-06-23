package raca.api.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
public class ResponsavelDTO {

    private String cpfcnpj;
    private String descricao;
}
