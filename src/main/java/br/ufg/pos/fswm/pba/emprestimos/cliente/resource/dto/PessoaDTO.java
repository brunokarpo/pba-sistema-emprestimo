package br.ufg.pos.fswm.pba.emprestimos.cliente.resource.dto;

import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Pessoa;
import br.ufg.pos.fswm.pba.emprestimos.cliente.modelo.Sexo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO que representa os dados de uma Pessoa no sistema
 *
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public class PessoaDTO {
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String profissao;
    private BigDecimal salario;
    private String sexo;

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

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    /**
     * Classe interna respons&aacute;vel por realizar transforma&ccedil;&otilde;es na entidade {@link PessoaDTO}
     */
    public static final class PessoaDTOTransformer {

        private PessoaDTOTransformer() {
            // classe utilitaria. Nao instanciavel
        }

        /**
         * Realiza a transformação de uma entidade {@link PessoaDTO} em {@link Pessoa}
         *
         * @param pessoaDto {@link PessoaDTO}
         * @return {@link Pessoa}
         */
        public static Pessoa criarEntidade(PessoaDTO pessoaDto) {
            final Pessoa pessoa = new Pessoa();
            pessoa.setNome(pessoaDto.getNome());
            pessoa.setCpf(pessoaDto.getCpf());
            pessoa.setProfissao(pessoaDto.getProfissao());
            pessoa.setSalario(pessoaDto.getSalario());
            pessoa.setNascimento(pessoaDto.getNascimento());
            pessoa.setSexo(Sexo.fromNome(pessoaDto.getSexo()));
            return pessoa;
        }
    }
}
