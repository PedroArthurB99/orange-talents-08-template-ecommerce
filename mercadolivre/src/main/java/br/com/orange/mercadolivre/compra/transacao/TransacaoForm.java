package br.com.orange.mercadolivre.compra.transacao;

import br.com.orange.mercadolivre.compra.Compra;
import br.com.orange.mercadolivre.compra.CompraRepository;
import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class TransacaoForm {

    @NotBlank
    private String codigoGateway;

    @NotBlank
    private String statusTransacaoEnum;

    public Transacao toModel(CompraRepository compraRepository, Long id) {
        Optional<Compra> compra = compraRepository.findById(id);
        if (compra.isEmpty()) throw new RegraNegocioException(new ObjetoErroDTO("compraId", "Não existe uma compra com esse id."));
         return new Transacao(this.codigoGateway, compra.get(), StatusTransacaoEnum.montarEnum(this.statusTransacaoEnum.toUpperCase()));
    }

    public String getCodigoGateway() {
        return codigoGateway;
    }

    public String getStatusTransacaoEnum() {
        return statusTransacaoEnum;
    }

}
