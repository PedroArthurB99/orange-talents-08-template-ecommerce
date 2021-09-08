package br.com.orange.mercadolivre.compra.apiexternasimulada;

import javax.validation.constraints.NotNull;

public class FormSistemaRanking {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public FormSistemaRanking(@NotNull Long idCompra, @NotNull Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    @Override
    public String toString() {
        return "FormSistemaRanking{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
