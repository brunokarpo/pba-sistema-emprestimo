package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto.RequisicaoEmprestimoDTO;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions.EmprestimoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions.EmprestimoNaoDisponivelException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions.PessoaNegativadaException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.impl.EmprestimoServicoImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 30/10/17.
 */
@RunWith(SpringRunner.class)
public class EmprestimoServicoTest {

    private static final String CODIGO_EMPRESTIMO_1 = "b120";
    private static final String TITULO_EMPRESTIMO_1 = "Titulo emprestimo 1";
    private static final BigDecimal CREDITO_EMPRESTIMO_1 = BigDecimal.valueOf(3000.0);
    private static final BigDecimal PARCELAS_EMPRESTIMO_1 = BigDecimal.valueOf(300.0);
    private static final BigDecimal JUROS_MES_EMPRESTIMO_1 = BigDecimal.valueOf(0.1);
    private static final int PRESTACOES_EMPRESTIMOS_1 = 10;

    private static final String CODIGO_EMPRESTIMO_2 = "a453";
    private static final String TITULO_EMPRESTIMO_2 = "Titulo emprestimo 2";
    private static final BigDecimal CREDITO_EMPRESTIMO_2 = BigDecimal.valueOf(6000.0);
    private static final BigDecimal PARCELAS_EMPRESTIMO_2 = BigDecimal.valueOf(600.0);
    private static final BigDecimal JUROS_MES_EMPRESTIMO_2 = BigDecimal.valueOf(0.2);
    private static final int PRESTACOES_EMPRESTIMOS_2 = 5;

    private static final String CPF = "53464103099";

    @MockBean
    private SimulacaoServico simulacaoServicoMock;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Emprestimo emprestimo1;
    private Emprestimo emprestimo2;

    private EmprestimoServico sut;
    private RequisicaoEmprestimoDTO requisicaoEmprestimoDTO;

    @Before
    public void setUp() throws Exception {
        configurarSimulacaoServicoMock();

        requisicaoEmprestimoDTO = new RequisicaoEmprestimoDTO();
        requisicaoEmprestimoDTO.setCpf(CPF);
        requisicaoEmprestimoDTO.setCodigoCredito(CODIGO_EMPRESTIMO_2);

        sut = new EmprestimoServicoImpl(simulacaoServicoMock);
    }

    private void configurarSimulacaoServicoMock() throws Exception{
        emprestimo1 = new Emprestimo();
        emprestimo1.setCodigo(CODIGO_EMPRESTIMO_1);
        emprestimo1.setTitulo(TITULO_EMPRESTIMO_1);
        emprestimo1.setCredito(CREDITO_EMPRESTIMO_1);
        emprestimo1.setParcelas(PARCELAS_EMPRESTIMO_1);
        emprestimo1.setJurosMes(JUROS_MES_EMPRESTIMO_1);
        emprestimo1.setPrestacoes(PRESTACOES_EMPRESTIMOS_1);

        emprestimo2 = new Emprestimo();
        emprestimo2.setCodigo(CODIGO_EMPRESTIMO_2);
        emprestimo2.setTitulo(TITULO_EMPRESTIMO_2);
        emprestimo2.setCredito(CREDITO_EMPRESTIMO_2);
        emprestimo2.setParcelas(PARCELAS_EMPRESTIMO_2);
        emprestimo2.setJurosMes(JUROS_MES_EMPRESTIMO_2);
        emprestimo2.setPrestacoes(PRESTACOES_EMPRESTIMOS_2);

        when(simulacaoServicoMock.simularEmprestimo(CPF)).thenReturn(Arrays.asList(emprestimo1, emprestimo2));
    }

    @Test
    public void deve_ser_possivel_solicitar_emprestimo_com_codigo_e_cpf_valido() throws Exception {
        final Emprestimo emprestimo = sut.solicitar(requisicaoEmprestimoDTO);

        assertThat(emprestimo).isNotNull();
        assertThat(emprestimo.getCodigo()).isEqualTo(CODIGO_EMPRESTIMO_2);
        assertThat(emprestimo.getTitulo()).isEqualTo(TITULO_EMPRESTIMO_2);
        assertThat(emprestimo.getCredito()).isEqualByComparingTo(CREDITO_EMPRESTIMO_2);
        assertThat(emprestimo.getJurosMes()).isEqualByComparingTo(JUROS_MES_EMPRESTIMO_2);
        assertThat(emprestimo.getParcelas()).isEqualByComparingTo(PARCELAS_EMPRESTIMO_2);
        assertThat(emprestimo.getPrestacoes()).isEqualTo(PRESTACOES_EMPRESTIMOS_2);
    }

    @Test
    public void deve_retornar_excecao_de_emprestimo_nao_disponivel_caso_codigo_emprestimo_nao_corresponda_a_nenhum_emprestimo_disponivel() throws Exception {
        requisicaoEmprestimoDTO.setCodigoCredito("aaaa");

        expectedException.expect(EmprestimoNaoDisponivelException.class);
        expectedException.expectMessage("Empréstimo com código 'aaaa' não disponível para cliente com CPF '" + CPF + "'");

        sut.solicitar(requisicaoEmprestimoDTO);
    }

    @Test
    public void nao_deve_gerar_emprestimo_para_cliente_negativado_na_aplicacao() throws Exception {
        when(simulacaoServicoMock.simularEmprestimo(CPF)).thenThrow(PessoaNegativadaException.class);

        expectedException.expect(PessoaNegativadaException.class);

        sut.solicitar(requisicaoEmprestimoDTO);
    }

    @Test
    public void nao_deve_gerar_emprestimo_para_cliente_nao_cadastrado_no_sistema() throws Exception {
        when(simulacaoServicoMock.simularEmprestimo(CPF)).thenThrow(CpfNaoEncontradoException.class);

        expectedException.expect(CpfNaoEncontradoException.class);

        sut.solicitar(requisicaoEmprestimoDTO);
    }

    @Test
    public void deve_gerar_excecao_generica_caso_ocorra_problemas_na_hora_de_consultar_cadastro_positivo() throws Exception {
        when(simulacaoServicoMock.simularEmprestimo(CPF)).thenThrow(DivergenciaDadosException.class);

        expectedException.expect(EmprestimoException.class);

        sut.solicitar(requisicaoEmprestimoDTO);
    }

    /**
     * TODO: Como deve funcionar?
     * * Dado o CPF, deve procurar no serviço de simulação os empréstimos disponíveis.
     * * Compara o código da solicitação com os dos emprestimos disponíveis. Se:
     * * * Existir um equivalente: salva o empréstimo no banco de dados e devolve o empréstimo preenchido.
     * * * Se não existir código: retorna exceção informando que o empréstimo não está disponível para esse cliente.
     */
}