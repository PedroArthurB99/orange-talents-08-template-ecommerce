package br.com.orange.mercadolivre.produto;

import br.com.orange.mercadolivre.categoria.CategoriaRepository;
import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.imagem.Imagem;
import br.com.orange.mercadolivre.imagem.ImagemForm;
import br.com.orange.mercadolivre.opiniao.Opiniao;
import br.com.orange.mercadolivre.opiniao.OpiniaoForm;
import br.com.orange.mercadolivre.pergunta.EnviadorDeEmails;
import br.com.orange.mercadolivre.pergunta.Pergunta;
import br.com.orange.mercadolivre.pergunta.PerguntaForm;
import br.com.orange.mercadolivre.usuario.Usuario;
import br.com.orange.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO cadastrar(@RequestBody @Valid ProdutoForm form, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = form.toModel(categoriaRepository, usuarioRepository, usuario);
        produto = this.repository.save(produto);
        return new ProdutoDTO(produto);
    }

    @PostMapping
    @RequestMapping("/{id}/imagens")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO adicionaImagem(@PathVariable("id") Long id, @Valid ImagemForm form, @AuthenticationPrincipal Usuario usuario) {
        List<Imagem> imagens = form.toModel(id, repository, usuario);
        Produto produto = repository.findById(id).get();
        produto.adicionarImagens(imagens);
        this.repository.save(produto);
        return new ProdutoDTO(produto);
    }

    @PostMapping
    @RequestMapping("/{id}/opinioes")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO adicionaOpiniao(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoForm form, @AuthenticationPrincipal Usuario usuario) {
        Opiniao opiniao = form.toModel(id, repository, usuario);
        Produto produto = repository.findById(id).get();
        produto.adicionarOpiniao(opiniao);
        this.repository.save(produto);
        return new ProdutoDTO(produto);
    }

    @PostMapping
    @RequestMapping("/{id}/perguntas")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO adicionaPerguntas(@PathVariable("id") Long id, @RequestBody @Valid PerguntaForm form, @AuthenticationPrincipal Usuario usuario) {
        Pergunta pergunta = form.toModel(id, repository, usuario);
        Produto produto = repository.findById(id).get();
        produto.adicionarPergunta(pergunta);
        this.repository.save(produto);
        EnviadorDeEmails.enviarEmail(produto.getUsuario(), usuario);
        return new ProdutoDTO(produto);
    }

    @GetMapping
    @RequestMapping("/{id}/detalhar")
    @ResponseStatus(HttpStatus.OK)
    public DetalharProdutoDTO detalhar(@PathVariable("id") Long id) {
        Produto produto = this.repository.findById(id).orElseThrow(() ->new RegraNegocioException(
                new ObjetoErroDTO("id-" + id, "Não existe um produto com esse id.")));
        return new DetalharProdutoDTO(produto);
    }
}
