package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto;

import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;

import java.math.BigDecimal;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 30/10/17.
 */
public class EmprestimoDisponivel {

    private String codigo;
    private String titulo;
    private BigDecimal credito;
    private BigDecimal jurosMes;
    private BigDecimal parcelas;
    private Integer prestacoes;

    public EmprestimoDisponivel() {
    }

    /**
     * Construtor que recebe uma inst&acirc;ncia de emprestimo disponivel
     *
     * @param emprestimo
     */
    public EmprestimoDisponivel(Emprestimo emprestimo) {
        setCodigo(emprestimo.getCodigo());
        setTitulo(emprestimo.getTitulo());
        setCredito(emprestimo.getCredito());
        setJurosMes(emprestimo.getJurosMes());
        setParcelas(emprestimo.getParcelas());
        setPrestacoes(emprestimo.getPrestacoes());
    }

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
