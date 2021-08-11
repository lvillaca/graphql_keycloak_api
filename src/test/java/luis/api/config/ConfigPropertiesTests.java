/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package luis.api.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ConfigPropertiesTests {

    private static ConfigProperties config;

    @Test
    public void testPackage()  {
        Assert.assertEquals(ConfigProperties.MODEL_PACKAGE, this.getClass().getPackage().getName().replaceFirst("config$","dados"));
    }

    @Test
    public void testRelevantAttributes() throws Throwable {
        Assert.assertEquals("none", ConfigProperties.JPA_PROPERTIES.getProperty("hibernate.hbm2ddl.auto"));
        Assert.assertEquals("none", ConfigProperties.JPA_PROPERTIES.getProperty("hibernate.ddl-auto"));
    }

}
