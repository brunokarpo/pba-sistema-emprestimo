package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto;

import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 26/10/17.
 */
public class EmprestimosDisponiveisDTO {


    private List<EmprestimoDisponivel> emprestimos;

    public EmprestimosDisponiveisDTO() {
        emprestimos = new ArrayList<>();
    }

    public List<EmprestimoDisponivel> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<EmprestimoDisponivel> emprestimos) {
        this.emprestimos = emprestimos;
    }

    static class EmprestimoDisponivel {

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

    public static class Transformer {
        public static EmprestimosDisponiveisDTO criarDto(List<Emprestimo> emprestimos) {
            final EmprestimosDisponiveisDTO dto = new EmprestimosDisponiveisDTO();
            emprestimos.forEach(emprestimo -> {
                final EmprestimoDisponivel emprestimoDisponivel = new EmprestimoDisponivel();
                emprestimoDisponivel.setCodigo(emprestimo.getCodigo());
                emprestimoDisponivel.setTitulo(emprestimo.getTitulo());
                emprestimoDisponivel.setCredito(emprestimo.getCredito());
                emprestimoDisponivel.setJurosMes(emprestimo.getJurosMes());
                emprestimoDisponivel.setParcelas(emprestimo.getParcelas());
                emprestimoDisponivel.setPrestacoes(emprestimo.getPrestacoes());
                dto.getEmprestimos().add(emprestimoDisponivel);
            });
            return dto;
        }
    }
}
