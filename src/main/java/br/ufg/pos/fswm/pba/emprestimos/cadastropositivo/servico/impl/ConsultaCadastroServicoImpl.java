package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.impl;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.ConectorCadastroPositivo;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.ConsultaCadastroServico;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;

/**
 * Implementa interface {@link ConsultaCadastroServico}
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public class ConsultaCadastroServicoImpl implements ConsultaCadastroServico {

    private final ConectorCadastroPositivo conectorCadastroPositivo;

    /**
     * Construtor padr&atilde;o que recebe todas as depend&ecirc;ncias necess&aacute;rias para o funcionamento dessa classe;
     *
     * @param conectorCadastroPositivo {@link ConectorCadastroPositivo}
     */
    public ConsultaCadastroServicoImpl(ConectorCadastroPositivo conectorCadastroPositivo) {
        this.conectorCadastroPositivo = conectorCadastroPositivo;
    }

    @Override
    public Pessoa consultarCadastro(Pessoa pessoa) {
        conectorCadastroPositivo.consultarCadastro(pessoa.getCpf());
        return null;
    }
}
