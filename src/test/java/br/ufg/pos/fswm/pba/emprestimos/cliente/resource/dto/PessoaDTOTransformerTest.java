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
 * @date 18/10/17.
 */
public class PessoaDTOTransformerTest {

    private static final String NOME = "Milena Sophia Gomes";
    private static final String CPF = "37270463689";
    private static final String PROFISSAO = "Programador de Software";
    private static final BigDecimal SALARIO = new BigDecimal(3000.00);
    private static final LocalDate NASCIMENTO = LocalDate.of(1995, Month.FEBRUARY, 22);
    public static final String SEXO = "FEMININO";

    private static PessoaDTO dto;
    private static Pessoa pessoa;

    @BeforeClass
    public static void init() throws Exception {
        dto = new PessoaDTO();
        dto.setNome(NOME);
        dto.setCpf(CPF);
        dto.setProfissao(PROFISSAO);
        dto.setSalario(SALARIO);
        dto.setSexo(SEXO);
        dto.setNascimento(NASCIMENTO);

        pessoa = PessoaDTO.PessoaDTOTransformer.criarEntidade(dto);
    }

    @Test
    public void deve_preencher_nome_da_pessoa() throws Exception {
        assertThat(pessoa.getNome()).isEqualTo(NOME);
    }

    @Test
    public void deve_preencher_cpf_da_pessoa() throws Exception {
        assertThat(pessoa.getCpf()).isEqualTo(CPF);
    }

    @Test
    public void deve_preenche_profissao_da_pessoa() throws Exception {
        assertThat(pessoa.getProfissao()).isEqualTo(PROFISSAO);
    }

    @Test
    public void deve_preencher_salario_da_pessoa() throws Exception {
        assertThat(pessoa.getSalario()).isEqualTo(SALARIO);
    }

    @Test
    public void deve_preencher_data_nascimento_da_pessoa() throws Exception {
        assertThat(pessoa.getNascimento()).isEqualTo(NASCIMENTO);
    }

    @Test
    public void deve_preencher_sexo_da_pessoa() throws Exception {
        assertThat(pessoa.getSexo()).isEqualTo(Sexo.FEMININO);
    }
}