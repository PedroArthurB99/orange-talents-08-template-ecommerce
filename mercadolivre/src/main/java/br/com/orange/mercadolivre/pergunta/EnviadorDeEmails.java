package br.com.orange.mercadolivre.pergunta;

import br.com.orange.mercadolivre.compra.Compra;
import br.com.orange.mercadolivre.compra.GatewayEnum;
import br.com.orange.mercadolivre.usuario.Usuario;

public class EnviadorDeEmails {

    public static void enviarEmail(Usuario usuarioDono, Usuario usuarioPergunta) {
        System.out.println("Olá, " + usuarioDono.getLogin() + ". O usuário " + usuarioPergunta.getLogin() + " fez uma pergunta no seu anúncio");
    }

    public static void enviarEmailIndicativoCompra(Usuario usuarioDono, Usuario usuarioPergunta, Compra compra) {
        System.out.println("Olá, " + usuarioDono.getLogin() + ". O usuário " + usuarioPergunta.getLogin() + " quer comprar " +
                compra.getQuantidade() + " unidades do seu produto " + compra.getProduto().getNome() +
                ". Contate-o o mais rápido possível para não perder essa oportunidade de fazer negócio.");
    }

    public static void enviarEmailCompraSucesso(Compra compra) {
        System.out.println("Olá, " + compra.getUsuario().getLogin() + ". A sua compra foi efetuada com sucesso. Suas " +
                compra.getQuantidade() + " unidades do produto " + compra.getProduto().getNome() + " estão a caminho.");
    }

    public static void enviarEmailCompraFalhou(Compra compra) {
        String path = compra.getGateway().equals(GatewayEnum.PAYPAL) ? "retono-paypal" : "retorno-pagseguro";

        System.out.println("Olá, " + compra.getUsuario().getLogin() + ". Houve um problema com a sua compra. Suas " +
                compra.getQuantidade() + " unidades do produto " + compra.getProduto().getNome() + " não foram enviadas. " +
                "Por favor tente pagar novamente no link: http://localhost:8080/" + path +"/" + compra.getId());
    }
}
