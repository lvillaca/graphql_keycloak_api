package luis.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

/**
 * Classe principal SpringBoot.
 * @author Luis
 */
@SpringBootApplication
@RestController
public class BootAPI  {

    /**
     * Define Filtro para codigo de caracteres.
     * @return objeto Filter
     */
    @Bean
    public Filter getCharacterEncodingFilter() {

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();

        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        return encodingFilter;

    }

    /**
     * Sobe o SpringBoot.
     * @param args
     */
    //classe principal do main
    public static void main(String[] args) {
        SpringApplication.run(BootAPI.class, args);
    }


}
