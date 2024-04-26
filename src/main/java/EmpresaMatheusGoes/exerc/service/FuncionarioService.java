package EmpresaMatheusGoes.exerc.service;

import EmpresaMatheusGoes.exerc.model.DAO.FuncionarioDAO;
import EmpresaMatheusGoes.exerc.model.DTO.FuncionarioDTO;
import EmpresaMatheusGoes.exerc.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class FuncionarioService {
    @Autowired
    private FuncionarioDAO funcionarioDAO;

    public List<FuncionarioDTO> getFuncionariosSemFerias(){

        List<Funcionario> funcionarios = funcionarioDAO.findByEstaDeFerias(false);
        List<FuncionarioDTO> funcionarioDTOList = new ArrayList<>();

        for (Funcionario j: funcionarios){
            FuncionarioDTO f = new FuncionarioDTO();
            f.setId(j.getId());
            f.setNome(j.getNome());
            f.setEstaDeFerias(j.getEstaDeFerias());
            f.setSalarioLiquido(j.getSalarioLiquido());
        }

        return funcionarioDTOList;
    }

    public Double calcularSalarioAnual(Long id) {

        Funcionario funcionario = funcionarioDAO.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        Double salarioBruto = funcionario.getSalarioBruto();
        Double inss = salarioBruto * 0.13;
        Double descontoValeTransporte = funcionario.getPossuiValeTransporte() ? salarioBruto * 0.06 : 0;
        Double bonoNoturno = funcionario.getTrabalhaNoturno() ? salarioBruto * 0.05 : 0;
        Integer bonoFilhos = Math.min(funcionario.getNumeroDeFilhos(), 3) * 50;
        Double salarioLiquido = salarioBruto - inss - descontoValeTransporte + bonoNoturno + bonoFilhos;
        Double salarioAnual = salarioLiquido * 12;
        return salarioAnual;
    }

    public Double calcularSalarioLiquido(Long id) {

        Funcionario funcionario = funcionarioDAO.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        Double salarioBruto = funcionario.getSalarioBruto();
        Double inss = salarioBruto * 0.13;
        Double descontoValeTransporte = funcionario.getPossuiValeTransporte() ? salarioBruto * 0.06 : 0;
        Double bonoNoturno = funcionario.getTrabalhaNoturno() ? salarioBruto * 0.05 : 0;
        Integer bonoFilhos = Math.min(funcionario.getNumeroDeFilhos(), 3) * 50;
        Double salarioLiquido = salarioBruto - inss - descontoValeTransporte + bonoNoturno + bonoFilhos;
        return salarioLiquido;
    }

    public void alterarStatusFerias(Long id, Boolean estaDeFerias) {
        Funcionario funcionario = funcionarioDAO.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        funcionario.setEstaDeFerias(estaDeFerias);
        if (estaDeFerias) {
            Double salarioBruto = funcionario.getSalarioBruto();
            funcionario.setSalarioBruto(salarioBruto - (salarioBruto / 3)); // Paga 1/3 do salário para férias
        } else {
            funcionario.setSalarioBruto(
                    funcionario.getSalarioBruto()
            );
        }
        funcionarioDAO.save(funcionario);
    }

    public Double calcularSalarioTotalEmpresa() {
        List<Funcionario> funcionarios = funcionarioDAO.findAll();

        Double salarioTotalPago = 0.0;

        for (Funcionario f: funcionarios){
            salarioTotalPago += calcularSalarioLiquido(f.getId());
        }
        return salarioTotalPago;
    }
}
