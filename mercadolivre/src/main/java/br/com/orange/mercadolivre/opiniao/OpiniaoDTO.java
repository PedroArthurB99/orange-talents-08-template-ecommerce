package br.com.orange.mercadolivre.opiniao;

public class OpiniaoDTO {

    public Long id;
    private Integer nota;
    private String titulo;
    private String descricao;
    private String usuario;

    public OpiniaoDTO(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usuario = opiniao.getUsuario().getLogin();
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

    public String getUsuario() {
        return usuario;
    }
}
