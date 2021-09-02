package br.com.orange.mercadolivre.caracteristica;

public class CaracteristicaDTO {

    private Long id;
    private String nome;
    private String descricao;

    public CaracteristicaDTO(Caracteristica caracteristica) {
        this.id = caracteristica.getId();
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
