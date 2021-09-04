package br.com.orange.mercadolivre.usuario;

import br.com.orange.mercadolivre.validator.CampoUnicoConstraint;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class UsuarioForm {

    @NotBlank @Email @CampoUnicoConstraint(modelClass = Usuario.class, campo = "login")
    private String login;

    @NotBlank @Size(min=6)
    private String senha;

    public Usuario toModel(PasswordEncoder encoder) {
        return new Usuario(this.login, encoder.encode(this.senha));
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
