package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource;

import br.ufg.pos.fswm.pba.emprestimos.EmprestimosApplicationTests;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto.PessoaDTO;
import org.hamcrest.number.BigDecimalCloseTo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 26/10/17.
 */
public class SimulacaoResourceTest extends EmprestimosApplicationTests{

    private static final String NOME = "Juliana Maria Oliveira";
    private static final String CPF = "61584806907";
    private static final String SEXO = "FEMININO";
    private static final LocalDate NASCIMENTO = LocalDate.of(1989, Month.MARCH, 3);
    private static final int RISCO = 4;
    private static final String URL_CONSULTA_CADASTRO = "http://dev.consulta-cadastro.nao.existe.com:8080/api/cadastro-positivo/consultar/";

    @MockBean
    private RestTemplate templateMock;

    private PessoaDTO pessoaDTO;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        CadastroPessoaDTO dto = new CadastroPessoaDTO();
        dto.setNome(NOME);
        dto.setCpf(CPF);
        dto.setNascimento(NASCIMENTO);
        dto.setSexo(SEXO);
        dto.setRisco(RISCO);
        when(templateMock.getForObject(URL_CONSULTA_CADASTRO + CPF, CadastroPessoaDTO.class)).thenReturn(dto);
    }

    @Test
    public void deve_ser_possivel_solicitar_simulacao_de_emprestimo_para_pessoa_de_risco_alto() throws Exception {
        given()
                .pathParam("cpf", CPF)
        .when()
        .get("/api/emprestimo/simulacao/{cpf}")
        .then()
                .log().headers()
            .and()
                .log().body()
            .and()
                .statusCode(HttpStatus.OK.value())
                .body("emprestimos-disponiveis.codigo", containsInAnyOrder("c018", "h453"))
                .body("emprestimos-disponiveis.titulo", containsInAnyOrder("Crédito para férias", "Crédito para emergências"))
                .body("emprestimos-disponiveis.credito", containsInAnyOrder(comparesEqualTo(2500.0f), comparesEqualTo(5000.0f)))
                .body("emprestimos-disponiveis.jurosMes", containsInAnyOrder(comparesEqualTo(0.1f), comparesEqualTo(0.05f)))
                .body("emprestimos-disponiveis.prestacoes", containsInAnyOrder(8, 10))
                .body("emprestimos-disponiveis.parcelas", containsInAnyOrder(comparesEqualTo(385.0f), comparesEqualTo(525.0f)));
    }
}
