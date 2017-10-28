package br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo;

import java.math.BigDecimal;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 26/10/17.
 */
public class Emprestimo {
    private String codigo;
    private String titulo;
    private BigDecimal credito;
    private BigDecimal jurosMes;
    private BigDecimal parcelas;
    private Integer prestacoes;

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setJurosMes(BigDecimal jurosMes) {
        this.jurosMes = jurosMes;
    }

    public BigDecimal getJurosMes() {
        return jurosMes;
    }

    public void setParcelas(BigDecimal parcelas) {
        this.parcelas = parcelas;
    }

    public BigDecimal getParcelas() {
        return parcelas;
    }

    public void setPrestacoes(Integer prestacoes) {
        this.prestacoes = prestacoes;
    }

    public Integer getPrestacoes() {
        return prestacoes;
    }
}
