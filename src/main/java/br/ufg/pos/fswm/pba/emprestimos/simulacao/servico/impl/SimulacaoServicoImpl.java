package br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.impl;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo.Risco;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.PessoaServico;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Contrato;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.repositorio.ContratoRepositorio;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.SimulacaoServico;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.exceptions.PessoaNegativadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa interface {@link SimulacaoServico}
 *
 * @author Bruno Nogueira de Oliveira
 * @date 28/10/17.
 */
@Service
@Primary
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
    public List<Emprestimo> simularEmprestimo(String cpf) throws CpfNaoEncontradoException, DivergenciaDadosException, PessoaNegativadaException {
        Pessoa pessoa = pessoaServico.buscarPorCpf(cpf);

        if (pessoa.getRisco().equals(Risco.NEGATIVADO)) {
            throw new PessoaNegativadaException("");
        }

        List<Contrato> contratos = contratoRepositorio.findByRiscosAceitos(pessoa.getRisco());

        List<Emprestimo> emprestimos = new ArrayList<>();

        contratos.forEach(contrato -> emprestimos.add(new Emprestimo(pessoa, contrato)));

        return emprestimos;
    }
}
