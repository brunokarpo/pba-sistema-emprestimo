package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;

/**
 * Interface que exp&otilde;e os servi&ccedil;os de consulta de Cadastro.
 *
 * A implementa&ccedil;&atilde;o dessa classe &eacute; respons&aacute;vel por conectar com o servi&ccedil;o de
 * consulta de cadastro positivo e preencher o n&iacute;vel de risco desse cliente no sistema.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public interface ConsultaCadastroServico {

    /**
     * Recebe uma pessoa e realiza a consulta de Cadastro Positivo da pessoa no sistema de consulta do Cadastro.
     *
     * Prenche o nível de risco na pessoa recebida por parâmetro.
     *
     * @param pessoa {@link Pessoa} que deve ter o cadastro verificado junto ao sistema de cadastro positivo.
     * @return pessoa recebida por par&acirc;metro com o n&iacute;vel de risco preenchido.
     */
    Pessoa consultarCadastro(Pessoa pessoa) throws DivergenciaDadosException;
}
