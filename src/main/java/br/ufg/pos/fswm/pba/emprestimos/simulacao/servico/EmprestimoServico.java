package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico;

import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto.RequisicaoEmprestimoDTO; /**
 * Servi&ccedil;o respons&aacute;vel por responder as solicita&ccedil;&otilde;es de empr&eacute;stimos da
 * aplica&ccedil;&atilde;o.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 30/10/17.
 */
public interface EmprestimoServico {

    /**
     * Dado uma requisi&ccedil;&atilde;o de empr&eacute;stimo, deve verificar se empr&eacute;stimo pode ser concedido
     * e devolve o empr&eacute;stimo realizado.
     * @param dto
     * @return
     */
    Emprestimo solicitar(RequisicaoEmprestimoDTO dto);
}
