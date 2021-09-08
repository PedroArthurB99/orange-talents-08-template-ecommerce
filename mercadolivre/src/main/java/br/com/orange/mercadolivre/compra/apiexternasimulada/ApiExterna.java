package br.com.orange.mercadolivre.compra.apiexternasimulada;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "sistema-externo", url = "${feign.client.api-externa}")
public interface ApiExterna {

    @PostMapping(value="/notasfiscais")
    public String comunicaSistemaNotaFiscal(@RequestBody @Valid FormSistemaNotaFiscal form);

    @PostMapping(value="/rankings")
    public String comunicaSistemaRanking(@RequestBody @Valid FormSistemaRanking form);

}
