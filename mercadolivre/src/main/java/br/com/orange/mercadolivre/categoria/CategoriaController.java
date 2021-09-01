package br.com.orange.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestControllerAdvice
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO cadastrar(@RequestBody @Valid CategoriaForm form) {
        Categoria categoria = form.toModel(this.repository);
        categoria = repository.save(categoria);
        return new CategoriaDTO(categoria);
    }
}
