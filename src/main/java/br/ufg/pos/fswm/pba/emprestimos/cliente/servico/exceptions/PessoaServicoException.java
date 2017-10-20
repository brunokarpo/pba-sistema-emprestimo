package br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions;

/**
 * Representa uma exce&ccedil;&atilde;o de servi&ccedil;o quando ocorre algum problema no servi&ccedil;o de Pessoa;
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public class PessoaServicoException extends Exception {

    public PessoaServicoException(String message) {
        super(message);
    }
}
