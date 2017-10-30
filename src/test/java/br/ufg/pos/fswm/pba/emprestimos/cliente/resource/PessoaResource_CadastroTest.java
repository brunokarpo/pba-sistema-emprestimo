package br.ufg.pos.fswm.pba.emprestimos.cliente.resource;

import br.ufg.pos.fswm.pba.emprestimos.EmprestimosApplicationTests;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto.PessoaDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public class PessoaResource_CadastroTest extends EmprestimosApplicationTests {

    private static final String NOME = "Milena Sophia Gomes";
    private static final String CPF = "37270463689";
    private static final String SEXO = "FEMININO";
    private static final LocalDate NASCIMENTO = LocalDate.of(1995, Month.FEBRUARY, 22);
    private static final String NASCIMENTO_STRING = "1995-02-22";
    private static final String PROFISSAO = "Programador de Software";
    private static final BigDecimal SALARIO = new BigDecimal(3500.00);
    private static final int SALARIO_NUMERICO = 3500;
    private static final int NIVEL_3 = 3;
    private static final String RISCO_MEDIO_STRING = "MEDIO";
    private static final String URL_CONSULTA_CADASTRO = "http://dev.consulta-cadastro.nao.existe.com:8080/api/cadastro-positivo/consultar/";
    public static final String PROFISSAO_DESEMPREGADO = "Desempregado";
    public static final String RISCO_ALTO_STRING = "ALTO";

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
        executarPostParaSalvarNovaPessoa()
                .statusCode(HttpStatus.CREATED.value())
                .headers("Location", equalTo("http://localhost:"+porta+"/api/emprestimo/cliente/" + CPF))
                .body("nome", equalTo(NOME),
                        "cpf", equalTo(CPF),
                        "nascimento", equalTo(NASCIMENTO_STRING),
                        "profissao", equalTo(PROFISSAO),
                        "salario", equalTo(SALARIO_NUMERICO),
                        "risco", equalTo(RISCO_MEDIO_STRING));
    }

    @Test
    public void deve_cadastrar_cliente_ainda_que_sistema_de_cadastro_positivo_apresente_erro_retornando_pessoa_com_risco_medio() throws Exception {
        when(templateMock.getForObject(URL_CONSULTA_CADASTRO + CPF, CadastroPessoaDTO.class)).thenThrow(RestClientException.class);

        executarPostParaSalvarNovaPessoa()
                .statusCode(HttpStatus.CREATED.value())
                .headers("Location", equalTo("http://localhost:"+porta+"/api/emprestimo/cliente/" + CPF))
                .body("nome", equalTo(NOME),
                        "cpf", equalTo(CPF),
                        "nascimento", equalTo(NASCIMENTO_STRING),
                        "profissao", equalTo(PROFISSAO),
                        "salario", equalTo(SALARIO_NUMERICO),
                        "risco", equalTo(RISCO_ALTO_STRING));
    }

    @Test
    public void nao_deve_ser_possivel_cadastrar_pessoa_com_o_cpf_invalido() throws Exception {
        pessoaDTO.setCpf("15699846221");

        executarPostParaSalvarNovaPessoa()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("statusHttp", contains(400),
                            "mensagemUsuario", contains("CPF inválido. Informe um CPF válido"));

    }

    @Test
    public void deve_preencher_desempregado_caso_cliente_nao_informe_profissao() throws Exception {
        pessoaDTO.setProfissao(null);

        executarPostParaSalvarNovaPessoa()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("profissao", equalTo(PROFISSAO_DESEMPREGADO));
    }

    @Test
    public void nao_deve_ser_possivel_cadastrar_nenhuma_pessoa_menor_de_idade() throws Exception {
        LocalDate nascimentoMenor = LocalDate.now().minus(15, ChronoUnit.YEARS);

        pessoaDTO.setNascimento(nascimentoMenor);

        executarPostParaSalvarNovaPessoa()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("statusHttp", contains(400),
                            "mensagemUsuario", contains("Não é possível cadastrar menores de idade"));
    }

    @Test
    public void nao_deve_ser_possivel_cadastrar_pessoa_com_mesmo_cpf_de_outra() throws Exception {
        pessoaDTO.setCpf("61584806907");
        executarPostParaSalvarNovaPessoa()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("statusHttp", contains(400),
                            "mensagemUsuario", contains("Já existe outra pessoa cadastrada com esse CPF"));
    }

    @Test
    public void nao_deve_ser_possivel_cadastrar_pessoa_sem_informar_o_nome() throws Exception {
        pessoaDTO.setNome(null);

        executarPostParaSalvarNovaPessoa()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("statusHttp", contains(400),
                            "mensagemUsuario", contains("O nome da pessoa é obrigatório"));
    }

    @Test
    public void nao_deve_ser_possivel_cadastrar_pessoa_sem_informar_data_de_nascimento() throws Exception {
        pessoaDTO.setNascimento(null);

        executarPostParaSalvarNovaPessoa()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusHttp", contains(400),
                        "mensagemUsuario", contains("A data de nascimento é obrigatória"));
    }

    @Test
    public void nao_deve_ser_possivel_cadstrar_pessoa_sem_informar_sexo() throws Exception {
        pessoaDTO.setSexo(null);

        executarPostParaSalvarNovaPessoa()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusHttp", contains(400),
                        "mensagemUsuario", contains("O sexo da pessoa é obrigatório"));
    }

    @Test
    public void nao_deve_ser_possivel_cadastrar_pessoa_informando_sexo_invalido() throws Exception {
        pessoaDTO.setSexo("Elefante azul de patas brancas");

        executarPostParaSalvarNovaPessoa()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusHttp", contains(400),
                        "mensagemUsuario", contains("Valor 'Elefante azul de patas brancas' não reconhecido como Sexo válido"));
    }

    @Test
    public void nao_deve_salvar_pessoa_caso_os_dados_recebidos_estejam_divergentes_dos_dados_do_sistema_de_cadastro_positivo() throws Exception {
        pessoaDTO.setNascimento(LocalDate.of(1990, Month.APRIL, 18));

        executarPostParaSalvarNovaPessoa()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusHttp", contains(400),
                        "mensagemUsuario", contains("O 'Nascimento' informado pelo cliente diverge do Sistema de Cadastro Positivo"));
    }

    private ValidatableResponse executarPostParaSalvarNovaPessoa() {
        return given()
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
                .and();
    }
}
