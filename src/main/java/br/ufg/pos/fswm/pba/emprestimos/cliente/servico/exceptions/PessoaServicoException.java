package br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions;

/**
 * Representa uma exce&ccedil;&atilde;o de servi&ccedil;o quando ocorre algum problema no servi&ccedil;o de Pessoa;
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public abstract class PessoaServicoException extends Exception {

    private final String messageProperty;

    public PessoaServicoException(String messageProperty, String message) {
        super(message);
        this.messageProperty = messageProperty;
    }

    public String getMessageProperty() {
        return messageProperty;
    }
}
