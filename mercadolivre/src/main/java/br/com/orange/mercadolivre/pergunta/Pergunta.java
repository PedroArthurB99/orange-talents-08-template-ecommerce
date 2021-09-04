package br.com.orange.mercadolivre.pergunta;

import br.com.orange.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDateTime instanteCricacao = LocalDateTime.now();

    @ManyToOne @NotNull
    private Usuario usuario;

    @Deprecated
    public Pergunta() {}

    public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario) {
        this.titulo = titulo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCricacao() {
        return instanteCricacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
