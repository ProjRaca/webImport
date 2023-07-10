package raca.api.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ResponsavelDTO {

    private String id;
    private String cpfcnpj;
    private String nome;

    private String email;

    private String telefone;
}
