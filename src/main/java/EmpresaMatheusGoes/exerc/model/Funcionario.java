package EmpresaMatheusGoes.exerc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double salarioBruto;
    private Double salarioLiquido;
    private Boolean estaDeFerias;
    private Integer numeroDeFilhos;
    private Boolean possuiValeTransporte;
    private Boolean trabalhaNoturno;
}
