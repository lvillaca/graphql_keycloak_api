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
package luis.api.service;

import luis.api.dados.Funcionario;
import luis.api.repository.primary.FuncionarioPrimaryRepository;
import luis.api.repository.secondary.FuncionarioSecondaryRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
public class FuncionarioServiceTests {

    private static String KEY = "KEY";
    private static String MATNR = "MATNR";
    private static String NOME = "NOME";


    FuncionarioPrimaryRepository funcionarioPrimaryRepository;
    FuncionarioSecondaryRepository funcionarioSecondaryRepository;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void initObjects() {
        funcionarioPrimaryRepository = Mockito.mock(FuncionarioPrimaryRepository.class);
        funcionarioSecondaryRepository = Mockito.mock(FuncionarioSecondaryRepository.class);
    }

    @Test
    public void testPullRepositoryRoundRobin()  {
        FuncionarioService service = new FuncionarioService(funcionarioPrimaryRepository, funcionarioSecondaryRepository);
        service.isPrimary = true;
        Assert.assertEquals(funcionarioPrimaryRepository, service.pullRepositoryRoundRobin());
        Assert.assertEquals(funcionarioSecondaryRepository, service.pullRepositoryRoundRobin());
        Assert.assertEquals(funcionarioPrimaryRepository, service.pullRepositoryRoundRobin());
        Assert.assertEquals(funcionarioSecondaryRepository, service.pullRepositoryRoundRobin());

    }

    @Test
    public void testfindFuncionarioByMatricula()  {
        //from 2nd repository
        Funcionario func = new Funcionario();
        func.setMatricula(MATNR);
        Mockito.doReturn(Optional.of(func)).when(funcionarioSecondaryRepository).findByMatricula(MATNR);
        Mockito.doReturn(null).when(funcionarioPrimaryRepository).findByMatricula(MATNR);

        FuncionarioService service = new FuncionarioService(funcionarioPrimaryRepository, funcionarioSecondaryRepository);
        service.isPrimary = false;

        Assert.assertEquals(MATNR, service.findFuncionarioByMatricula(MATNR).get().getMatricula());
    }


    @Test
    public void testAllFuncionarios() {
        //from 2nd repository
        Funcionario func = new Funcionario();
        func.setNomeCompleto(NOME);

        ArrayList<Funcionario> arrayList = new ArrayList<Funcionario>();
        arrayList.add(func);
        Mockito.doReturn(arrayList).when(funcionarioSecondaryRepository).allFuncionarios();
        Mockito.doReturn(null).when(funcionarioPrimaryRepository).allFuncionarios();

        FuncionarioService service = new FuncionarioService(funcionarioPrimaryRepository, funcionarioSecondaryRepository);
        service.isPrimary = false;

        Funcionario funcionarioItem = (Funcionario) service.allFuncionarios().iterator().next();
        Assert.assertEquals(NOME, funcionarioItem.getNomeCompleto());

    }


    @Test
    public void testNoRepository() throws Exception {
        expectedEx.expect(NullPointerException.class);

        FuncionarioService service = new FuncionarioService(null, null);
        service.isPrimary = true;
        service.findFuncionarioByMatricula(MATNR);
    }
}
