package br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public class PessoaDTO {
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String profissao;
    private BigDecimal salario;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
