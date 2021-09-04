package br.com.orange.mercadolivre.imagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String endereco;

    @Deprecated
    public Imagem() {}

    public Imagem(@NotBlank String endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }
}
