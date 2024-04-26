package EmpresaMatheusGoes.exerc.controller;

import EmpresaMatheusGoes.exerc.model.DTO.FuncionarioDTO;
import EmpresaMatheusGoes.exerc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/sem-ferias")
    public List<FuncionarioDTO> getFuncionariosSemFerias() {
        return funcionarioService.getFuncionariosSemFerias();
    }

    @GetMapping("/{id}/salario-anual")
    public Double calcularSalarioAnual(@PathVariable Long id) {
        return funcionarioService.calcularSalarioAnual(id);
    }

    @PutMapping("/{id}/ferias")
    public void alterarStatusFerias(@PathVariable Long id, @RequestBody Boolean estaDeFerias) {
        funcionarioService.alterarStatusFerias(id, estaDeFerias);
    }

    @GetMapping("/salario-total")
    public Double calcularSalarioTotalEmpresa() {
        return funcionarioService.calcularSalarioTotalEmpresa();
    }
}
