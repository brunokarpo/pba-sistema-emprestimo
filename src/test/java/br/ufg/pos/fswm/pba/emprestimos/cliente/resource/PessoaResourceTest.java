package br.ufg.pos.fswm.pba.emprestimos.cliente.resource;

import br.ufg.pos.fswm.pba.emprestimos.EmprestimosApplicationTests;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto.PessoaDTO;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public class PessoaResourceTest extends EmprestimosApplicationTests {

    private static final String NOME = "Milena Sophia Gomes";
    private static final String CPF = "37270463689";
    private static final String SEXO = "FEMININO";
    private static final LocalDate NASCIMENTO = LocalDate.of(1995, Month.FEBRUARY, 22);
    private static final String PROFISS = "Programador de Software";
    private static final BigDecimal SALARIO = new BigDecimal(3500.00);
    private static final String URL_CONSULTA_CADASTRO = "http://dev.consulta-cadastro.nao.existe.com:8080/api/cadastro-positivo/consultar/";
    private static final int NIVEL_3 = 3;

    @MockBean
    private RestTemplate templateMock;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        CadastroPessoaDTO dto = new CadastroPessoaDTO();
        dto.setNome(NOME);
        dto.setCpf(CPF);
        dto.setNascimento(NASCIMENTO);
        dto.setSexo(SEXO);
        dto.setRisco(NIVEL_3);
        when(templateMock.getForObject(URL_CONSULTA_CADASTRO + CPF, CadastroPessoaDTO.class)).thenReturn(dto);
    }

    @Test
    public void deve_ser_possivel_cadastrar_uma_pessoa_nova() throws Exception {
        final PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(NOME);
        pessoaDTO.setCpf(CPF);
        pessoaDTO.setSexo(SEXO);
        pessoaDTO.setNascimento(NASCIMENTO);
        pessoaDTO.setProfissao(PROFISS);
        pessoaDTO.setSalario(SALARIO);

        given()
                .request()
                .header(HEADER_ACCEPT, ContentType.ANY)
                .header(HEADER_CONTENT_TYPE, ContentType.JSON)
                .body(pessoaDTO)
        .when()
        .post("/api/emprestimo/cliente")
        .then()
                .log().headers()
            .and()
                .log().body()
            .and()
                .statusCode(HttpStatus.CREATED.value())
                .headers("Location", equalTo("http://localhost:"+porta+"/api/emprestimo/cliente/37270463689"))
                .body("nome", equalTo("Milena Sophia Gomes"),
                        "cpf", equalTo("37270463689"),
                        "nascimento", equalTo("1995-02-22"),
                        "profissao", equalTo("Programador de Software"),
                        "salario", equalTo(3500),
                        "risco", equalTo("MEDIO"));
    }

    /*
     * TODO: testes a se fazer:
     * * Tentar cadastrar pessoa com CPF inválido
     * * Tentar cadastrar pessoa com dados divergentes do sistema de Cadastro Positivo (no caso de exceção, não deverá ser salvo);
     */
}
