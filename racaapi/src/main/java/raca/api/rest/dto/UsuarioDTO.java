package raca.api.rest.dto;

import lombok.Data;
import raca.api.domain.entity.ErroCod;
@Data
public class UsuarioDTO extends ErroCod {
    private String nome;
    private String login;
}
