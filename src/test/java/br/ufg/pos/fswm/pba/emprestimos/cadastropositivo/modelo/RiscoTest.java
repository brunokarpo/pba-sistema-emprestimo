package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public class RiscoTest {

    public static final int NIVEL_0 = 0;
    public static final int NIVEL_1 = 1;
    public static final int NIVEL_2 = 2;
    public static final int NIVEL_3 = 3;
    public static final int NIVEL_4 = 4;
    public static final int NIVEL_5 = 5;
    public static final int NIVEL_NAO_MAPEADO = 159;

    @Test
    public void deve_retornar_risco_medio_para_cliente_com_risco_nivel_0() throws Exception {
        Risco risco = Risco.calculaRisco(NIVEL_0);

        assertThat(risco).isEqualTo(Risco.MEDIO);
    }

    @Test
    public void deve_retornar_risco_baixo_para_cliente_com_risco_nivel_1() throws Exception {
        Risco risco = Risco.calculaRisco(NIVEL_1);

        assertThat(risco).isEqualTo(Risco.BAIXO);
    }

    @Test
    public void deve_retornar_risco_baixo_para_cliente_com_risco_nivel_2() throws Exception {
        Risco risco = Risco.calculaRisco(NIVEL_2);

        assertThat(risco).isEqualTo(Risco.BAIXO);
    }

    @Test
    public void deve_retornar_risco_medio_para_cliente_com_risco_nivel_3() throws Exception {
        Risco risco = Risco.calculaRisco(NIVEL_3);

        assertThat(risco).isEqualTo(Risco.MEDIO);
    }

    @Test
    public void deve_retornar_risco_alto_para_cliente_com_risco_nivel_4() throws Exception {
        Risco risco = Risco.calculaRisco(NIVEL_4);

        assertThat(risco).isEqualTo(Risco.ALTO);
    }

    @Test
    public void deve_retornar_risco_negativado_para_cliente_com_risco_nivel_5() throws Exception {
        Risco risco = Risco.calculaRisco(NIVEL_5);

        assertThat(risco).isEqualTo(Risco.NEGATIVADO);
    }

    @Test
    public void deve_tratar_como_alto_risco_todo_cliente_com_risco_de_nivel_nao_mapeado() throws Exception {
        Risco risco = Risco.calculaRisco(NIVEL_NAO_MAPEADO);

        assertThat(risco).isEqualTo(Risco.ALTO);
    }
}