package br.ufg.pos.fswm.pba.emprestimos.simulacao.repositorio;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo.Risco;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Contrato;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 29/10/17.
 */
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class ContratoRepositorioTest {

    @Autowired
    private ContratoRespositorio sut;

    @Test
    public void deve_ser_possivel_buscar_contrato_utilizando_codigo_como_parametro_de_busca() throws Exception {
        Optional<Contrato> optional = sut.findByCodigo("y88y");

        assertThat(optional.isPresent()).isTrue();

        final Contrato contrato = optional.get();
        assertThat(contrato.getJurosMes()).isEqualByComparingTo(new BigDecimal("0.15"));
        assertThat(contrato.getPercentualSalarioComprometido()).isEqualByComparingTo(new BigDecimal("0.1"));
        assertThat(contrato.getPercentualSalarioEmprestimo()).isEqualByComparingTo(new BigDecimal("5"));
        assertThat(contrato.getTitulo()).isEqualTo("Crédito para quitação de débitos");
        assertThat(contrato.getRiscosAceitos()).contains(Risco.BAIXO, Risco.MEDIO);
    }

    @Test
    public void deve_ser_possivel_buscar_contratos_utilizando_os_nivel_de_risco_envolvido() throws Exception {
        List<Contrato> contratos = sut.findByRiscosAceitos(Risco.MEDIO);

        assertThat(contratos).hasSize(4);
        assertThat(contratos).extracting("codigo")
                .containsExactlyInAnyOrder("c018", "h453", "y88y", "ad56");
    }
}
