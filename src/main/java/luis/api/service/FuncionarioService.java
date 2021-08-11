package luis.api.service;

import luis.api.dados.Funcionario;
import luis.api.repository.primary.FuncionarioPrimaryRepository;
import luis.api.repository.secondary.FuncionarioSecondaryRepository;
import org.springframework.stereotype.Service;
import luis.api.repository.FuncionarioRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Luis
 */
@Service
public class FuncionarioService {

    private FuncionarioPrimaryRepository funcionarioPrimaryRepository;

    private FuncionarioSecondaryRepository funcionarioSecondaryRepository;


    public FuncionarioService(FuncionarioPrimaryRepository primaryRepository, FuncionarioSecondaryRepository secondaryRepository) {
        this.funcionarioPrimaryRepository = primaryRepository;
        this.funcionarioSecondaryRepository = secondaryRepository;
    }

    boolean isPrimary = true;


    Object pullRepositoryRoundRobin() {
        if (isPrimary) {
            isPrimary = false;
            return funcionarioPrimaryRepository;
        } else {
            isPrimary = true;
            return funcionarioSecondaryRepository;
        }
    }

    public Optional<Funcionario> findFuncionarioByMatricula(String matricula) {
        return ((FuncionarioRepository)pullRepositoryRoundRobin()).findByMatricula(matricula);
    }

    public Collection<Funcionario> allFuncionarios() {
        return ((FuncionarioRepository)pullRepositoryRoundRobin()).allFuncionarios();
    }

}



