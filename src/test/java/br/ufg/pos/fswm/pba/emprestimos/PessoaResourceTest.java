package br.ufg.pos.fswm.pba.emprestimos;

import br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto.PessoaDTO;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public class PessoaResourceTest extends EmprestimosApplicationTests {

    @Test
    public void deve_ser_possivel_cadastrar_uma_pessoa_nova() throws Exception {
        final PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Milena Sophia Gomes");
        pessoaDTO.setCpf("37270463689");
        pessoaDTO.setSexo("FEMININO");
        pessoaDTO.setNascimento(LocalDate.of(1995, Month.FEBRUARY, 22));
        pessoaDTO.setProfissao("Programador de Software");
        pessoaDTO.setSalario(new BigDecimal(3500.00));

        given()
                .request()
                .header(HEADER_ACCEPT, ContentType.ANY)
                .header(HEADER_CONTENT_TYPE, ContentType.JSON)
                .body(pessoaDTO)
        .when()
        .post("/api/emprestimo/cliente/cadastrar")
        .then()
                .log().headers()
            .and()
                .log().body()
            .and()
                .statusCode(HttpStatus.CREATED.value())
                .headers("Location", equalTo("http://localhost:"+porta+"/api/emprestimo/consultar/37270463689"))
                .body("nome", equalTo("Milena Sophia Gomes"),
                        "cpf", equalTo("37270463689"),
                        "nascimento", equalTo("1995-02-22"),
                        "profissao", equalTo("Programador de Software"),
                        "salario", equalTo(LocalDate.of(1995, Month.FEBRUARY, 22)),
                        "risco", equalTo("MEDIO"));

    }
}
