
package luis.api.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DataSourceConfigPrimaryTest {

    private final static String PRIMARY = "primary";
    DataSourceConfigPrimary primary = new DataSourceConfigPrimary();

    @Test
    public void testEntityManagerFactory()  {
        HikariDataSource ds = Mockito.mock(HikariDataSource.class);
        Assert.assertEquals(PRIMARY,
                ((LocalContainerEntityManagerFactoryBean)primary.entityManagerFactory(ds)).getPersistenceUnitName());
    }


}
