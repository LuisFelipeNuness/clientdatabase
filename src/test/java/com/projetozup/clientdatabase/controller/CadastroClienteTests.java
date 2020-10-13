package com.projetozup.clientdatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetozup.clientdatabase.model.Cliente;
import com.projetozup.clientdatabase.service.impl.CadastroClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CadastroClienteTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private CadastroClienteServiceImpl cadastroClienteService;

    @Test
    public void testeCadastroComSucesso() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Luis");
        cliente.setCpf("2321038219031");
        cliente.setDataDeNascimento(LocalDate.now());
        cliente.setEndereco("Rua 13");

        this.mvc.perform(post("/cliente")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated());

    }

    @Test
    public void testeAtualizarComSucesso() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Andre");
        cliente.setCpf("2321038219031");
        cliente.setDataDeNascimento(LocalDate.now());
        cliente.setEndereco("Rua 13");

        this.mvc.perform(put("/cliente/2")
                .param("id", "2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk());

    }

    @Test
    public void testeVisualizarComSucesso() throws Exception {

        this.mvc.perform(get("/cliente/2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new Cliente())))
                .andExpect(status().isOk());

    }

    @Test
    public void testeDeletarComSucesso() throws Exception {

        this.mvc.perform(delete("/cliente/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testeVisualizarTodosComSucesso() throws Exception {

        this.mvc.perform(get("/cliente")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new Cliente())))
                .andExpect(status().isOk());

    }

    @Test
    public void testeCadastroComFalhaDeValidacao() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("2321038219031");
        cliente.setDataDeNascimento(LocalDate.now());
        cliente.setEndereco("Rua 13");

        this.mvc.perform(post("/cliente")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void testeAtualizarComFalhaDeValidacao() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("2321038219031");
        cliente.setDataDeNascimento(LocalDate.now());
        cliente.setEndereco("Rua 13");

        this.mvc.perform(put("/cliente/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void testeVisualizarComFalhaDeValidacao() throws Exception {

        this.mvc.perform(get("/cliente/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new Cliente())))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testeDeletarComFalhaDeValidacao() throws Exception {


        this.mvc.perform(delete("/cliente")
                .param("id", "11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }
}
