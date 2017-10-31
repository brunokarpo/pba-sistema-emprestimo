package br.ufg.pos.fswm.pba.emprestimos.cliente.resource;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto.PessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.cliente.resource.evento.RecursoCriadoEvent;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.PessoaServico;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.CpfNaoEncontradoException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.PessoaServicoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Controlador do recurso de Pessoas na aplica&ccedil;&atilde;o
 *
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@RestController
@RequestMapping("/api/emprestimo/cliente")
public class PessoaResource {

    private final PessoaServico servico;

    private final ApplicationEventPublisher publisher;

    @Autowired
    public PessoaResource(PessoaServico servico, ApplicationEventPublisher publisher) {
        this.servico = servico;
        this.publisher = publisher;
    }

    /**
     * Realiza o cadastro de uma nova pessoa no sistema
     *
     * @param pessoaDto dto com os dados da pessoa para ser salvo.
     * @param response representa&ccedil;&atilde;o da resposta.
     * @return
     */
    @PostMapping
    public ResponseEntity<PessoaDTO> criarNovo(@RequestBody @Valid PessoaDTO pessoaDto, HttpServletResponse response) throws PessoaServicoException, DivergenciaDadosException {
        Pessoa pessoa = PessoaDTO.PessoaDTOTransformer.criarEntidade(pessoaDto);
        pessoa = servico.salvar(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getCpf()));

        return new ResponseEntity<>(PessoaDTO.PessoaDTOTransformer.criarDto(pessoa), HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<PessoaDTO> consultar(@PathVariable("cpf") String cpf) throws DivergenciaDadosException, CpfNaoEncontradoException {
        final Pessoa pessoa = servico.buscarPorCpf(cpf);
        return new ResponseEntity<>(PessoaDTO.PessoaDTOTransformer.criarDto(pessoa), HttpStatus.OK);
    }
}
