package br.ufg.pos.fswm.pba.emprestimos.simulacao.repositorio;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo.Risco;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 29/10/17.
 */
@Repository
public interface ContratoRepositorio extends JpaRepository<Contrato, Long> {

    /**
     * Retorna um contrato utilizando o código como parâmetro de busca;
     *
     * @param codigo
     * @return
     */
    Optional<Contrato> findByCodigo(String codigo);

    /**
     * Retorna os contratos na aplica&ccedil;&atilde;o que aceitam o risco recebido por par&acirc;metro.
     *
     * @param risco
     * @return
     */
    List<Contrato> findByRiscosAceitos(Risco risco);
}
