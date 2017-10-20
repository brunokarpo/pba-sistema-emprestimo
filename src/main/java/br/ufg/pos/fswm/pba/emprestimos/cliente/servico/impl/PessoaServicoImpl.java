package br.ufg.pos.fswm.pba.emprestimos.cliente.servico.impl;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.ConsultaCadastroServico;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.repositorio.PessoaRepositorio;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.PessoaServico;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfUnicidadeException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.MenorDeIdadeException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.PessoaServicoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Implementa interface {@link PessoaServico}
 *
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@Service
@Primary
public class PessoaServicoImpl implements PessoaServico {

    private static final Logger LOG = LoggerFactory.getLogger(PessoaServicoImpl.class);

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
    public Pessoa salvar(Pessoa pessoa) throws PessoaServicoException {

        if (LocalDate.now().compareTo(pessoa.getNascimento()) < 18) {
            LOG.warn("Pessoa menor de idade. Não vou salvar.");
            throw new MenorDeIdadeException("Não pode salvar usuário menor de idade");
        }

        Optional<Pessoa> optional = pessoaRepositorio.findByCpf(pessoa.getCpf());
        if (optional.isPresent()) {
            LOG.warn("Pessoa com mesmo CPF de outra já existente.");
            throw new CpfUnicidadeException("Não pode salvar pessoa com mesmo CPF de outra");
        }

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
