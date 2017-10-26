package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource;

import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.PessoaServico;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto.EmprestimosDisponviveisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 26/10/17.
 */

@RestController
@RequestMapping("/api/emprestimo/simulacao/")
public class SimulacaoResource {

    @Autowired
    private PessoaServico pessoaServico;

    @GetMapping("/{cpf}")
    public ResponseEntity<EmprestimosDisponviveisDTO> simularEmprestimo(@PathVariable("cpf") String cpf) {
        final List<Emprestimo> emprestimos = pessoaServico.encontrarPeloCpf(cpf);
        final List<EmprestimosDisponviveisDTO> dto = new ArrayList<>();
        emprestimos.forEach(emprestimo -> dto.add(EmprestimosDisponviveisDTO.Transformer.criarDto(emprestimo)));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
