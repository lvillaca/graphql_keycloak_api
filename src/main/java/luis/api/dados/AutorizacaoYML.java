package luis.api.dados;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.HashSet;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "autorizacao")
public class AutorizacaoYML {
    private Set<String> clientesNP1;
    private Set<String> clientesNP2;

    public Set<String> getClientesNP1() {
        return (Set<String>) ((HashSet) clientesNP1).clone();
    }

    public void setClientesNP1(Set<String> clientesNP1) {
        this.clientesNP1 = clientesNP1;
    }

    public Set<String> getClientesNP2() {
        return (Set<String>) ((HashSet) clientesNP2).clone();
    }

    public void setClientesNP2(Set<String> clientesNP2) {
        this.clientesNP2 = clientesNP2;
    }

}
