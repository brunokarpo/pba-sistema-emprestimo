package br.ufg.pos.fswm.pba.emprestimos.cliente.repositorio;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Sexo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class PessoaRepositorioTest {

    private Pessoa pessoa;

    @Autowired
    private PessoaRepositorio sut;

    @Test
    public void deve_salvar_pessoa_nova_atribuindo_identificador() throws Exception {
        pessoa = new Pessoa();
        pessoa.setNome("Milena Sophia Gomes");
        pessoa.setCpf("37270463689");
        pessoa.setNascimento(LocalDate.of(1995, Month.FEBRUARY, 22));
        pessoa.setSexo(Sexo.FEMININO);
        pessoa.setProfissao("Programador de Software");
        pessoa.setSalario(new BigDecimal(3000.00));

        pessoa = sut.save(pessoa);

        assertThat(pessoa.getId()).isEqualTo(6L);
    }
}