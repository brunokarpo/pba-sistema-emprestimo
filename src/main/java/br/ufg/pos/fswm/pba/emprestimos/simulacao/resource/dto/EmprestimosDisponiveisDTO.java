package br.ufg.pos.fswm.pba.emprestimos.simulacao.resource.dto;

import br.ufg.pos.fswm.pba.emprestimos.simulacao.modelo.Emprestimo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 26/10/17.
 */
public class EmprestimosDisponiveisDTO {


    @JsonProperty("emprestimos-disponiveis")
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

    public static class Transformer {
        public static EmprestimosDisponiveisDTO criarDto(List<Emprestimo> emprestimos) {
            final EmprestimosDisponiveisDTO dto = new EmprestimosDisponiveisDTO();
            emprestimos.forEach(emprestimo ->
                dto.getEmprestimos().add(new EmprestimoDisponivel(emprestimo))
            );
            return dto;
        }
    }
}
