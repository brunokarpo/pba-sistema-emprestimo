package br.ufg.pos.fswm.pba.emprestimos.cliente.repositorio;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa um repositório de Pessoas no sistema.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Long>{
}
