package br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 20/10/17.
 */
public class CpfUnicidadeException extends PessoaServicoException {

    private static final String MESSAGE_PROPERTY = "servico.pessoa.unicidade.cpf";

    public CpfUnicidadeException(String message) {
        super(MESSAGE_PROPERTY, message);
    }
}
