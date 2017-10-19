package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.ConectorCadastroPositivo;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.impl.ConsultaCadastroServicoImpl;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Sexo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
@RunWith(SpringRunner.class)
public class ConsultaCadastroServicoTest {

    private static final String NOME = "Milena Sophia Gomes";
    private static final String CPF = "37270463689";
    private static final String PROFISSAO = "Programador de Software";
    private static final BigDecimal SALARIO = new BigDecimal(3000.00);
    private static final LocalDate NASCIMENTO = LocalDate.of(1995, Month.FEBRUARY, 22);
    private static final Sexo SEXO = Sexo.FEMININO;

    @MockBean
    private ConectorCadastroPositivo conectorCadastroPositivoMock;

    private ConsultaCadastroServico sut;

    private Pessoa pessoa;
    private CadastroPessoaDTO dto;

    @Before
    public void setUp() throws Exception {
        pessoa = new Pessoa();
        pessoa.setNome(NOME);
        pessoa.setCpf(CPF);
        pessoa.setNascimento(NASCIMENTO);
        pessoa.setProfissao(PROFISSAO);
        pessoa.setSalario(SALARIO);
        pessoa.setSexo(SEXO);

        sut = new ConsultaCadastroServicoImpl(conectorCadastroPositivoMock);

        dto = new CadastroPessoaDTO();
        dto.setNome(NOME);
        dto.setCpf(CPF);
        dto.setNascimento(NASCIMENTO);
        dto.setSexo(SEXO.name());
        dto.setRisco(1);
        when(conectorCadastroPositivoMock.consultarCadastro(CPF)).thenReturn(dto);
    }

    /*
     * Fluxo:
     * * Pega o CPF da pessoa e consulta no sistema de cadastro (cria uma interface que expoe esse servico). Esse cara
     *      é o responsável por conectar no serviço de consulta de cadastro e retornar um objeto com a representação dos
     *      dados encontrados.
     * * Pega o objeto retornado e verifica se os dados fornecidos pelo cliente estão em consonância com os dados do
     *      sistema de cadastro positivo. Se não estiver, tem de dar um erro informando que os dados não são compatíveis;
     * * Com os dados do sistema de cadastro positivo o risco da pessoa é calculado;
     * * O risco é preenchido no objeto pessoa e ele é retornado.
     */

    @Test
    public void deve_realizar_uma_consulta_no_sistema_de_cadastro_positivo_utilizando_o_cpf_como_parametro_de_busca() throws Exception {
        sut.consultarCadastro(pessoa);

        verify(conectorCadastroPositivoMock).consultarCadastro(CPF);
    }
}