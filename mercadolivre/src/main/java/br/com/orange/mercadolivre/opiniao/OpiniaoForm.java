package br.com.orange.mercadolivre.opiniao;

import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.produto.Produto;
import br.com.orange.mercadolivre.produto.ProdutoRepository;
import br.com.orange.mercadolivre.usuario.Usuario;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class OpiniaoForm {

    @NotNull @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    public Opiniao toModel(Long id, ProdutoRepository repository, Usuario usuario) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isEmpty()) throw new RegraNegocioException(
                new ObjetoErroDTO("id-" + id, "Não existe um produto com esse id."));
        return new Opiniao(this.nota, this.titulo, this.descricao, usuario);
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
}
