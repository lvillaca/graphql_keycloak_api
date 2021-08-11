
package luis.api.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DataSourceConfigSecondaryTest {

    private final static String SECONDARY = "secondary";
    DataSourceConfigSecondary secondary = new DataSourceConfigSecondary();

    @Test
    public void testEntityManagerFactory()  {
        HikariDataSource ds = Mockito.mock(HikariDataSource.class);
        Assert.assertEquals(SECONDARY,
                ((LocalContainerEntityManagerFactoryBean)secondary.entityManagerFactorySecondary(ds)).getPersistenceUnitName());
    }


}
