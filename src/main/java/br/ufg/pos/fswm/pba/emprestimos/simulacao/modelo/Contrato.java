package br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo;

import br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.modelo.Risco;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Representa os detalhes de um contrato que gera um empr&eacute;stimo.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 29/10/17.
 */
@Entity
@Table(name = "contrato")
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String codigo;

    private String titulo;

    /*
     * Determina quanto do salário do cliente pode ser utilizado na mensalidade.
     */
    @Column(name = "perc_sal_comprometido")
    private BigDecimal percentualSalarioComprometido;

    /*
     * Determina quanto vai emprestar
     */
    @Column(name = "perc_sal_emprestimo")
    private BigDecimal percentualSalarioEmprestimo;

    @Column(name = "juros_mes")
    private BigDecimal jurosMes;

    @ElementCollection(targetClass = Risco.class, fetch = FetchType.EAGER)
    @JoinTable(name = "risco_contrato", joinColumns = {@JoinColumn(name = "id_contrato")},
                inverseJoinColumns = {@JoinColumn(name = "risco")})
    private List<Risco> riscosAceitos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPercentualSalarioComprometido() {
        return percentualSalarioComprometido;
    }

    public void setPercentualSalarioComprometido(BigDecimal percentualSalarioComprometido) {
        this.percentualSalarioComprometido = percentualSalarioComprometido;
    }

    public BigDecimal getPercentualSalarioEmprestimo() {
        return percentualSalarioEmprestimo;
    }

    public void setPercentualSalarioEmprestimo(BigDecimal percentualSalarioEmprestimo) {
        this.percentualSalarioEmprestimo = percentualSalarioEmprestimo;
    }

    public List<Risco> getRiscosAceitos() {
        return riscosAceitos;
    }

    public void setRiscosAceitos(List<Risco> riscosAceitos) {
        this.riscosAceitos = riscosAceitos;
    }

    public BigDecimal getJurosMes() {
        return jurosMes;
    }

    public void setJurosMes(BigDecimal jurosMes) {
        this.jurosMes = jurosMes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contrato contrato = (Contrato) o;

        return getId() != null ? getId().equals(contrato.getId()) : contrato.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
