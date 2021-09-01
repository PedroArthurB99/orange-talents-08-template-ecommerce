package br.com.orange.mercadolivre.categoria;

import java.util.Objects;

public class CategoriaDTO {

    private Long id;
    private String nome;
    private Long categoriaMaeId;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        if(Objects.nonNull(categoria.getMãe())) {
            this.categoriaMaeId = categoria.getMãe().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }
}
