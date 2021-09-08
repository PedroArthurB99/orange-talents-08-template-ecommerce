package br.com.orange.mercadolivre.compra;

import br.com.orange.mercadolivre.compra.apiexternasimulada.ApiExterna;
import br.com.orange.mercadolivre.compra.apiexternasimulada.FormSistemaNotaFiscal;
import br.com.orange.mercadolivre.compra.apiexternasimulada.FormSistemaRanking;
import br.com.orange.mercadolivre.compra.transacao.StatusTransacaoEnum;
import br.com.orange.mercadolivre.compra.transacao.Transacao;
import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.pergunta.EnviadorDeEmails;
import br.com.orange.mercadolivre.produto.Produto;
import br.com.orange.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive @NotNull
    private Integer quantidade;

    @Enumerated @NotNull
    private GatewayEnum gateway;

    private StatusCompraEnum status;

    private Double valorProdutoMomentoCompra;

    @ManyToOne @NotNull
    private Produto produto;

    @ManyToOne @NotNull
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private List<Transacao> transacoes = new ArrayList<>();

    @Deprecated
    public Compra() {}

    public Compra(@Positive @NotNull Integer quantidade, @NotNull GatewayEnum gateway,
                  @NotNull Produto produto, @NotNull Usuario usuario) {
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.produto = produto;
        this.usuario = usuario;
        this.status = StatusCompraEnum.INICIADA;
        this.valorProdutoMomentoCompra = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayEnum getGateway() {
        return gateway;
    }

    public StatusCompraEnum getStatus() {
        return status;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Double getValorProdutoMomentoCompra() {
        return valorProdutoMomentoCompra;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void adicionarTransacao(Transacao transacao, ApiExterna apiExterna) {
        if (this.transacoes.contains(transacao)) {
            throw new RegraNegocioException(new ObjetoErroDTO(
                "transacao", "Essa transação já aconteceu!"));
        }
        List<Transacao> transacoesComSucesso = this.transacoes.stream().filter(transacao::concluidaComSucesso).collect(Collectors.toList());
        if (!(transacoesComSucesso.size() < 2)) throw new RegraNegocioException(new ObjetoErroDTO(
                "transacao", "Você já fez o número de transações máximas para esta compra. Obrigado."
        ));

        processarComunicacaoSistemasExternos(transacao, apiExterna);

        this.transacoes.add(transacao);


    }

    private void processarComunicacaoSistemasExternos(Transacao transacao, ApiExterna apiExterna) {
        if (transacao.getStatusCompraEnum().equals(StatusTransacaoEnum.SUCESSO)) {
            apiExterna.comunicaSistemaNotaFiscal(new FormSistemaNotaFiscal(this.id, this.usuario.getId()));
            apiExterna.comunicaSistemaRanking(new FormSistemaRanking(this.id, this.produto.getUsuario().getId()));
            EnviadorDeEmails.enviarEmailCompraSucesso(this);
        }
        else {
            EnviadorDeEmails.enviarEmailCompraFalhou(this);
        }
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", gateway=" + gateway +
                ", status=" + status +
                ", valorProdutoMomentoCompra=" + valorProdutoMomentoCompra +
                ", produto=" + produto +
                ", usuario=" + usuario +
                ", transacoes=" + transacoes.size() +
                '}';
    }
}
