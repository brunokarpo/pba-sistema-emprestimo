package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico;

import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.PessoaServico;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.impl.SimulacaoServicoImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 28/10/17.
 */
@RunWith(SpringRunner.class)
public class SimulacaoServicoTest {

    public static final String CPF = "89900534042";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private PessoaServico pessoaServicoMock;

    private SimulacaoServico sut;

    @Before
    public void setUp() throws Exception {
        sut = new SimulacaoServicoImpl(pessoaServicoMock);
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

    /*
     * TODO: como deve funcionar:
     * * Ao receber um CPF deve verificar se o usuário existe cadastrado na aplicação.
     * * * Caso o cliente não existir na aplicação, deve retornar uma exceção para pedir para cadastrar o usuário primeiramente.
     * * * Ao pesquisar o usuário ele deverá retornar com o nível de risco, pesquisado no sistema de cadastro positivo.
     * * Com o nível de risco, deve buscar quais são os 'contratos' disponíveis para aquele nível de risco
     * * Deve preencher o(s) emprestímo(s) para retornar de acordo com os dados do cliente.
     */
}