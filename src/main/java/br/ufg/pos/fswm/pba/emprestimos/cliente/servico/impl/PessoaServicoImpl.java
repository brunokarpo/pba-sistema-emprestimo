package br.ufg.pos.fswm.pba.emprestimos.cliente.servico.impl;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.ConsultaCadastroServico;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.repositorio.PessoaRepositorio;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.PessoaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Implementa interface {@link PessoaServico}
 *
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@Service
@Primary
public class PessoaServicoImpl implements PessoaServico {

    private final PessoaRepositorio pessoaRepositorio;
    private final ConsultaCadastroServico consultaCadastroServico;

    /**
     * Construtor padr&atilde;o que recebe todas as depend&ecirc;ncias necess&aacute;rias para o funcionamento.
     *
     * @param pessoaRepositorio {@link PessoaRepositorio}
     * @param consultaCadastroServico {@link ConsultaCadastroServico}
     */
    @Autowired
    public PessoaServicoImpl(PessoaRepositorio pessoaRepositorio, ConsultaCadastroServico consultaCadastroServico) {
        this.pessoaRepositorio = pessoaRepositorio;
        this.consultaCadastroServico = consultaCadastroServico;
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        pessoa = pessoaRepositorio.save(pessoa);
        try {
            return consultaCadastroServico.consultarCadastro(pessoa);
        } catch (DivergenciaDadosException e) {
            // TODO tratar essa excecao. Testar
            e.printStackTrace();
            return null;
        }
    }
}
