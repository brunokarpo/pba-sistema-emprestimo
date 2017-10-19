package br.ufg.pos.fswm.pba.emprestimos.cliente.servico;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;

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
     */
    Pessoa salvar(Pessoa pessoa);
}
