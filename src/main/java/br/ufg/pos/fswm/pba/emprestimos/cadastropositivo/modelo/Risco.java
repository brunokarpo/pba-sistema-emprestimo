package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public enum Risco {
    BAIXO,
    MEDIO,
    ALTO,
    NEGATIVADO;

    /**
     * Calcula o nível de Risco do cliente de acordo com o nível recebido pelo Sistema de Consulta Positivo.
     *
     * @param nivel {@link Integer} que representa o nível de risco do cliente
     * @return instância de {@link Risco} de acordo com o cliente;
     */
    public static Risco calculaRisco(Integer nivel) {
        switch(nivel.intValue()) {
            case 0:
            case 3:
                return Risco.MEDIO;
            case 1:
            case 2:
                return Risco.BAIXO;
            case 5:
                return Risco.NEGATIVADO;
            case 4:
            default:
                return Risco.ALTO;
        }
    }
}
