/**
 * @author Pedrom
 * Data: 08/07/2022
 */

package connections.bean;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder

public class ModeloDados {
    //private int codigo;
    private String funcionario;
    private String cpf;
    private BigDecimal valor;
    private String empresa;
}