package br.com.orange.mercadolivre.produto;

import br.com.orange.mercadolivre.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO cadastrar(@RequestBody @Valid ProdutoForm form) {
        Produto produto = form.toModel(categoriaRepository);
        produto = this.repository.save(produto);
        return new ProdutoDTO(produto);
    }
}
