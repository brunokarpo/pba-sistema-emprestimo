package br.ufg.pos.fswm.pba.emprestimos.cliente.servico;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.repositorio.PessoaRepositorio;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.impl.PessoaServicoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.verify;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@RunWith(SpringRunner.class)
public class PessoaServicoTest {

    private static final String NOME = "Milena Sophia Gomes";
    private static final String CPF = "37270463689";
    private static final String PROFISSAO = "Programador de Software";
    private static final BigDecimal SALARIO = new BigDecimal(3000.00);
    private static final LocalDate NASCIMENTO = LocalDate.of(1995, Month.FEBRUARY, 22);

    @MockBean
    private PessoaRepositorio repositorioMock;

    private Pessoa pessoa;

    private PessoaServico sut;

    @Before
    public void setUp() throws Exception {
        pessoa = new Pessoa();
        pessoa.setNome(NOME);
        pessoa.setCpf(CPF);
        pessoa.setNascimento(NASCIMENTO);
        pessoa.setProfissao(PROFISSAO);
        pessoa.setSalario(SALARIO);

        sut = new PessoaServicoImpl(repositorioMock);
    }

    @Test
    public void deve_ser_possivel_salvar_nova_pessoa_no_sistema() throws Exception {
        sut.salvar(pessoa);

        verify(repositorioMock).save(pessoa);
    }
}