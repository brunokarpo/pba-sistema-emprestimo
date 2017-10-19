package br.ufg.pos.fswm.pba.emprestimos.cadastropositivo.conector.dto;

import java.time.LocalDate;

/**
 * DTO que representa os dados retornado pelo Sistema de Cadastro Positivo com os dados da pessoa naquele sistema.
 *
 * @author Bruno Nogueira de Oliveira
 * @date 19/10/17.
 */
public class CadastroPessoaDTO {

    private String nome;
    private String cpf;
    private String sexo;
    private LocalDate nascimento;
    private Integer risco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getRisco() {
        return risco;
    }

    public void setRisco(Integer risco) {
        this.risco = risco;
    }
}
