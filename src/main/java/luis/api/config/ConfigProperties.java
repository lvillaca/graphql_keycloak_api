package luis.api.config;

import java.util.Properties;

public class ConfigProperties {

    final static String MODEL_PACKAGE = "luis.api.dados";

    final static Properties JPA_PROPERTIES= new Properties();

    static {
//        put("hibernate.dialect", "org.hibernate.dialect...");
        JPA_PROPERTIES.put("hibernate.hbm2ddl.auto", "none");
        JPA_PROPERTIES.put("hibernate.ddl-auto", "none");
        JPA_PROPERTIES.put("show-sql", "true");
    };
}
