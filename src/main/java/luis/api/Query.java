package luis.api;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import luis.api.dados.AutorizacaoYML;
import luis.api.dados.Funcionario;
import luis.api.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


import static org.springframework.security.core.context.SecurityContextHolder.getContext;
/**
 * Classe relativa ao servico de consumo dos GraphQLs.
 * @author Luis
 */
@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    AutorizacaoYML autorizacoes;

    public Optional<Funcionario> findFuncionarioByMatricula(String matricula){
        if (acessoChamadaNP1(getApplicationId()))
            return funcionarioService.findFuncionarioByMatricula(matricula);
        return Optional.empty();
    }
    
    public Collection<Funcionario> allFuncionarios(){
        if (acessoChamadaNP2(getApplicationId()))
            return funcionarioService.allFuncionarios();
        return new ArrayList<Funcionario>();
    }

    String getApplicationId() {
        if (getContext()!=null && getContext().getAuthentication()!=null ) return (getContext().getAuthentication().getName());
        else return "NA";
    }

    boolean acessoChamadaNP1(String applicationId) {
        // menor nivel de acesso
        return true;
    }

    boolean acessoChamadaNP2(String applicationId) {
        if (applicationId==null) return false;
        AtomicBoolean found = new AtomicBoolean();
        autorizacoes.getClientesNP2().forEach(cliente -> {
            if (applicationId.contains(cliente)) found.set(true);
        });
        return found.get();
    }
}

