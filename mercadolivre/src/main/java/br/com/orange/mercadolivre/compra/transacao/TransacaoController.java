package br.com.orange.mercadolivre.compra.transacao;

import br.com.orange.mercadolivre.compra.Compra;
import br.com.orange.mercadolivre.compra.CompraRepository;
import br.com.orange.mercadolivre.compra.apiexternasimulada.ApiExterna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ApiExterna apiExterna;

    @PostMapping
    @RequestMapping("/retorno-pagseguro/{id}")
    public String cadastrarTransacaoPagSeguro(@PathVariable("id") Long id,  @RequestBody @Valid TransacaoForm form) {
        return processo(id, form);
    }

    @PostMapping
    @RequestMapping("/retorno-paypal/{id}")
    public String cadastrarTransacaoPayPal(@PathVariable("id") Long id,  @RequestBody @Valid TransacaoForm form) {
        return processo(id, form);
    }

    private String processo(Long id, TransacaoForm form) {
        Transacao transacao = form.toModel(this.compraRepository, id);
        Compra compra = compraRepository.findById(id).get();
        compra.adicionarTransacao(transacao, apiExterna);
        compra = compraRepository.save(compra);
        return "deu certo a primeira parte - " + compra.toString();
    }
}
