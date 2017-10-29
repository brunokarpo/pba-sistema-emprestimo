package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;

import java.util.List;

/**
 * Serviço que realiza a simulação de emprestimos na aplicação.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 28/10/17.
 */
public interface SimulacaoServico {

    /**
     * Realiza a simula&ccedil;&atilde;o de poss&iacute;veis empr&eacute;stimos que podem ser solicitados pelo
     * usu&aacute;rio utiizando o CPF do cliente como par&acirc;metro de busca.
     *
     * @param cpf CPF da pessoa que deseja a simula&ccedil;&atilde;o do empr&eacute;stimo
     * @return A lista de poss&iacute;veis empr&eacute;stimos que podem ser realizados pela pessoa.
     */
    List<Emprestimo> simularEmprestimo(String cpf) throws CpfNaoEncontradoException, DivergenciaDadosException;
}
