package br.ufg.pos.fswm.pba.emprestimos.cliente.modelo;

import java.util.Arrays;

/**
 * @author Bruno Nogueira de Oliveira
 * @date 18/10/17.
 */
public enum Sexo {
    MASCULINO,
    FEMININO;

    public static Sexo fromNome(String sexoStr) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.name().equals(sexoStr.toUpperCase())) {
                return sexo;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' não reconhecido como Sexo válido", sexoStr));
    }
}
