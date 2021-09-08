package br.com.orange.mercadolivre.categoria;

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
public class CategoriaControllerTest {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new Gson();

    @BeforeEach
    public void limbarBase() {
        this.repository.deleteAll();
    }

    @Test
    @DisplayName("Deve adicionar uma categoria com sucesso")
    public void adicionarCategoriaComSucesso() throws Exception {
        CategoriaForm categoria = new CategoriaForm("Eletrônicos", null);

        mockMvc.perform(MockMvcRequestBuilders.post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.gson.toJson(categoria)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(categoria.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve adicionar uma categoria com nome duplicado e lançar exceção")
    public void adicionarCategoriaComNomeDuplicado() throws Exception {
        Categoria categoria = new Categoria("Eletrônicos");
        repository.save(categoria);

        CategoriaForm categoriaForm = new CategoriaForm("Eletrônicos", null);

        mockMvc.perform(MockMvcRequestBuilders.post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.gson.toJson(categoriaForm)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deve passar um id inválido para o campo idCategoriaMae")
    public void adicionarCategoriaComMaeInvalida() throws Exception {
        CategoriaForm categoriaForm = new CategoriaForm("Eletrônicos", 99L);

        mockMvc.perform(MockMvcRequestBuilders.post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.gson.toJson(categoriaForm)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deve cadastrar uma categoria com Mãe")
    public void adicionarCategoriaComMae() throws Exception {
        Categoria categoria = new Categoria("Eletrônicos");
        repository.save(categoria);

        CategoriaForm categoriaForm = new CategoriaForm("Imóveis", categoria.getId());

        mockMvc.perform(MockMvcRequestBuilders.post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.gson.toJson(categoriaForm)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(categoriaForm.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoriaMaeId").value(categoria.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}