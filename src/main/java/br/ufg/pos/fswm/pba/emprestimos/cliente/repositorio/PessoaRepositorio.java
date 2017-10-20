package br.ufg.pos.fswm.pba.emprestimos.cliente.repositorio;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface que representa um repositório de Pessoas no sistema.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Long>{

    /**
     * Busca uma pessoa no reposit&oacute;rio utilizando o CPF como par&acirc;metro de busca.
     *
     * @param cpf CPF do cliente
     * @return {@link Optional} contendo uma {@link Pessoa} caso exista algu&eacute;m com esse CPF.
     *          Optional vazio caso contr&aacute;rio
     */
    Optional<Pessoa> findByCpf(@Param("cpf") String cpf);
}
