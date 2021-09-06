package br.com.orange.mercadolivre.compra;

import br.com.orange.mercadolivre.pergunta.EnviadorDeEmails;
import br.com.orange.mercadolivre.produto.Produto;
import br.com.orange.mercadolivre.produto.ProdutoRepository;
import br.com.orange.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.FOUND)
    public String cadastrarCompra(@RequestBody @Valid CompraForm form, @AuthenticationPrincipal Usuario usuario,
                                  UriComponentsBuilder uriCompBuilder) {
        Compra compra = form.toModel(this.produtoRepository, usuario);
        Produto produto = compra.getProduto();
        produto.abaterEstoque(compra.getQuantidade());
        repository.save(compra);
        EnviadorDeEmails.enviarEmailIndicativoCompra(compra.getProduto().getUsuario(), usuario, compra);
        String urlRetorno = "";
        return construirUrlRetorno(uriCompBuilder, compra);
    }

    private String construirUrlRetorno(UriComponentsBuilder uriCompBuilder, Compra compra) {
        String urlRetorno;
        if (compra.getGateway()==GatewayEnum.PAYPAL) {
            urlRetorno = "paypal.com?buyerId=/" + compra.getId() + "&redirectUrl= " +
                    uriCompBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();
        }
        else {
            urlRetorno = "pagseguro.com?returnId=/" + compra.getId() + "&redirectUrl= " +
                    uriCompBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toString();
        }
        return urlRetorno;
    }
}
