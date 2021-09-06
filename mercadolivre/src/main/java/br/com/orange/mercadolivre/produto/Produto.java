package br.com.orange.mercadolivre.produto;

import br.com.orange.mercadolivre.caracteristica.Caracteristica;
import br.com.orange.mercadolivre.categoria.Categoria;
import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.imagem.Imagem;
import br.com.orange.mercadolivre.opiniao.Opiniao;
import br.com.orange.mercadolivre.pergunta.Pergunta;
import br.com.orange.mercadolivre.usuario.Usuario;
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

    @NotNull
    @Positive
    private Double valor;

    @NotNull
    @Positive
    private Integer quantidadeDisponivel;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @ManyToOne
    @NotNull
    private Categoria categoria;

    @ManyToOne
    @NotNull
    private Usuario usuario;

    @Size(min = 3)
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Caracteristica> caracteristicas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> imagens;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opiniao> opinioes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pergunta> perguntas;

    @Deprecated
    public Produto() {}

    public Produto(@NotBlank String nome, @NotNull Double valor, @NotNull Integer quantidadeDisponivel,
                   @NotBlank String descricao, @NotNull Categoria categoria, @NotNull List<Caracteristica> caracteristicas,
                   @NotNull Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void adicionarImagens(List<Imagem> imagens) {
        imagens.forEach(imagem -> {
            if (!this.imagens.contains(imagem)) {
                this.imagens.add(imagem);
            }
        });
    }

    public void adicionarOpiniao(Opiniao opiniao) {
        this.opinioes.add(opiniao);
    }

    public void adicionarPergunta(Pergunta opiniao) {
        this.perguntas.add(opiniao);
    }

    public void abaterEstoque(Integer quantidade) {
        if (this.quantidadeDisponivel >= quantidade) {
            this.quantidadeDisponivel -= quantidade;
        }
        else {
            throw new RegraNegocioException(new ObjetoErroDTO(
                    "quantidade", "Não temos dispoível esta quantidade desse produto em estoque."));
        }
    }
}
