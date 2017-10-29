package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions;

import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.PessoaServicoException;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 29/10/17.
 */
public class PessoaNegativadaException extends PessoaServicoException {

    private static final String MESSAGE_PROPERTY = "servico.simulacao.pessoa.negativada";

    public PessoaNegativadaException(String message) {
        super(MESSAGE_PROPERTY, message);
    }
}
