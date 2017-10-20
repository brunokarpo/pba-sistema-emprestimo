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
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
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
    private static final String NASCIMENTO_STRING = "1995-02-22";
    private static final String PROFISSAO = "Programador de Software";
    private static final BigDecimal SALARIO = new BigDecimal(3500.00);
    private static final int SALARIO_NUMERICO = 3500;
    private static final int NIVEL_3 = 3;
    private static final String RISCO_STRING = "MEDIO";
    private static final String URL_CONSULTA_CADASTRO = "http://dev.consulta-cadastro.nao.existe.com:8080/api/cadastro-positivo/consultar/";

    @MockBean
    private RestTemplate templateMock;

    private PessoaDTO pessoaDTO;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(NOME);
        pessoaDTO.setCpf(CPF);
        pessoaDTO.setSexo(SEXO);
        pessoaDTO.setNascimento(NASCIMENTO);
        pessoaDTO.setProfissao(PROFISSAO);
        pessoaDTO.setSalario(SALARIO);

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
                .headers("Location", equalTo("http://localhost:"+porta+"/api/emprestimo/cliente/" + CPF))
                .body("nome", equalTo(NOME),
                        "cpf", equalTo(CPF),
                        "nascimento", equalTo(NASCIMENTO_STRING),
                        "profissao", equalTo(PROFISSAO),
                        "salario", equalTo(SALARIO_NUMERICO),
                        "risco", equalTo(RISCO_STRING));
    }

    @Test
    public void nao_deve_ser_possivel_cadastrar_pessoa_com_o_cpf_invalido() throws Exception {
        pessoaDTO.setCpf("15699846221");

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
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("statusHttp", contains(400),
                            "mensagemUsuario", contains("CPF inválido. Informe um CPF válido"));

    }

    @Test
    public void nao_deve_ser_possivel_possivel_cadastrar_nenhuma_pessoa_menor_de_idade() throws Exception {
        LocalDate nascimentoMenor = LocalDate.now().minus(15, ChronoUnit.YEARS);

        pessoaDTO.setNascimento(nascimentoMenor);

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
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("statusHttp", contains(400),
                            "mensagemUsuario", contains("Não é possível cadastrar menores de idade"));
    }

    /*
     * TODO: testes a se fazer:
     * * Tentar cadastrar pessoa com dados divergentes do sistema de Cadastro Positivo (no caso de exceção, não deverá ser salvo);
     */
}
