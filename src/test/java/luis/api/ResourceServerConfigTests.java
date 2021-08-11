package luis.api;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResourceServerConfigTests {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testSecurityException() throws Exception {
        ResourceServerConfig resConf = Mockito.spy(ResourceServerConfig.class);

        HttpSecurity mockedSec = Mockito.mock(HttpSecurity.class);
        expectedEx.expect(java.lang.NullPointerException.class);

        resConf.configure(mockedSec);

    }

    @Test
    public void testResourceServerConfigExtractors()  {
        ResourceServerConfig resConf = new ResourceServerConfig();

        Assert.assertTrue(resConf.keycloakPrincipalExtractor() instanceof KeycloakPrincipalExtractor);
    }
}
