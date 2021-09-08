package br.com.orange.mercadolivre.usuario;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new Gson();

    @BeforeEach
    public void limbarBase() {
        this.repository.deleteAll();
    }

    @Test
    @DisplayName("Deve adicionar um usuário com sucesso")
    public void cadastrarUsuarioComSucesso() throws Exception {
        UsuarioForm form = new UsuarioForm("pedro@email.com", "123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.gson.toJson(form)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve adicionar um usuário com login duplicado")
    public void cadastrarUsuarioComLoginDuplicado() throws Exception {
        Usuario usuario = new Usuario("pedro@email.com", "123456");
        repository.save(usuario);

        UsuarioForm form = new UsuarioForm("pedro@email.com", "123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.gson.toJson(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}