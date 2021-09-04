package br.com.orange.mercadolivre.pergunta;

import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.produto.Produto;
import br.com.orange.mercadolivre.produto.ProdutoRepository;
import br.com.orange.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class PerguntaForm {

    @NotBlank
    private String titulo;

    public Pergunta toModel(Long id, ProdutoRepository repository, Usuario usuario) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isEmpty()) throw new RegraNegocioException(
                new ObjetoErroDTO("id-" + id, "Não existe um produto com esse id."));
        return new Pergunta(this.titulo, usuario);
    }

    public String getTitulo() {
        return titulo;
    }
}
