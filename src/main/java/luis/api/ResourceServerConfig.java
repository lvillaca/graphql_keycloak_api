package luis.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

// instead of @EnableOAuth2Sso, the following validates tokens
@EnableResourceServer
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOAuth2Sso
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/graphql/**","/graphql")
                     .fullyAuthenticated()
                     //hasRole("READ_BIDT_CORP") - pode ser utilizado futuramente
                .anyRequest().permitAll();
	}

    @Bean
    public PrincipalExtractor keycloakPrincipalExtractor() {
        // retorna o client ID - necessario para a validacao de permissionamento da query
        return new KeycloakPrincipalExtractor();
    }

}
