package br.com.orange.mercadolivre.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class UsuarioForm {

    @NotBlank @Email
    private String login;

    @NotBlank @Size(min=6)
    private String senha;

    @NotNull @PastOrPresent
    private LocalDateTime dataCadastro;

    public Usuario toModel(PasswordEncoder encoder) {
        return new Usuario(this.login, encoder.encode(this.senha), this.dataCadastro);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
