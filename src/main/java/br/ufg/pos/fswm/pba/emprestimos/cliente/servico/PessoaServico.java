package br.ufg.pos.fswm.pba.emprestimos.cliente.servico;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.PessoaServicoException;

/**
 * Expõe os serviços específicos do recurso de {@link Pessoa} no sistema
 *
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public interface PessoaServico {

    /**
     * Realiza a persistência de uma nova pessoa no sistema
     *
     * @param pessoa Pessoa a ser salva;
     * @return pessoa com os dados salvos;
     * @throws PessoaServicoException caso ocorra alguma exce&ccedil;&atilde;o no servi&ccedil;o
     */
    Pessoa salvar(Pessoa pessoa) throws PessoaServicoException, DivergenciaDadosException;
}
