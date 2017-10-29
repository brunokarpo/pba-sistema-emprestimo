package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto.EmprestimosDisponiveisDTO;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.SimulacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Exp&otilde;e os end-points para realizar simula&ccedil;&atilde;o de empr&eacute;stimos
 *
 * @author Bruno Nogueira de Oliveira
 * @date 26/10/17.
 */

@RestController
@RequestMapping("/api/emprestimo/simulacao/")
public class SimulacaoResource {

    @Autowired
    private SimulacaoServico simulacaoServico;

    @GetMapping("/{cpf}")
    public ResponseEntity<EmprestimosDisponiveisDTO> simularEmprestimo(@PathVariable("cpf") String cpf) throws CpfNaoEncontradoException, DivergenciaDadosException {
        final List<Emprestimo> emprestimos = simulacaoServico.simularEmprestimo(cpf);
        final EmprestimosDisponiveisDTO dto = EmprestimosDisponiveisDTO.Transformer.criarDto(emprestimos);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
