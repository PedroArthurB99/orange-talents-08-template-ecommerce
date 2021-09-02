package br.com.orange.mercadolivre.produto;

import br.com.orange.mercadolivre.caracteristica.Caracteristica;
import br.com.orange.mercadolivre.categoria.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull @Positive
    private Double valor;

    @NotNull @Positive
    private Integer quantidadeDisponivel;

    @NotBlank @Length(max = 1000)
    private String descricao;

    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @ManyToOne @NotNull
    private Categoria categoria;

    @Size(min = 3) @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Caracteristica> caracteristicas;

    @Deprecated
    public Produto() {}

    public Produto(@NotBlank String nome, @NotNull Double valor, @NotNull Integer quantidadeDisponivel,
                   @NotBlank String descricao, @NotNull Categoria categoria, @NotNull List<Caracteristica> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }
}
