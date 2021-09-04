package br.com.orange.mercadolivre.pergunta;

import java.time.LocalDateTime;

public class PerguntaDTO {

    private Long id;
    private String usuario;
    private String titulo;
    private LocalDateTime instanteCriacao;

    public PerguntaDTO(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.usuario = pergunta.getUsuario().getLogin();
        this.titulo = pergunta.getTitulo();
        this.instanteCriacao = pergunta.getInstanteCricacao();
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }
}
