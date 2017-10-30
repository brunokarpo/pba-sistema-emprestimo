package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.impl;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto.RequisicaoEmprestimoDTO;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.EmprestimoServico;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.SimulacaoServico;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions.EmprestimoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions.EmprestimoNaoDisponivelException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions.PessoaNegativadaException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementa a interface {@link EmprestimoServico}
 *
 * @author Bruno Nogueira de Oliveira
 * @date 30/10/17.
 */
@Service
@Primary
public class EmprestimoServicoImpl implements EmprestimoServico {

    private static final Logger LOG = LoggerFactory.getLogger(EmprestimoServicoImpl.class);

    private final SimulacaoServico simulacaoServico;

    /**
     * Construtor padr&atilde;o que recebe as depend&ecirc;ncias necess&aacute;rias para o funcionamento dessa classe.
     *
     * @param simulacaoServico {@link SimulacaoServico}
     */
    @Autowired
    public EmprestimoServicoImpl(SimulacaoServico simulacaoServico) {
        this.simulacaoServico = simulacaoServico;
    }

    @Override
    public Emprestimo solicitar(RequisicaoEmprestimoDTO dto) throws EmprestimoNaoDisponivelException, PessoaNegativadaException, CpfNaoEncontradoException, EmprestimoException {
        try {
            final List<Emprestimo> emprestimos = simulacaoServico.simularEmprestimo(dto.getCpf());

            Optional<Emprestimo> optional = emprestimos.stream()
                    .filter(emprestimo -> StringUtils.equalsIgnoreCase(emprestimo.getCodigo(), dto.getCodigoCredito()))
                    .findFirst();

            return optional.orElseThrow(() -> new EmprestimoNaoDisponivelException("Empréstimo com código '" + dto.getCodigoCredito() +"' não disponível para cliente com CPF '" + dto.getCpf() + "'"));
        } catch (DivergenciaDadosException e) {
            LOG.error("Ocorreu erro na hora de consultar cadastro do cliente.", e);
            throw new EmprestimoException("Problema no cadastro do cliente");
        }

    }
}
