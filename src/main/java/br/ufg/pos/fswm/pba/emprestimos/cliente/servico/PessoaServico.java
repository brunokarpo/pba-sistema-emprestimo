package br.ufg.pos.fswm.pba.emprestimos.cliente.servico;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
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

    /**
     * Realiza a busca de uma pessoa na aplica&ccedil;&atilde;o utilizando o CPF como par&acirc;metro de busca.
     *
     * <p>
     *     A pesquisa j&aacute; realiza uma busca no sistema de cadastro positivo e j&aacute;
     *     preenche o n&iacute;vel de risco da pessoa.
     * </p>
     *
     * @param cpf CPF da pessoa a ser pesquisada
     * @return {@link Pessoa}
     */
    Pessoa buscarPorCpf(String cpf) throws CpfNaoEncontradoException, DivergenciaDadosException;
}
