package br.com.orange.mercadolivre.opiniao;

import br.com.orange.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1) @Max(5) @NotNull
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank @Length(max = 500)
    private String descricao;

    @ManyToOne @NotNull
    private Usuario usuario;

    @Deprecated
    public Opiniao(){}

    public Opiniao(@NotNull Integer nota, @NotBlank String titulo, @NotBlank String descricao, @NotNull Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
