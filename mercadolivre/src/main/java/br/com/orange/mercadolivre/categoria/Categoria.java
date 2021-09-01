package br.com.orange.mercadolivre.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @ManyToOne
    private Categoria mae;

    @Deprecated
    public Categoria() {}

    public Categoria(@NotNull String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getMãe() {
        return mae;
    }

    public void setMãe(Categoria mae) {
        this.mae = mae;
    }
}
