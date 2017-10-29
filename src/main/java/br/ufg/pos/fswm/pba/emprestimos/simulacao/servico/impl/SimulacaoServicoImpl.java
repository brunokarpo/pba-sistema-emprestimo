package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.impl;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.PessoaServico;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Contrato;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.repositorio.ContratoRepositorio;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.SimulacaoServico;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa interface {@link SimulacaoServico}
 *
 * @author Bruno Nogueira de Oliveira
 * @date 28/10/17.
 */
public class SimulacaoServicoImpl implements SimulacaoServico {

    private final PessoaServico pessoaServico;
    private final ContratoRepositorio contratoRepositorio;

    /**
     * Construtor padr&atilde;o que recebe as depend&ecirc;ncias necess&aacute;rias para funcionamento da classe.
     *
     * @param pessoaServico {@link PessoaServico}
     * @param contratoRepositorio {@link ContratoRepositorio}
     */
    @Autowired
    public SimulacaoServicoImpl(PessoaServico pessoaServico, ContratoRepositorio contratoRepositorio) {
        this.pessoaServico = pessoaServico;
        this.contratoRepositorio = contratoRepositorio;
    }

    @Override
    public List<Emprestimo> simularEmprestimo(String cpf) throws CpfNaoEncontradoException {
        Pessoa pessoa = pessoaServico.buscarPorCpf(cpf);

        List<Contrato> contratos = contratoRepositorio.findByRiscosAceitos(pessoa.getRisco());

        List<Emprestimo> emprestimos = new ArrayList<>();

        contratos.forEach(contrato -> emprestimos.add(new Emprestimo(pessoa, contrato)));

        return emprestimos;
    }
}
