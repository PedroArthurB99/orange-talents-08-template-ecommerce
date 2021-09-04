package br.com.orange.mercadolivre.produto;

import br.com.orange.mercadolivre.caracteristica.CaracteristicaDTO;
import br.com.orange.mercadolivre.opiniao.Opiniao;
import br.com.orange.mercadolivre.opiniao.OpiniaoDTO;
import br.com.orange.mercadolivre.pergunta.PerguntaDTO;

import java.util.ArrayList;
import java.util.List;

public class DetalharProdutoDTO {

    private List<String> linksParaImagens = new ArrayList<>();
    private String nome;
    private Double preco;
    private List<CaracteristicaDTO> caracteristicas = new ArrayList<>();
    private String descricao;
    private Double mediaNotas;
    private Integer totalNotas;
    private List<OpiniaoDTO> opinioes = new ArrayList<>();
    private List<PerguntaDTO> perguntas = new ArrayList<>();

    public DetalharProdutoDTO(Produto produto) {
        preencherLinksImagens(produto);
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        preencherCaracteristicas(produto);
        this.descricao = produto.getDescricao();
        preencherMediaETotalDeNotas(produto);
        preencherOpinioes(produto);
        preencherPerguntas(produto);
    }

    private void preencherMediaETotalDeNotas(Produto produto) {
        int notas = produto.getOpinioes().stream().mapToInt(Opiniao::getNota).sum();
        this.mediaNotas = (double) (notas / produto.getOpinioes().size());
        this.totalNotas = produto.getOpinioes().size();
    }

    private void preencherPerguntas(Produto produto) {
        produto.getPerguntas().forEach(pergunta -> {
            PerguntaDTO perguntaDTO = new PerguntaDTO(pergunta);
            this.perguntas.add(perguntaDTO);
        });
    }

    private void preencherOpinioes(Produto produto) {
        produto.getOpinioes().forEach(opiniao -> {
            OpiniaoDTO opiniaoDTO = new OpiniaoDTO(opiniao);
            this.opinioes.add(opiniaoDTO);
        });
    }

    private void preencherCaracteristicas(Produto produto) {
        produto.getCaracteristicas().forEach(caracteristica -> {
            CaracteristicaDTO caracteristicaDTO = new CaracteristicaDTO(caracteristica);
            this.caracteristicas.add(caracteristicaDTO);
        });
    }

    private void preencherLinksImagens(Produto produto) {
        produto.getImagens().forEach(imagem -> {
            linksParaImagens.add("https.drive.google/" + imagem.getEndereco() + ".com");
        });
    }

    public List<String> getLinksParaImagens() {
        return linksParaImagens;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public List<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public List<OpiniaoDTO> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDTO> getPerguntas() {
        return perguntas;
    }
}
