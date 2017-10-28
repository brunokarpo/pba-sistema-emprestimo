package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto;

import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 28/10/17.
 */
public class EmprestimosDisponiveisTransformerTest {

    private static final String CODIGO_EMPRESTIMO_1 = "c018";
    private static final String TITULO_EMPRESTIMO_1 = "Crédito para férias";
    private static final BigDecimal VALOR_CREDITO_EMPRESTIMO_1 = new BigDecimal("500.0");
    private static final BigDecimal JUROS_MES_EMPRESTIMO_1 = new BigDecimal("0.1");
    private static final BigDecimal PARCELAS_EMPRESTIMO_1 = new BigDecimal("78.58");
    private static final int PRESTACOES_EMPRESTIMO_1 = 7;

    private static final String CODIGO_EMPRESTIMO_2 = "H453";
    private static final String TITULO_EMPRESTIMO_2 = "Crédito para emergências";
    private static final BigDecimal VALOR_CREDITO_EMPRESTIMO_2 = new BigDecimal("1000.0");
    private static final BigDecimal JUROS_MES_EMPRESTIMO_2 = new BigDecimal("0.05");
    private static final BigDecimal PARCELAS_EMPRESTIMO_2 = new BigDecimal("105.00");
    private static final int PRESTACOES_EMPRESTIMO_2 = 10;

    private static Emprestimo emprestimo1;
    private static Emprestimo emprestimo2;

    private static EmprestimosDisponiveisDTO dto;

    @BeforeClass
    public static void init() throws Exception {
        emprestimo1 = new Emprestimo();
        emprestimo1.setCodigo(CODIGO_EMPRESTIMO_1);
        emprestimo1.setTitulo(TITULO_EMPRESTIMO_1);
        emprestimo1.setCredito(VALOR_CREDITO_EMPRESTIMO_1);
        emprestimo1.setJurosMes(JUROS_MES_EMPRESTIMO_1);
        emprestimo1.setParcelas(PARCELAS_EMPRESTIMO_1);
        emprestimo1.setPrestacoes(PRESTACOES_EMPRESTIMO_1);

        emprestimo2 = new Emprestimo();
        emprestimo2.setCodigo(CODIGO_EMPRESTIMO_2);
        emprestimo2.setTitulo(TITULO_EMPRESTIMO_2);
        emprestimo2.setCredito(VALOR_CREDITO_EMPRESTIMO_2);
        emprestimo2.setJurosMes(JUROS_MES_EMPRESTIMO_2);
        emprestimo2.setParcelas(PARCELAS_EMPRESTIMO_2);
        emprestimo2.setPrestacoes(PRESTACOES_EMPRESTIMO_2);

        dto = EmprestimosDisponiveisDTO.Transformer.criarDto(Arrays.asList(emprestimo1, emprestimo2));
    }

    @Test
    public void deve_criar_dto_de_emprestimos_disponiveis_na_aplicacao() throws Exception {
        assertThat(dto).describedAs("Objeto não deveria ter retornado com valor nulo").isNotNull();
    }

    @Test
    public void deve_preencher_lista_de_emprestimos_disponiveis_no_dto() throws Exception {
        assertThat(dto.getEmprestimos()).hasSize(2);
    }

    @Test
    public void deve_preencher_codigo_dos_emprestimos_no_dto() throws Exception {
        assertThat(dto.getEmprestimos()).extracting("codigo")
                                        .containsExactlyInAnyOrder(CODIGO_EMPRESTIMO_1, CODIGO_EMPRESTIMO_2);
    }

    @Test
    public void deve_preencher_titulos_dos_emprestimos_no_dto() throws Exception {
        assertThat(dto.getEmprestimos()).extracting("titulo")
                                        .containsExactlyInAnyOrder(TITULO_EMPRESTIMO_2, TITULO_EMPRESTIMO_1);
    }

    @Test
    public void deve_preencher_valor_do_credito_dos_emprestimos_no_dto() throws Exception {
        assertThat(dto.getEmprestimos()).extracting("credito")
                                        .containsExactlyInAnyOrder(VALOR_CREDITO_EMPRESTIMO_1, VALOR_CREDITO_EMPRESTIMO_2);
    }

    @Test
    public void deve_preencher_juros_mensais_dos_emprestimos_no_dto() throws Exception {
        assertThat(dto.getEmprestimos()).extracting("jurosMes")
                                        .containsExactlyInAnyOrder(JUROS_MES_EMPRESTIMO_1, JUROS_MES_EMPRESTIMO_2);
    }

    @Test
    public void deve_preencher_valor_das_parcelas_dos_emprestimos_no_dto() throws Exception {
        assertThat(dto.getEmprestimos()).extracting("parcelas")
                                        .containsExactlyInAnyOrder(PARCELAS_EMPRESTIMO_1, PARCELAS_EMPRESTIMO_2);
    }

    @Test
    public void deve_preencher_quantidades_de_parcelas_dos_emprestimos_no_dto() throws Exception {
        assertThat(dto.getEmprestimos()).extracting("prestacoes")
                                        .containsExactlyInAnyOrder(PRESTACOES_EMPRESTIMO_2, PRESTACOES_EMPRESTIMO_1);
    }
}