package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions;

/**
 * Representa uma exce&ccedil;&atilde;o de sistema quando os dados informados pelo Cliente est&atilde;o em
 * diverg&ecirc;ncia com os dados existentes no Sistema de Cadastro Positivo.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public class DivergenciaDadosException extends Exception {

    public DivergenciaDadosException(String message) {
        super(message);
    }
}
