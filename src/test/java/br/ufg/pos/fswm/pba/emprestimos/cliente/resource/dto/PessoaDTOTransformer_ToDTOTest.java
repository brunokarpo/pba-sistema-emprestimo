package br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Sexo;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public class PessoaDTOTransformer_ToDTOTest {

    private static final String NOME = "Milena Sophia Gomes";
    private static final String CPF = "37270463689";
    private static final String PROFISSAO = "Programador de Software";
    private static final BigDecimal SALARIO = new BigDecimal(3000.00);
    private static final LocalDate NASCIMENTO = LocalDate.of(1995, Month.FEBRUARY, 22);
    private static final Sexo SEXO = Sexo.FEMININO;
    private static final String SEXO_STR = "FEMININO";

    private static PessoaDTO dto;
    private static Pessoa pessoa;

    @BeforeClass
    public static void init() throws Exception {
        pessoa = new Pessoa();
        pessoa.setNome(NOME);
        pessoa.setCpf(CPF);
        pessoa.setSexo(SEXO);
        pessoa.setProfissao(PROFISSAO);
        pessoa.setNascimento(NASCIMENTO);
        pessoa.setSalario(SALARIO);

        dto = PessoaDTO.PessoaDTOTransformer.criarDto(pessoa);
    }

    @Test
    public void deve_criar_um_pessoa_dto() throws Exception {
        assertThat(dto).isNotNull();
    }

    @Test
    public void deve_preencher_o_nome_da_pessoa_no_dto() throws Exception {
        assertThat(dto.getNome()).isEqualTo(NOME);
    }

    @Test
    public void deve_preencher_o_cpf_da_pessoa_no_dto() throws Exception {
        assertThat(dto.getCpf()).isEqualTo(CPF);
    }

    @Test
    public void deve_preencher_profissao_da_pessoa_no_dto() throws Exception {
        assertThat(dto.getProfissao()).isEqualTo(PROFISSAO);
    }

    @Test
    public void deve_preencher_salario_da_pessoa_no_dto() throws Exception {
        assertThat(dto.getSalario()).isEqualTo(SALARIO);
    }

    @Test
    public void deve_preencher_nascimento_da_pessoa_no_dto() throws Exception {
        assertThat(dto.getNascimento()).isEqualTo(NASCIMENTO);
    }

    @Test
    public void deve_preencher_sexo_da_pessoa_no_dto_em_formato_string() throws Exception {
        assertThat(dto.getSexo()).isEqualTo(SEXO_STR);
    }
}
