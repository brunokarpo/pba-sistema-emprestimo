package br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;

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

    /**
     * Construtor padr&atilde;o da classe;
     */
    public Emprestimo() {
    }

    /**
     * Cria um empr&eacute;stimo atrav&eacute;s da Pessoa solicitando o empr&eacute;stimo e o contrato dispon&iacute;vel.
     *
     * @param pessoa {@link Pessoa}
     * @param contrato {@link Contrato}
     */
    public Emprestimo(Pessoa pessoa, Contrato contrato) {
        setCodigo(contrato.getCodigo());
        setTitulo(contrato.getTitulo());
        setJurosMes(contrato.getJurosMes());

        final BigDecimal creditoDisponibilizado = pessoa.getSalario().multiply(contrato.getPercentualSalarioEmprestimo());
        setCredito(creditoDisponibilizado);

        // Valor mensal comprometido
        final BigDecimal valorMensalComprometido = pessoa.getSalario().multiply(contrato.getPercentualSalarioComprometido());

        final double prestacoesDouble = Math.ceil( creditoDisponibilizado.doubleValue() / valorMensalComprometido.doubleValue() );
        final Integer prestacoes = (int) prestacoesDouble;
        setPrestacoes(prestacoes);

        final BigDecimal jurosCalculado = valorMensalComprometido.multiply(contrato.getJurosMes());
        setParcelas(jurosCalculado.add(valorMensalComprometido));
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
