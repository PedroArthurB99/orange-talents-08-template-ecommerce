package br.com.orange.mercadolivre.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ImagemPertenceAOutroUsuarioException extends RuntimeException {

    private ObjetoErroDTO objetoErroDTO;

    public ImagemPertenceAOutroUsuarioException(ObjetoErroDTO objetoErroDTO) {
        this.objetoErroDTO = objetoErroDTO;
    }

    public ObjetoErroDTO getObjetoErroDTO() {
        return objetoErroDTO;
    }
}
