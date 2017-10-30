package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 30/10/17.
 */
public class RequisicaoEmprestimoDTO {
    private String cpf;
    private String codigoCredito;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCodigoCredito(String codigoCredito) {
        this.codigoCredito = codigoCredito;
    }

    public String getCodigoCredito() {
        return codigoCredito;
    }
}
