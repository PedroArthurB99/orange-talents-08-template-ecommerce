package br.com.orange.mercadolivre.pergunta;

import br.com.orange.mercadolivre.usuario.Usuario;

public class EnviadorDeEmails {

    public static void enviarEmail(Usuario usuarioDono, Usuario usuarioPergunta) {
        System.out.println("Olá, " + usuarioDono.getLogin() + ". O usuário " + usuarioPergunta.getLogin() + " fez uma pergunta no seu anúncio");
    }
}
