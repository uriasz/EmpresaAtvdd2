package EmpresaMatheusGoes.exerc.model.DTO;

import lombok.Data;

@Data

public class FuncionarioDTO {
    private Long id;
    private String nome;
    private Double salarioLiquido;
    private Boolean estaDeFerias;
}
