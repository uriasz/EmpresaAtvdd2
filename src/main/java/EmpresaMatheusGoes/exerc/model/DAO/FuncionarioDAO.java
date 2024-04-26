package EmpresaMatheusGoes.exerc.model.DAO;

import EmpresaMatheusGoes.exerc.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FuncionarioDAO extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByEstaDeFerias(boolean b);
}
