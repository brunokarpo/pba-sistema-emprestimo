package br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions;

/**
 * Exce&ccedil;&atilde;o lan&ccedil;ada quando n&atilde;o &eacute; encontrada uma pessoa na base com o CPF da pesquisa.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 29/10/17.
 */
public class CpfNaoEncontradoException extends PessoaServicoException {

    private static final String MESSAGE_PROPERTY = "servico.pessoa.cpf.inexistente";

    /**
     * Construtor padr&atilde;o da exce&ccedil;&atilde;o.
     *
     * @param message
     */
    public CpfNaoEncontradoException(String message) {
        super(MESSAGE_PROPERTY, message);
    }
}
