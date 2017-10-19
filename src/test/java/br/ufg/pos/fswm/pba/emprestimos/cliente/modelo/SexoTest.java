package br.ufg.pos.fswm.pba.emprestimos.cliente.modelo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public class SexoTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deve_ser_possivel_buscar_instancia_de_sexo_masculino_atraves_do_nome_em_formato_string() throws Exception {
        final String sexoStr = "MASCULINO";

        Sexo sexo = Sexo.fromNome(sexoStr);

        assertThat(sexo).isEqualTo(Sexo.MASCULINO);
    }

    @Test
    public void deve_retornar_instancia_de_sexo_feminino_independente_do_case_dos_caracteres() throws Exception {
        final String sexoStr = "feMinINo";

        Sexo sexo = Sexo.fromNome(sexoStr);

        assertThat(sexo).isEqualTo(Sexo.FEMININO);
    }

    @Test
    public void deve_retornar_um_illegal_argument_exception_caso_valor_informado_nao_esteja_dentro_do_esperado() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Valor 'trololo' não reconhecido como Sexo válido");

        final String sexoStr = "trololo";

        Sexo sexo = Sexo.fromNome(sexoStr);
    }
}