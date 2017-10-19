package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.impl;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.ConectorCadastroPositivo;
import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto.CadastroPessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Implementa a interface {@link ConectorCadastroPositivo}
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
@Service
@Primary
public class ConectorCadastroPositivoImpl implements ConectorCadastroPositivo {

    private final RestTemplate restTemplate;

    @Value("${url.consulta-cadastro}")
    private String urlConsultaCadastro;

    @Autowired
    public ConectorCadastroPositivoImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CadastroPessoaDTO consultarCadastro(String cpf) {
        return restTemplate.getForObject(urlConsultaCadastro + cpf, CadastroPessoaDTO.class);
    }
}
