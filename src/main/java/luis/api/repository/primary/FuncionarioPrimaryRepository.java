package luis.api.repository.primary;

import luis.api.dados.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import luis.api.repository.FuncionarioRepository;

@Repository
public interface FuncionarioPrimaryRepository extends JpaRepository<Funcionario, String>, FuncionarioRepository {


}
