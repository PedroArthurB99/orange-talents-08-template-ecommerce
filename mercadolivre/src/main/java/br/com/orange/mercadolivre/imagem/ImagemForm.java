package br.com.orange.mercadolivre.imagem;

import br.com.orange.mercadolivre.exception.ImagemPertenceAOutroUsuarioException;
import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;
import br.com.orange.mercadolivre.produto.Produto;
import br.com.orange.mercadolivre.produto.ProdutoRepository;
import br.com.orange.mercadolivre.usuario.Usuario;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImagemForm {

    @Size(min = 1) @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();

    public ImagemForm(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<Imagem> toModel(Long id, ProdutoRepository produtoRepository, Usuario usuario) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) throw new RegraNegocioException(new ObjetoErroDTO
                ("produtoId", "Não existe um produto com esse id"));

        //tratar retorno de status 403
        if (usuario.getId() != produto.get().getUsuario().getId()) throw new ImagemPertenceAOutroUsuarioException(new ObjetoErroDTO
                ("Usuário", "Você não pode adicionar uma imagem em um produto que não é seu."));

        List<Imagem> imagens = new ArrayList<>();
        this.imagens.forEach(imagem -> {
            imagens.add(new Imagem(imagem.getOriginalFilename()));
        });
        return imagens;
    }

}
