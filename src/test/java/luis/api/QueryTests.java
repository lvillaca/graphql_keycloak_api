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
package luis.api;

import luis.api.dados.AutorizacaoYML;
import luis.api.dados.Funcionario;
import luis.api.service.FuncionarioService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
//@SpringBootTest - cannot run app
public class QueryTests {

    private static String MATNR = "MATNR";
    private static String CLIENTE = "CLIENTE";
    private static String NOT_CLIENTE = "NOT_CLIENTE";

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void testfindFuncionarioByMatricula()  {
        FuncionarioService service =Mockito.mock(FuncionarioService.class);
        Funcionario func = new Funcionario();
        func.setMatricula(MATNR);
        Mockito.doReturn(Optional.of(func)).when(service).findFuncionarioByMatricula(Mockito.anyString());


        Query query = Mockito.spy(Query.class);
        Mockito.doReturn(CLIENTE).when(query).getApplicationId();

        query.funcionarioService = service;

        Assert.assertTrue(query.findFuncionarioByMatricula(MATNR).isPresent());
        Assert.assertEquals(MATNR, query.findFuncionarioByMatricula(MATNR).get().getMatricula());
    }




    @Test
    public void testFindAllFuncionarios() {
        FuncionarioService service =Mockito.mock(FuncionarioService.class);
        Funcionario func = new Funcionario();
        func.setMatricula(MATNR);
        ArrayList<Funcionario> arrayList = new ArrayList();
        arrayList.add(func);
        Mockito.doReturn(arrayList).when(service).allFuncionarios();

        AutorizacaoYML autorizacaoYml = Mockito.mock(AutorizacaoYML.class);
        HashSet<String> clients = new HashSet();
        clients.add(NOT_CLIENTE);
        clients.add(CLIENTE);
        Mockito.doReturn(clients).when(autorizacaoYml).getClientesNP2();

        Query query = Mockito.spy(Query.class);
        Mockito.doReturn(CLIENTE).when(query).getApplicationId();

        query.funcionarioService = service;
        query.autorizacoes = autorizacaoYml;

        Assert.assertTrue(query.allFuncionarios().size()>0);
        Assert.assertEquals(MATNR, query.allFuncionarios().iterator().next().getMatricula());
    }



    @Test
    public void tesDenialfindAllFuncionarios() {
        FuncionarioService service =Mockito.mock(FuncionarioService.class);
        Funcionario func = new Funcionario();
        func.setMatricula(MATNR);
        ArrayList<Funcionario> arrayList = new ArrayList<Funcionario>();
        arrayList.add(func);
        Mockito.doReturn(arrayList).when(service).allFuncionarios();

        AutorizacaoYML autorizacaoYml = Mockito.mock(AutorizacaoYML.class);
        HashSet<String> clients = new HashSet();
        clients.add(NOT_CLIENTE);
        Mockito.doReturn(clients).when(autorizacaoYml).getClientesNP2();

        Query query = Mockito.spy(Query.class);
        Mockito.doReturn(CLIENTE).when(query).getApplicationId();

        query.funcionarioService = service;
        query.autorizacoes = autorizacaoYml;
        System.out.println(query.allFuncionarios());

        int result = query.allFuncionarios().size();
        Assert.assertTrue(result==0);
    }

    @Test
    public void testNoService() throws Exception {
        expectedEx.expect(NullPointerException.class);

        Query query = new Query();
        Assert.assertTrue(query.findFuncionarioByMatricula(
                MATNR).isPresent());
    }
}
