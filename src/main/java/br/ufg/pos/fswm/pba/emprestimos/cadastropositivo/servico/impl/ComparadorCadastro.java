package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.impl;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Realiza a valida&ccedil;&atilde;o dos dados cadastrais entre os dados informados pelo cliente e os dados do sistema
 * de Cadastro Positivo.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
class ComparadorCadastro {

    private static final Logger LOG = LoggerFactory.getLogger(ComparadorCadastro.class);

    private final Pessoa pessoa;
    private final CadastroPessoaDTO dto;

    /**
     * Construtor que recebe a {@link Pessoa} e o {@link CadastroPessoaDTO} para comparação
     * @param pessoa {@link Pessoa}
     * @param dto {@link CadastroPessoaDTO}
     */
    ComparadorCadastro(final Pessoa pessoa, final CadastroPessoaDTO dto) {
        this.pessoa = pessoa;
        this.dto = dto;
    }

    void realizarComparacao() throws DivergenciaDadosException {
        final String templateMensagem = "O '%s' informado pelo cliente diverge do Sistema de Cadastro Positivo";
        String message = null;

        if (!StringUtils.equalsIgnoreCase(pessoa.getNome(), dto.getNome())) {
            gerarMenssagemELancarException(templateMensagem, "Nome");
        }

        if (pessoa.getNascimento().compareTo(dto.getNascimento()) != 0) {
            gerarMenssagemELancarException(templateMensagem, "Nascimento");
        }

        if (!StringUtils.equalsIgnoreCase(pessoa.getSexo().name(), dto.getSexo())) {
            gerarMenssagemELancarException(templateMensagem, "Sexo");
        }
    }

    private void gerarMenssagemELancarException(String templateMensagem, String nomeCampo) throws DivergenciaDadosException {
        String message;
        message = String.format(templateMensagem, nomeCampo);
        LOG.warn(message);
        throw new DivergenciaDadosException(message);
    }
}
