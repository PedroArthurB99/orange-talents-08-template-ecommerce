package br.com.orange.mercadolivre.categoria;

import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.validator.CampoUnicoConstraint;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CategoriaForm {

    @NotNull
    @CampoUnicoConstraint(modelClass = Categoria.class, campo = "nome")
    private String nome;

    private Long categoriaMaeId;

    public Categoria toModel(CategoriaRepository repository) {
        Categoria categoria = new Categoria(this.nome);
        if (this.categoriaMaeId != null) {
            Optional<Categoria> categoriaMae = repository.findById(categoriaMaeId);
            if (!categoriaMae.isPresent()) {
                throw new RegraNegocioException(new ObjetoErroDTO("categoriaMaeId",
                        "Não existe uma categoria com esse id."));
            }
            categoria.setMãe(categoriaMae.get());
        }
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }
}
