package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions;

import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.PessoaServicoException;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 30/10/17.
 */
public class EmprestimoNaoDisponivelException extends PessoaServicoException{

    private static final String MESSAGE_PROPERTY = "";

    public EmprestimoNaoDisponivelException(String message) {
        super(MESSAGE_PROPERTY, message);
    }
}
