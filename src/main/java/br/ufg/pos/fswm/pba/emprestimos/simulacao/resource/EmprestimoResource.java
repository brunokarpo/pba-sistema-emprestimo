package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource;

import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto.EmprestimoDisponivel;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto.RequisicaoEmprestimoDTO;
import br.ufg.pos.fswm.pba.emprestimos.simulacao.servico.EmprestimoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 30/10/17.
 */

@RestController
@RequestMapping("/api/emprestimo")
public class EmprestimoResource {

    @Autowired
    private EmprestimoServico emprestimoServico;

    @PostMapping
    public ResponseEntity<EmprestimoDisponivel> solicitarEmprestimo(@RequestBody RequisicaoEmprestimoDTO dto) {
        final Emprestimo emprestimo = emprestimoServico.solicitar(dto);
        return new ResponseEntity<>(new EmprestimoDisponivel(emprestimo), HttpStatus.CREATED);
    }
}
