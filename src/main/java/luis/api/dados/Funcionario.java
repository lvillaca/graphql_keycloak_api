package luis.api.dados;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Luis
 */
@Entity
public class Funcionario implements Serializable {
    @Id
    @Column(name="vw_cd_employee")
    int id; 
    @Column(name = "vw_nr_matricula")
    String matricula;
    @Column(name = "vw_nr_cpf")
    String cpf;
    @Column(name = "vw_nm_func")
    String nomeCompleto;


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
