package br.ufg.pos.fswm.pba.emprestimos.handles;

import br.ufg.pos.fswm.pba.emprestimos.cliente.servico.exceptions.PessoaServicoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<Erro> erros = criarListaDeErros(ex.getBindingResult());

        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({PessoaServicoException.class})
    public ResponseEntity<Object> handlePessoaServicoException(PessoaServicoException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage(ex.getMessageProperty(), null, LocaleContextHolder.getLocale());
        Erro erro = new Erro(HttpStatus.BAD_REQUEST.value(), mensagemUsuario, ex.getMessage());
        return handleExceptionInternal(ex, Arrays.asList(erro), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String mensagemUsuario = ex.getMessage();
        Erro erro = new Erro(HttpStatus.BAD_REQUEST.value(), mensagemUsuario, ex.getMessage());
        return handleExceptionInternal(ex, Arrays.asList(erro), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        final List<Erro> erros = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Erro(HttpStatus.BAD_REQUEST.value(), mensagemUsuario, mensagemDesenvolvedor));

        });

        return erros;
    }

    /**
     * Representa uma entidade de erro retornada pelo sistema
     */
    public static final class Erro {
        private final String mensagemUsuario;
        private final String mensagemDesenvolvedor;
        private final int statusHttp;

        public Erro(int statusHttp, String mensagemUsuario, String mensagemDesenvolvedor) {
            this.statusHttp = statusHttp;
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public int getStatusHttp() {
            return statusHttp;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
    }
}
