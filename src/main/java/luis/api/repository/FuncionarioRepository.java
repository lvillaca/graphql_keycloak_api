package luis.api.repository;

import luis.api.dados.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;


public interface FuncionarioRepository {
    final String  NOT_AVAILABLE = "'N/A'";

    final String FORCA_TRABALHO_PUBLI_QUERY = "SELECT vw_cd_employee, vw_nr_matricula, vw_fotr_nm_func, vw_nr_cpf from VW_FUNC";


    @Query(value=FORCA_TRABALHO_PUBLI_QUERY+" where   vw_nr_matricula = :matricula ",
            nativeQuery = true)
    Optional<Funcionario> findByMatricula(@Param("matricula") String matricula);

    @Query(value=FORCA_TRABALHO_PUBLI_QUERY,
            nativeQuery = true)
    Collection<Funcionario> allFuncionarios();

}

