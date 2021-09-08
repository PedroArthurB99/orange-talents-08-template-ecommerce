package br.com.orange.mercadolivre.compra.transacao;

import br.com.orange.mercadolivre.exception.ObjetoErroDTO;
import br.com.orange.mercadolivre.exception.RegraNegocioException;

public enum StatusTransacaoEnum {
    FALHA, SUCESSO;

    public static StatusTransacaoEnum montarEnum(String value) {
        switch (value) {
            case "SUCESSO":
                return StatusTransacaoEnum.SUCESSO;
            case "1":
                return StatusTransacaoEnum.SUCESSO;
            case "FALHA":
                return StatusTransacaoEnum.FALHA;
            case "0":
                return StatusTransacaoEnum.FALHA;
        }
        throw new RegraNegocioException(new ObjetoErroDTO("statusTransacaoEnum", "Você precisa passar um status válido"));
    }
}
