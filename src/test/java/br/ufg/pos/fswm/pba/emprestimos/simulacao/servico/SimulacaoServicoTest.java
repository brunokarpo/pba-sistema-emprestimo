package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo.Risco;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Sexo;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.PessoaServico;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Contrato;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.repositorio.ContratoRepositorio;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.impl.SimulacaoServicoImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 28/10/17.
 */
@RunWith(SpringRunner.class)
public class SimulacaoServicoTest {

    private static final String NOME = "Milena Sophia Gomes";
    private static final String CPF = "37270463689";
    private static final String PROFISSAO = "Programador de Software";
    private static final BigDecimal SALARIO = new BigDecimal(3000.00);
    private static final LocalDate NASCIMENTO = LocalDate.of(1995, Month.FEBRUARY, 22);
    private static final Sexo SEXO = Sexo.FEMININO;
    private static final Risco RISCO = Risco.ALTO;
    private static final List<Risco> RISCOS_ACEITOS_CONTRATO_2 = Arrays.asList(RISCO);
    private static final List<Risco> RISCOS_ACEITOS_CONTRATO_1 = Arrays.asList(RISCO);
    private static final String CODIGO_CONTRATO_1 = "a123";
    private static final String TITULO_CONTRATO_1 = "titulo contrato 1";
    private static final BigDecimal PERCENTUAL_SALARIO_EMPRESTIMO_CONTRATO_1 = BigDecimal.valueOf(0.5);
    private static final BigDecimal PERCENTUAL_SALARIO_COMPROMETIDO_CONTRATO_1 = BigDecimal.valueOf(0.07);
    private static final BigDecimal JUROS_MES_CONTRATO_1 = BigDecimal.valueOf(0.1);
    private static final String CODIGO_CONTRATO_2 = "b456";
    private static final String TITULO_CONTRATO_2 = "titulo contrato 2";
    private static final BigDecimal PERCENTUAL_SALARIO_EMPRESTIMO_CONTRATO_2 = BigDecimal.valueOf(1);
    private static final BigDecimal PERCENTUAL_SALARIO_COMPROMETIDO_CONTRATO_2 = BigDecimal.valueOf(0.2);
    private static final BigDecimal JUROS_MES_CONTRATO_2 = BigDecimal.valueOf(0.05);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private PessoaServico pessoaServicoMock;

    @MockBean
    private ContratoRepositorio contratoRepositorioMock;

    private Pessoa pessoa;
    private Contrato contrato1;
    private Contrato contrato2;

    private SimulacaoServico sut;

    @Before
    public void setUp() throws Exception {
        sut = new SimulacaoServicoImpl(pessoaServicoMock, contratoRepositorioMock);

        configurarMockPessoa();

        configurarMockContratos();
    }

    private void configurarMockContratos() {
        contrato1 = new Contrato();
        contrato1.setCodigo(CODIGO_CONTRATO_1);
        contrato1.setTitulo(TITULO_CONTRATO_1);
        contrato1.setPercentualSalarioEmprestimo(PERCENTUAL_SALARIO_EMPRESTIMO_CONTRATO_1);
        contrato1.setPercentualSalarioComprometido(PERCENTUAL_SALARIO_COMPROMETIDO_CONTRATO_1);
        contrato1.setJurosMes(JUROS_MES_CONTRATO_1);
        contrato1.setRiscosAceitos(RISCOS_ACEITOS_CONTRATO_1);

        contrato2 = new Contrato();
        contrato2.setCodigo(CODIGO_CONTRATO_2);
        contrato2.setTitulo(TITULO_CONTRATO_2);
        contrato2.setPercentualSalarioEmprestimo(PERCENTUAL_SALARIO_EMPRESTIMO_CONTRATO_2);
        contrato2.setPercentualSalarioComprometido(PERCENTUAL_SALARIO_COMPROMETIDO_CONTRATO_2);
        contrato2.setJurosMes(JUROS_MES_CONTRATO_2);
        contrato2.setRiscosAceitos(RISCOS_ACEITOS_CONTRATO_2);

        when(contratoRepositorioMock.findByRiscosAceitos(RISCO)).thenReturn(Arrays.asList(contrato1, contrato2));
    }

    private void configurarMockPessoa() throws Exception {
        pessoa = new Pessoa();
        pessoa.setNome(NOME);
        pessoa.setCpf(CPF);
        pessoa.setNascimento(NASCIMENTO);
        pessoa.setProfissao(PROFISSAO);
        pessoa.setSalario(SALARIO);
        pessoa.setSexo(SEXO);
        pessoa.setRisco(RISCO);

        when(pessoaServicoMock.buscarPorCpf(CPF)).thenReturn(pessoa);
    }

    @Test
    public void deve_verificar_se_existe_pessoa_cadastrada_com_o_cpf_recebido_por_parametro() throws Exception {
        sut.simularEmprestimo(CPF);

        verify(pessoaServicoMock).buscarPorCpf(CPF);
    }

    @Test
    public void deve_lancar_excecao_caso_cpf_utilizado_para_simular_emprestimo_nao_exista_cadastrado_na_aplicacao() throws Exception {
        when(pessoaServicoMock.buscarPorCpf(CPF)).thenThrow(CpfNaoEncontradoException.class);

        expectedException.expect(CpfNaoEncontradoException.class);

        sut.simularEmprestimo(CPF);
    }

    @Test
    public void deve_devolver_emprestimos_disponiveis_para_pessoa_de_acordo_com_seu_risco() throws Exception {
        List<Emprestimo> emprestimos = sut.simularEmprestimo(CPF);

        assertThat(emprestimos).hasSize(2).extracting("codigo", "titulo", "credito", "jurosMes", "prestacoes", "parcelas")
                .usingComparatorForElementFieldsWithType(BigDecimal::compareTo, BigDecimal.class)
                .contains(tuple(CODIGO_CONTRATO_1, TITULO_CONTRATO_1, BigDecimal.valueOf(1500.0), JUROS_MES_CONTRATO_1, new Integer(8), new BigDecimal("231.000")),
                        tuple(CODIGO_CONTRATO_2, TITULO_CONTRATO_2, BigDecimal.valueOf(3000), JUROS_MES_CONTRATO_2, new Integer(5), new BigDecimal("630.000")));
    }

}