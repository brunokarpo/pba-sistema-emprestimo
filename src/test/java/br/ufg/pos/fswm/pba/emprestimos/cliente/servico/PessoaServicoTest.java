package br.ufg.pos.fswm.pba.emprestimos.cliente.servico;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.ConsultaCadastroServico;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Sexo;
import br.ufg.pos.fswm.pba.emprestimos.cliente.repositorio.PessoaRepositorio;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfUnicidadeException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.MenorDeIdadeException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.PessoaServicoException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.impl.PessoaServicoImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    private static final Sexo SEXO = Sexo.FEMININO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private PessoaRepositorio repositorioMock;

    @MockBean
    private ConsultaCadastroServico consultaCadastroMock;

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
        pessoa.setSexo(SEXO);

        sut = new PessoaServicoImpl(repositorioMock, consultaCadastroMock);

        when(repositorioMock.save(pessoa)).thenReturn(pessoa);
        when(repositorioMock.findByCpf(CPF)).thenReturn(Optional.empty());
    }

    @Test
    public void deve_ser_possivel_salvar_nova_pessoa_no_sistema() throws Exception {
        sut.salvar(pessoa);

        verify(repositorioMock).save(pessoa);
    }

    @Test
    public void deve_consultar_sistema_de_cadastro_positivo_apos_salvar_nova_pessoa() throws Exception {
        sut.salvar(pessoa);

        verify(consultaCadastroMock).consultarCadastro(pessoa);
    }

    @Test
    public void nao_deve_permitir_salvar_pessoa_menor_de_idade() throws Exception {
        LocalDate nascimentoMenor = LocalDate.now().minus(12, ChronoUnit.YEARS);
        pessoa.setNascimento(nascimentoMenor);

        expectedException.expect(MenorDeIdadeException.class);
        expectedException.expectMessage("Não pode salvar usuário menor de idade");

        sut.salvar(pessoa);
    }

    @Test
    public void nao_deve_permitir_salvar_pessoa_com_mesmo_cpf_de_outra() throws Exception {
        final Pessoa doBanco = new Pessoa();
        pessoa.setId(3L);
        pessoa.setCpf(CPF);

        when(repositorioMock.findByCpf(CPF)).thenReturn(Optional.of(doBanco));

        expectedException.expect(CpfUnicidadeException.class);
        expectedException.expectMessage("Não pode salvar pessoa com mesmo CPF de outra");

        sut.salvar(pessoa);
    }

    @Test
    public void deve_retornar_exception_quando_comparacao_de_cadastro_positivo_apresentar_divergencia() throws Exception {
        when(consultaCadastroMock.consultarCadastro(pessoa)).thenThrow(DivergenciaDadosException.class);

        expectedException.expect(DivergenciaDadosException.class);

        sut.salvar(pessoa);
    }

    @Test
    public void deve_ser_possivel_buscar_pessoa_utilizando_o_cpf_como_parametro_de_busca() throws Exception {
        when(repositorioMock.findByCpf(CPF)).thenReturn(Optional.of(pessoa));
        when(consultaCadastroMock.consultarCadastro(pessoa)).thenReturn(pessoa);

        Pessoa procurada = sut.buscarPorCpf(CPF);

        assertThat(procurada).isNotNull();
    }

    @Test
    public void deve_retornar_excecao_caso_cpf_procurado_nao_exista() throws Exception {
        expectedException.expect(CpfNaoEncontradoException.class);

        sut.buscarPorCpf(CPF);
    }

    @Test
    public void deve_consultar_o_cadastro_da_pessoa_ao_procurar_por_cpf() throws Exception {
        when(repositorioMock.findByCpf(CPF)).thenReturn(Optional.of(pessoa));

        sut.buscarPorCpf(CPF);

        verify(consultaCadastroMock).consultarCadastro(pessoa);
    }
}