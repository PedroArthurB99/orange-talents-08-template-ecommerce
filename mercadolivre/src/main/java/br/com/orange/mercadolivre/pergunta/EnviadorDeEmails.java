package br.com.orange.mercadolivre.pergunta;

import br.com.orange.mercadolivre.compra.Compra;
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
}
