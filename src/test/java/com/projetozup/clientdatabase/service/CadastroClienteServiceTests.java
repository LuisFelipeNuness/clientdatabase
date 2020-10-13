package com.projetozup.clientdatabase.service;

import com.projetozup.clientdatabase.dto.MensagemDTO;
import com.projetozup.clientdatabase.exception.CadastroClienteException;
import com.projetozup.clientdatabase.model.Cliente;
import com.projetozup.clientdatabase.repository.CadastroClienteRepository;
import com.projetozup.clientdatabase.service.impl.CadastroClienteServiceImpl;
import org.junit.Rule;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class CadastroClienteServiceTests {

    @Mock
    private CadastroClienteRepository cadastroClienteRepository;

    @InjectMocks
    private CadastroClienteServiceImpl cadastroClienteService;

    @Rule
    public ExpectedException  exception = ExpectedException.none();

    @Test
    public void testaCadastroComSucesso(){
        Cliente cliente = new Cliente();
        cliente.setNome("Luis");
        cliente.setCpf("2321038219031");
        cliente.setDataDeNascimento(LocalDate.now());
        cliente.setEndereco("Rua 13");

        Mockito.when(cadastroClienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteRetornado = cadastroClienteService.inserirNovoCliente(cliente);

        assertEquals(cliente, clienteRetornado);
    }

    @Test
    public void testaAtualizarComSucesso(){
        Cliente cliente = new Cliente();
        cliente.setNome("Andre");
        cliente.setCpf("2321038219031");
        cliente.setDataDeNascimento(LocalDate.now());
        cliente.setEndereco("Rua 13");

        Mockito.when(cadastroClienteRepository.save(cliente)).thenReturn(cliente);
        Mockito.when(cadastroClienteRepository.findById(4l)).thenReturn(Optional.of(cliente));

        Cliente clienteRetornado = cadastroClienteService.atualizarCliente(cliente, 4l);

        assertEquals(cliente, clienteRetornado);
    }

    @Test
    public void testaDeletarComSucesso(){

        String stringEsperada = "Usuario removido";

        MensagemDTO mensagem = cadastroClienteService.deletarCliente(5l);

        assertEquals(stringEsperada,mensagem.getMensagem());

    }

    @Test
    public void testaVizualizarConSucesso(){
        Cliente clienteEsperado = new Cliente();
        clienteEsperado.setNome("Luis");
        clienteEsperado.setCpf("2321038219031");
        clienteEsperado.setDataDeNascimento(LocalDate.now());
        clienteEsperado.setEndereco("Rua 13");

        Mockito.when(cadastroClienteRepository.findById(12l)).thenReturn(Optional.of(clienteEsperado));

        Cliente cliente = cadastroClienteService.visualizarCliente(12l);

        assertEquals(clienteEsperado,cliente);
    }

    @Test
    public void testaListarClientes(){
        Cliente cliente = new Cliente();
        cliente.setNome("Luis");
        cliente.setCpf("2321038219031");
        cliente.setDataDeNascimento(LocalDate.now());
        cliente.setEndereco("Rua 13");


        List<Cliente> listaClientesEsperada = new ArrayList<>();
        listaClientesEsperada.add(cliente);

        Mockito.when(cadastroClienteRepository.findAll()).thenReturn(listaClientesEsperada);

        List<Cliente> listaClientes = cadastroClienteService.listarClientes();

        assertEquals(listaClientesEsperada,listaClientes);
    }

}
