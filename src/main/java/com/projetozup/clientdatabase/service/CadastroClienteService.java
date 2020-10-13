package com.projetozup.clientdatabase.service;

import com.projetozup.clientdatabase.dto.MensagemDTO;
import com.projetozup.clientdatabase.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface CadastroClienteService {

    public Cliente inserirNovoCliente(Cliente cliente);

    public Cliente atualizarCliente(Cliente cliente, Long id );

    public MensagemDTO deletarCliente(Long id);

    public Cliente visualizarCliente(Long id);

    List<Cliente> listarClientes();
}
