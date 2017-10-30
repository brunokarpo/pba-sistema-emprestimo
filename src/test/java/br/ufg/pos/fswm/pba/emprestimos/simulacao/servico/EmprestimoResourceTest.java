package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico;

import br.ufg.pos.fswm.pba.emprestimos.EmprestimosApplicationTests;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto.RequisicaoEmprestimoDTO;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

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
public class EmprestimoResourceTest extends EmprestimosApplicationTests {

    private static final String NOME = "Juliana Maria Oliveira";
    private static final String CPF = "61584806907";
    private static final String SEXO = "FEMININO";
    private static final LocalDate NASCIMENTO = LocalDate.of(1989, Month.MARCH, 3);
    private static final int RISCO = 4;
    private static final String URL_CONSULTA_CADASTRO = "http://dev.consulta-cadastro.nao.existe.com:8080/api/cadastro-positivo/consultar/";

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
    public void deve_ser_possivel_solicitar_emprestimo_na_aplicacao() throws Exception {
        RequisicaoEmprestimoDTO dto = new RequisicaoEmprestimoDTO();
        dto.setCpf(CPF);
        dto.setCodigoCredito("c018");

        given()
                .request()
                .header(HEADER_ACCEPT, ContentType.ANY)
                .header(HEADER_CONTENT_TYPE, ContentType.JSON)
                .body(dto)
            .when()
            .post("/api/emprestimo")
            .then()
                    .log().headers()
                .and()
                    .log().body()
                .and()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("codigo", equalTo("c018"),
                            "titulo", equalTo("Crédito para férias"),
                            "credito", comparesEqualTo(2500.0f),
                            "jurosMes", comparesEqualTo(0.1f),
                            "parcelas", comparesEqualTo(385.0f),
                            "prestacoes", equalTo(8));
    }
}
