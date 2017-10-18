package br.ufg.pos.fswm.pba.emprestimos.cliente.resource;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto.PessoaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Controlador do recurso de Pessoas na aplica&ccedil;&atilde;o
 *
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@RestController
@RequestMapping("/api/emprestimo/cliente/")
public class PessoaResource {

    /**
     * Realiza o cadastro de uma nova pessoa no sistema
     *
     * @param pessoaDto dto com os dados da pessoa para ser salvo.
     * @param response representa&ccedil;&atilde;o da resposta.
     * @return
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<PessoaDTO> criarNovo(@RequestBody PessoaDTO pessoaDto, HttpServletResponse response) {
        Pessoa pessoa = PessoaDTO.PessoaDTOTransformer.criarEntidade(pessoaDto);

        return new ResponseEntity<>(new PessoaDTO(), HttpStatus.CREATED);
    }
}
