package br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 20/10/17.
 */
public class MenorDeIdadeException extends PessoaServicoException{

    private static final String MESSAGE_PROPERTY = "servico.pessoa.menor.idade";

    public MenorDeIdadeException(String message) {
        super(MESSAGE_PROPERTY, message);
    }
}
