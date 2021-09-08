package br.com.orange.mercadolivre.compra.apiexternasimulada;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-externa")
public class ApiExternaController {

    @PostMapping("/notasfiscais")
    public String conectaSistemaNotaFiscal(@RequestBody FormSistemaNotaFiscal form) {
        System.out.println(form.toString());
        return form.toString();
    }

    @PostMapping("/rankings")
    public String conectaSistemaRanking(@RequestBody FormSistemaRanking form){
        System.out.println(form.toString());
        return form.toString();
    }

}
