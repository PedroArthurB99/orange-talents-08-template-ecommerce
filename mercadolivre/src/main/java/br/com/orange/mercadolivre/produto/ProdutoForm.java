package br.com.orange.mercadolivre.produto;

import br.com.orange.mercadolivre.caracteristica.Caracteristica;
import br.com.orange.mercadolivre.caracteristica.CaracteristicaForm;
import br.com.orange.mercadolivre.categoria.Categoria;
import br.com.orange.mercadolivre.categoria.CategoriaRepository;
import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.usuario.Usuario;
import br.com.orange.mercadolivre.usuario.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoForm {

    @NotBlank
    private String nome;

    @NotNull @Positive
    private Double valor;

    @NotNull @Positive
    private Integer quantidadeDisponivel;

    @NotBlank @Length(max = 1000)
    private String descricao;

    @NotNull
    private Long categoriaId;

    @Size(min = 3) @NotNull
    private List<CaracteristicaForm> caracteristicas;

    public Produto toModel(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository, Usuario usuario) {
        Optional<Categoria> categoria = categoriaRepository.findById(this.categoriaId);
        if(!categoria.isPresent()) {
            throw new RegraNegocioException(new ObjetoErroDTO("categoriaId", "Não existe uma categoria com esse id"));
        }
        List<Caracteristica> caracteristicaEntity = new ArrayList<>();
        for (CaracteristicaForm caracteristica : this.caracteristicas) {
            caracteristicaEntity.add(caracteristica.toModel());
        }
        return new Produto(this.nome, this.valor, this.quantidadeDisponivel, this.descricao,
                categoria.get(), caracteristicaEntity, usuario);
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public List<CaracteristicaForm> getCaracteristicas() {
        return caracteristicas;
    }
}
