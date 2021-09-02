package br.com.orange.mercadolivre.caracteristica;

import javax.validation.constraints.NotBlank;

public class CaracteristicaForm {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public Caracteristica toModel() {
        return new Caracteristica(this.nome, this.descricao);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
