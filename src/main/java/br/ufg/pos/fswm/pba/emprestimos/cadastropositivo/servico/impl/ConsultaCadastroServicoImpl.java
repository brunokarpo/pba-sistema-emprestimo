package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.impl;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.ConectorCadastroPositivo;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo.Risco;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.ConsultaCadastroServico;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.servico.exceptions.DivergenciaDadosException;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Implementa interface {@link ConsultaCadastroServico}
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
@Service
@Primary
public class ConsultaCadastroServicoImpl implements ConsultaCadastroServico {

    private static final Logger LOG = LoggerFactory.getLogger(ConsultaCadastroServicoImpl.class);

    private final ConectorCadastroPositivo conectorCadastroPositivo;

    /**
     * Construtor padr&atilde;o que recebe todas as depend&ecirc;ncias necess&aacute;rias para o funcionamento dessa classe;
     *
     * @param conectorCadastroPositivo {@link ConectorCadastroPositivo}
     */
    @Autowired
    public ConsultaCadastroServicoImpl(ConectorCadastroPositivo conectorCadastroPositivo) {
        this.conectorCadastroPositivo = conectorCadastroPositivo;
    }

    @Override
    public Pessoa consultarCadastro(Pessoa pessoa) throws DivergenciaDadosException {
        Risco risco = Risco.ALTO;

        try {
            CadastroPessoaDTO dto = conectorCadastroPositivo.consultarCadastro(pessoa.getCpf());

            verificarCadastroCliente(pessoa, dto);

            risco = Risco.calculaRisco(dto.getRisco());
        } catch (RestClientException e) {
            LOG.error("Ocorreu erro ao tentar conectar no Sistema de Cadastro Positivo. Retornando risco ALTO para pessoa: "+ e.getMessage(), e);
        }
        pessoa.setRisco(risco);

        return pessoa;
    }

    private void verificarCadastroCliente(Pessoa pessoa, CadastroPessoaDTO dto) throws DivergenciaDadosException {
        ComparadorCadastro comparadorCadastro = new ComparadorCadastro(pessoa, dto);
        comparadorCadastro.realizarComparacao();
    }
}
