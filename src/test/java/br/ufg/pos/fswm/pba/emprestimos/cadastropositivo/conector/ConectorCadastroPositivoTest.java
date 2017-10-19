package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.impl.ConectorCadastroPositivoImpl;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Sexo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
@RunWith(SpringRunner.class)
public class ConectorCadastroPositivoTest {

    private static final String NOME = "Milena Sophia Gomes";
    private static final String CPF = "11118124000192";
    private static final LocalDate NASCIMENTO = LocalDate.of(1995, Month.FEBRUARY, 22);
    private static final String SEXO = "FEMININO";
    private static final int NIVEL_1 = 1;
    private static final String URL_CONSULTA_CADASTRO = "http://dev.consulta-cadastro.nao.existe.com:8080/api/cadastro-positivo/consultar/";
    private static final String URL_COMPLETA = URL_CONSULTA_CADASTRO + CPF;

    @MockBean
    private RestTemplate templateMock;

    private ConectorCadastroPositivo sut;

    @Before
    public void setUp() throws Exception {
        sut = new ConectorCadastroPositivoImpl(templateMock);

        final CadastroPessoaDTO dto = new CadastroPessoaDTO();
        dto.setNome(NOME);
        dto.setCpf(CPF);
        dto.setNascimento(NASCIMENTO);
        dto.setSexo(SEXO);
        dto.setRisco(NIVEL_1);

        ReflectionTestUtils.setField(sut, "urlConsultaCadastro", URL_CONSULTA_CADASTRO);

        when(templateMock.getForObject(URL_COMPLETA, CadastroPessoaDTO.class)).thenReturn(dto);
    }

    @Test
    public void deve_buscar_pessoa_no_sistema_de_cadastro_positivo_usando_o_cpf_como_parametro() throws Exception {
        sut.consultarCadastro(CPF);

        verify(templateMock).getForObject(URL_COMPLETA, CadastroPessoaDTO.class);
    }
}