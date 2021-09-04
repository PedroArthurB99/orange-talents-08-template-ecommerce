package br.com.orange.mercadolivre.produto;

import br.com.orange.mercadolivre.caracteristica.CaracteristicaDTO;
import br.com.orange.mercadolivre.imagem.ImagemDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double valor;
    private Integer quantidadeDisponivel;
    private String descricao;
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    private Long categoriaId;
    private String donoProduto;
    private List<CaracteristicaDTO> caracteristicas = new ArrayList<>();
    private List<ImagemDTO> imagens = new ArrayList<>();

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
        this.descricao = produto.getDescricao();
        this.instanteCadastro = produto.getInstanteCadastro();
        this.categoriaId = produto.getCategoria().getId();
        this.donoProduto = produto.getUsuario().getLogin();
        produto.getCaracteristicas().forEach(caracteristica -> {
            CaracteristicaDTO caracteristicaDTO = new CaracteristicaDTO(caracteristica);
            this.caracteristicas.add(caracteristicaDTO);
        });
        produto.getImagens().forEach(imagem -> {
            ImagemDTO imagemDTO = new ImagemDTO(imagem.getEndereco());
            this.imagens.add(imagemDTO);
        });

    }

    public Long getId() {
        return id;
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

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getDonoProduto() {
        return donoProduto;
    }

    public List<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public List<ImagemDTO> getImagens() {
        return imagens;
    }
}
