package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo;

import java.util.Arrays;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public enum Risco {
    BAIXO(Constants.NIVEL_1, Constants.NIVEL_2),
    MEDIO(Constants.NIVEL_0, Constants.NIVEL_3),
    ALTO(Constants.NIVEL_4),
    NEGATIVADO(Constants.NIVEL_5);

    private final Integer[] niveis;

    Risco(Integer... niveis) {
        this.niveis =  niveis;
    }

    /**
     * Calcula o nível de Risco do cliente de acordo com o nível recebido pelo Sistema de Consulta Positivo.
     *
     * @param nivel {@link Integer} que representa o nível de risco do cliente
     * @return instância de {@link Risco} de acordo com o cliente;
     */
    public static Risco calculaRisco(Integer nivel) {
        for (Risco risco : Risco.values()) {
            if(Arrays.asList(risco.niveis).contains(nivel)) {
                return risco;
            }
        }
        return Risco.ALTO;
    }

    private static class Constants {
        static final int NIVEL_1 = 1;
        static final int NIVEL_2 = 2;
        static final int NIVEL_0 = 0;
        static final int NIVEL_3 = 3;
        static final int NIVEL_4 = 4;
        static final int NIVEL_5 = 5;
    }
}
