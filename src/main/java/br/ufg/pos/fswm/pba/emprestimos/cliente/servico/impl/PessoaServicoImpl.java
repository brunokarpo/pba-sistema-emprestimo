package br.ufg.pos.fswm.pba.emprestimos.cliente.servico.impl;

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

    @Autowired
    public PessoaServicoImpl(PessoaRepositorio pessoaRepositorio) {
        this.pessoaRepositorio = pessoaRepositorio;
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepositorio.save(pessoa);
    }
}
