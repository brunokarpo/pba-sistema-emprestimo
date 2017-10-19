package br.ufg.pos.fswm.pba.emprestimos.cliente.resource.evento;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public class RecursoCriadoEvent extends ApplicationEvent {

    private final String cpf;
    private final HttpServletResponse response;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, String cpf) {
        super(source);
        this.response = response;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
