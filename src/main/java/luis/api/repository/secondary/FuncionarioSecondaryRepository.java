package luis.api.repository.secondary;

import luis.api.dados.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import luis.api.repository.FuncionarioRepository;

@Repository
public interface FuncionarioSecondaryRepository extends JpaRepository<Funcionario, String>, FuncionarioRepository {

}
