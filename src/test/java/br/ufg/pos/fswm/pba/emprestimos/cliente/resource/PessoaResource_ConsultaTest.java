package br.ufg.pos.fswm.pba.emprestimos.cliente.resource;

import br.ufg.pos.fswm.pba.emprestimos.EmprestimosApplicationTests;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 30/10/17.
 */
public class PessoaResource_ConsultaTest extends EmprestimosApplicationTests {

    private static final String NOME = "Juliana Maria Oliveira";
    private static final String CPF = "61584806907";
    private static final String SEXO = "FEMININO";
    private static final LocalDate NASCIMENTO = LocalDate.of(1989, Month.MARCH, 3);
    private static final String NASCIMENTO_STR = "1989-03-03";
    private static final int RISCO = 4;
    private static final String URL_CONSULTA_CADASTRO = "http://dev.consulta-cadastro.nao.existe.com:8080/api/cadastro-positivo/consultar/";

    private static final String PROFISSAO = "Estilista";
    private static final float SALARIO = 5000.00f;
    private static final String RISCO_ALTO_STRING = "ALTO";

    @MockBean
    private RestTemplate templateMock;

    private CadastroPessoaDTO dto;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        dto = new CadastroPessoaDTO();
        dto.setNome(NOME);
        dto.setCpf(CPF);
        dto.setNascimento(NASCIMENTO);
        dto.setSexo(SEXO);
        dto.setRisco(RISCO);
        when(templateMock.getForObject(URL_CONSULTA_CADASTRO + CPF, CadastroPessoaDTO.class)).thenReturn(dto);
    }

    @Test
    public void deve_ser_possivel_consultar_dados_da_pessoa_utilizando_cpf_como_parametro_de_busca() throws Exception {
        given()
                .pathParam("cpf", CPF)
            .when()
            .get("/api/emprestimo/cliente/{cpf}")
            .then()
                    .log().headers()
                .and()
                    .log().body()
                .and()
                    .statusCode(HttpStatus.OK.value())
                    .body("nome", equalTo(NOME),
                            "sexo", equalTo(SEXO),
                            "cpf", equalTo(CPF),
                            "nascimento", equalTo(NASCIMENTO_STR),
                            "profissao", equalTo(PROFISSAO),
                            "salario", comparesEqualTo(SALARIO),
                            "risco", equalTo(RISCO_ALTO_STRING));
    }
}
