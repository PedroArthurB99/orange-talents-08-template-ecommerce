package br.com.orange.mercadolivre.compra.transacao;

import br.com.orange.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigoGateway;

    @ManyToOne @NotNull
    private Compra compra;

    @Enumerated @NotNull
    private StatusTransacaoEnum statusCompraEnum;

    @NotNull
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Transacao() {}

    public Transacao(String codigoGateway, Compra compra, StatusTransacaoEnum statusCompraEnum) {
        this.codigoGateway = codigoGateway;
        this.compra = compra;
        this.statusCompraEnum = statusCompraEnum;
        this.instanteCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getCodigoGateway() {
        return codigoGateway;
    }

    public Compra getCompra() {
        return compra;
    }

    public StatusTransacaoEnum getStatusCompraEnum() {
        return statusCompraEnum;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public boolean concluidaComSucesso(Transacao transacao) {
        return this.statusCompraEnum.equals(StatusTransacaoEnum.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return getCodigoGateway().equals(transacao.getCodigoGateway());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoGateway());
    }
}
