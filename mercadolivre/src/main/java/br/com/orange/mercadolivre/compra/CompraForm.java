package br.com.orange.mercadolivre.compra;

import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.produto.ProdutoRepository;
import br.com.orange.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {

    @NotNull
    private Long produtoId;

    @Positive @NotNull
    private Integer quantidade;

    @NotNull
    private GatewayEnum gateway;

    public Compra toModel(ProdutoRepository produtoRepository, Usuario usuario) {
        if (!produtoRepository.existsById(produtoId)) throw new RegraNegocioException(
                new ObjetoErroDTO("produtoId", "Não existe um produto com esse id")
        );
        return new Compra(this.quantidade, this.gateway, produtoRepository.findById(produtoId).get(), usuario);
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayEnum getGateway() {
        return gateway;
    }
}
