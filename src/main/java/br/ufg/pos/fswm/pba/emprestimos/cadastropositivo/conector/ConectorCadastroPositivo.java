package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;

/**
 * Interface respons&aacute;vel por disponibilizar os m&eacute;todos que faz as requisi&ccedil;&otilde;es HTTP
 * para o servi&ccedil;o de Cadastro Positivo.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public interface ConectorCadastroPositivo {

    CadastroPessoaDTO consultarCadastro(String cpf);
}
