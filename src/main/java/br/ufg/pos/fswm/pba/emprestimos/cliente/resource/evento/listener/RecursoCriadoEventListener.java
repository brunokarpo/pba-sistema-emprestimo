package br.ufg.pos.fswm.pba.emprestimos.cliente.resource.evento.listener;

import br.ufg.pos.fswm.pba.emprestimos.cliente.resource.evento.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
@Component
public class RecursoCriadoEventListener implements ApplicationListener<RecursoCriadoEvent> {

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
        HttpServletResponse response = recursoCriadoEvent.getResponse();
        String cpf = recursoCriadoEvent.getCpf();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cpf}")
                .buildAndExpand(cpf).toUri();

        response.setHeader("Location", uri.toASCIIString());
    }
}
